package com.colin.app.config.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;

import com.colin.app.config.database.jaxb.ResourceDetail;
import com.colin.app.config.database.jaxb.ResourceDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@PropertySource({ "classpath:persistence.properties" })
@EnableTransactionManagement
@ComponentScan({ "com.colin.app.entity" })
@EnableJpaRepositories(basePackages = "com.colin.app.entity.repository")
public class JpaConfig {

	@Autowired
	private Environment properties;

	public JpaConfig() {
		super();
	}

	@Bean()
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws FileNotFoundException, JAXBException {
		final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(multipleDataSource());
		entityManager.setPackagesToScan(new String[] { "com.colin.app.entity.domain" });
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		entityManager.setJpaProperties(additionalProperties());
		return entityManager;
	}

	@Bean
	public DataSource multipleDataSource() throws FileNotFoundException, JAXBException {
		// convert 
		File file = ResourceUtils.getFile("classpath:" + properties.getProperty("resource.routing.file"));
		JAXBContext jaxbContext = JAXBContext.newInstance(ResourceDetails.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ResourceDetails resourceRouting = (ResourceDetails) jaxbUnmarshaller.unmarshal(file);

		Map<Object, Object> resolvedDataSources = new HashMap<Object, Object>();
        for(ResourceDetail resourceDetail : resourceRouting.getResourcedetail()) {
            DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());

			dataSourceBuilder.driverClassName(resourceDetail.getName())
			        .url(resourceDetail.getUrl())
			        .username(resourceDetail.getUser())
			        .password(resourceDetail.getPass());

//                if(properties.getType() != null) {
//                    dataSourceBuilder.type(properties.getType());
//                }
			
			resolvedDataSources.put(resourceDetail.getKey(), dataSourceBuilder.build());
        }
		
		
		
		// call data source lookup key map to get the right data source
		JpaRoutingDataSource dataSource = new JpaRoutingDataSource();
		
		dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();
		return dataSource;
	}
	
	/**
     * Creates the default data source for the application
     * @return
     */
	//@Bean
	public DataSource defaultDataSource() {
        DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader())
                .driverClassName(properties.getProperty("jdbc.driverClassName"))
                .url(properties.getProperty("jdbc.url"))
                .username(properties.getProperty("jdbc.user"))
                .password(properties.getProperty("jdbc.pass"));

//        if(Objects.nonNull(properties.getProperty("jdbc.type"))) {
//            dataSourceBuilder.setType(properties.getProperty("jdbc.type"));
//        }

        return dataSourceBuilder.build();
    }
    
    //@Bean
	public DataSource singleDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(properties.getProperty("jdbc.url"));
		dataSource.setUsername(properties.getProperty("jdbc.user"));
		dataSource.setPassword(properties.getProperty("jdbc.pass"));
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws FileNotFoundException, JAXBException {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	protected Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
		return hibernateProperties;
	}
	
	/**
	 * Config Jackson Mapper to receive empty and null value
	 * @return
	 */
	@Bean
	public ObjectMapper mapper() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	    return mapper;
	}
}
