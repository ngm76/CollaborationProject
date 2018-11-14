package com.project2.testcases;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project2.configuration.DBConfiguration;
import com.project2.dao.UserDao;
import com.project2.dao.UserDaoImpl;
import com.project2.models.User;

import junit.framework.TestCase;

public class UserDaoTestCase extends TestCase {
	ApplicationContext context = new AnnotationConfigApplicationContext(DBConfiguration.class , UserDaoImpl.class);
	UserDao userDao = (UserDao) context.getBean("userDaoImpl");
	
	
	@Test
	public void testRegisterUser() {
		User user = new User();
		user.setFirstname("neha");
		user.setLastname("neha");
		user.setEmail("neha@neha.com");
		user.setPassword("neha");
		user.setPhonenumber("7798940189");
		user.setRole("Student");
		user.setOnline(true);
		
		userDao.RegisterUser(user);
		assertTrue(user.getEmail() != null);
	}
	
	@Test
	public void testGetUser() {
		User user = userDao.getUser("manoli.neha07@gmail.com");
		System.out.println(user.getFirstname() + " \n" + user.getLastname());
		assertTrue(user != null);
	}
	
	public void textUpdateUser() {
		User user = userDao.getUser("manoli.neha07@gmail.com");
		user.setOnline(false);
		System.out.println(user.isOnline());
		assertTrue(user.isOnline() == false);
	}
	
}
