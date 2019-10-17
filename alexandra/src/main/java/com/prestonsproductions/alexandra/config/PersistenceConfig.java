package com.prestonsproductions.alexandra.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.typesafe.config.Config;

/**
 * @author Preston Frazier
 *
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	@Autowired
	private Config properties;
 
	   @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.prestonsproductions.alexandra" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	      return em;
	   }
	 
	   @Bean
	   public DataSource dataSource(){
	      ComboPooledDataSource cpds = new ComboPooledDataSource();
	      try {
			cpds.setDriverClass(properties.getString("com.prestonsproductions.alexandra.DATABASE_DRIVER"));
			cpds.setJdbcUrl(properties.getString("com.prestonsproductions.alexandra.DATABASE_URL"));
			cpds.setUser(properties.getString("com.prestonsproductions.alexandra.DATABASE_USERNAME"));
			cpds.setPassword(properties.getString("com.prestonsproductions.alexandra.DATABASE_PASSWORD"));
			
			cpds.setMinPoolSize(1);
			cpds.setInitialPoolSize(1);
			cpds.setMaxPoolSize(10);
			cpds.setMaxIdleTime(1800);
			cpds.setMaxStatements(50);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	      return cpds;
	   }
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);
	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	      properties.setProperty("hibernate.show_sql", "false");	     
	      return properties;
	   }
}
