package com.project2.testcases;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project2.configuration.DBConfiguration;
import com.project2.dao.NotificationDao;
import com.project2.dao.NotificationDaoImpl;
import com.project2.dao.UserDao;
import com.project2.dao.UserDaoImpl;
import com.project2.models.Notification;
import com.project2.models.User;

public class NotificationTestDao {

	ApplicationContext context = new AnnotationConfigApplicationContext(DBConfiguration.class , NotificationDaoImpl.class , UserDaoImpl.class);
	NotificationDao notificationDao = (NotificationDao) context.getBean("notificationDaoImpl");
	UserDao userDao = (UserDao) context.getBean("userDaoImpl");
	
	@Test
	public void testGetAllNotifications() {
		String email="neha@neha.com";
		List<Notification> notifications = notificationDao.getAllNotifications(email);
		assertTrue(notifications.size() > 0);
		
		for(Notification n : notifications)
		System.out.println(n);
		
		
	}
	
}
