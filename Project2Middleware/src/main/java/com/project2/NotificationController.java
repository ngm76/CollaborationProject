package com.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project2.dao.NotificationDao;

@Controller
public class NotificationController {
	@Autowired
	private NotificationDao notificationDao;
}
