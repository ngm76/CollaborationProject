package com.project2.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project2.models.Notification;

@Service
public interface NotificationDao {
	void addNotification(Notification notification);
	List<Notification>  getAllNotifications(String email);
	Notification getNotification(int notificationId);
	void updateNotification(int notificationId);
	
}
