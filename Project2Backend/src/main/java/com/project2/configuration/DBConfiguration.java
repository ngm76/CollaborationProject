package com.project2.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project2.models.BlogComment;
import com.project2.models.BlogPost;
import com.project2.models.BlogPostLikes;
import com.project2.models.Job;
import com.project2.models.Notification;
import com.project2.models.User;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {
	public DBConfiguration() {
		System.out.println("DBConfiguration class is instantiated");
	}

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		lsf.addProperties(hibernateProperties);

		Class[] classes = new Class[] { User.class , Job.class , BlogPost.class , Notification.class , BlogComment.class , BlogPostLikes.class };// class objects of all the entities

		return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		// dataSource.setDriverClassName("org.h2.Driver");
		// dataSource.setUrl("jdbc:h2:tcp://localhost/~/project2");
		dataSource.setUsername("SYSTEM");
		dataSource.setPassword("system");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransManagment() {
		return new HibernateTransactionManager(sessionFactory());
	}
}
