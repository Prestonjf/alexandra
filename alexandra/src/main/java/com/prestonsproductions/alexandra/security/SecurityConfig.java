package com.prestonsproductions.alexandra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.prestonsproductions.alexandra.config.CustomSuccessHandler;

/**
 * @author Preston Frazier
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserAuthenticationProvider authProvider;
    
    @Bean
    public AuthenticationSuccessHandler MyCustomSuccessHandler(){
        return new CustomSuccessHandler();
    }
    
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home",true)
			.failureUrl("/login?failure=1")
			.successHandler(MyCustomSuccessHandler())
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.and()
			.httpBasic();
		

		http.csrf().ignoringAntMatchers("/login*");
		http.sessionManagement().maximumSessions(1);
		http.sessionManagement().invalidSessionUrl("/invalid-session");
		http.headers().frameOptions().disable();
	}
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/invalid-session");
        web.ignoring().antMatchers("/resources/static/**");
    }
     
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider); 
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}