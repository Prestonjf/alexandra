package com.prestonsproductions.alexandra.config;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * @author Preston Frazier
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.prestonsproductions.alexandra")
public class WebMvcConfig implements WebMvcConfigurer  {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(
          new String[] { "/WEB-INF/tiles-def.xml" });
        tilesConfigurer.setCheckRefresh(true);
         
        return tilesConfigurer;
    }
     
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
     
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
    	registry.addInterceptor(new CustomRequestInterceptor()).excludePathPatterns("/resources/**");
    }
    
	@Bean
	public Config properties() throws Exception {
		Config conf = ConfigFactory
			.parseFile(new File(System.getProperty("catalina.base") + System.getProperty("file.separator")
						+ "properties" + System.getProperty("file.separator") + "alexandra.properties"));
	
		// Set Log Level
		Logger root = (Logger)LoggerFactory.getLogger("com.prestonsproductions.alexandra");
		root.setLevel(getLogLevel(conf.getString("com.prestonsproductions.alexandra.LOG_LEVEL")));
		
		Properties p = new Properties();
		p.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
		//AWSKMS kms = AWSKMSClientBuilder.defaultClient();
		//conf = addNewPropertyToConfig(conf,decrypt(kms, conf.getString("com.prestonsproductions.alexandra.DATABASE_PASSWORD")), "com.prestonsproductions.alexandra.DATABASE_PASSWORD");
		conf = addNewPropertyToConfig(conf,p.getProperty("version"), "com.prestonsproductions.alexandra.VERSION");
		conf = addNewPropertyToConfig(conf,p.getProperty("name"), "com.prestonsproductions.alexandra.NAME");

		return conf;
	}
	
	private Config addNewPropertyToConfig(Config newConf, String decryptedString, String property) {
		ConfigValue cv = ConfigValueFactory.fromAnyRef(decryptedString);
		newConf = newConf.withValue(property, cv);
		return newConf;
	}
	
	@SuppressWarnings("unused")
	private String decrypt(AWSKMS kms, String password) {
		String decodedString = "";
		ByteBuffer ciphertext = ByteBuffer.wrap(com.amazonaws.util.Base64.decode(password));
		DecryptRequest decreq = new DecryptRequest().withCiphertextBlob(ciphertext);
		ByteBuffer plaintext = kms.decrypt(decreq).getPlaintext();
		decodedString = Charset.forName("UTF-8").decode(plaintext).toString();
		return decodedString;
	}
	
	private Level getLogLevel(String level) {
		Level l = Level.INFO;
		if (level != null) {
			if ("debug".equalsIgnoreCase(level)) l = Level.DEBUG;
			else if ("info".equalsIgnoreCase(level)) l = Level.INFO;
			else if ("error".equalsIgnoreCase(level)) l = Level.ERROR;
			else if ("off".equalsIgnoreCase(level)) l = Level.OFF;
			else if ("all".equalsIgnoreCase(level)) l = Level.ALL;
		}
		return l;
	}

	
}
