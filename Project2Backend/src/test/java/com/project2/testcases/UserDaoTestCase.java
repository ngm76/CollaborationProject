package com.project2.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project2.configuration.DBConfiguration;
import com.project2.dao.UserDao;
import com.project2.dao.UserDaoImpl;
import com.project2.models.User;



public class UserDaoTestCase  {
	ApplicationContext context = new AnnotationConfigApplicationContext(DBConfiguration.class,UserDaoImpl.class);
	UserDao userDao = (UserDao) context.getBean("userDaoImpl");
	
	@Ignore
	public void testRegisterUser() {
		User user = new User();
		user.setFirstname("john");
		user.setLastname("smith");
		user.setEmail("john@smith.com");
		user.setPassword("john");
		user.setPhonenumber("777777");
		user.setRole("Student");
		user.setOnline(true);
		
		userDao.RegisterUser(user);
		assertTrue(user.getEmail() != null);
	}
	
	
	@Test
	public void testGetUser() {
		User user = userDao.getUser("adam@adam.com");
		System.out.println(user.getFirstname() + " \n" + user.getLastname());
		assertTrue(user != null);
	}
	
	
	@Test
	public void testUpdateUser() {
		User user = userDao.getUser("adam@adam.com");
		user.setOnline(false);
		System.out.println(user.isOnline());
		assertTrue(user.isOnline() == false);
	}
	
}
