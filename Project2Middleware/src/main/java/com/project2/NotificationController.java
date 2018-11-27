package com.project2;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project2.dao.NotificationDao;
import com.project2.models.ErrorClass;
import com.project2.models.Notification;

@Controller
public class NotificationController {
	@Autowired
	private NotificationDao notificationDao;

	NotificationController() {
		System.out.println("Inside NotificationController");
	}

	@RequestMapping(value = "/getallnotifications", method = RequestMethod.GET)
	public ResponseEntity<?> getAllNotifications(HttpSession session) {// get email id of the loggedin user
		System.out.println("inside getAllNotifications");
		// CHECK FOR AUTHENTICATION
		String email = (String) session.getAttribute("email");
		if (email == null) {
			ErrorClass errorClass = new ErrorClass(5, "Unauthorized access.. please login");
			return new ResponseEntity<ErrorClass>(errorClass, HttpStatus.UNAUTHORIZED);
		}
		//String email="neha@neha.com";
		List<Notification> notifications = notificationDao.getAllNotifications(email);
		return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
	}

	@RequestMapping(value = "/getnotification/{notificationId}", method = RequestMethod.GET)
	public ResponseEntity<?> getNotification(@PathVariable int notificationId, HttpSession session) {
		System.out.println("inside getNotification");
		String email = (String) session.getAttribute("email");
		if (email == null) {
			ErrorClass errorClass = new ErrorClass(5, "Unauthorized access.. please login");
			return new ResponseEntity<ErrorClass>(errorClass, HttpStatus.UNAUTHORIZED);
		}
		Notification notification = notificationDao.getNotification(notificationId);
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);
	}

	@RequestMapping(value = "/updatenotification/{notificationId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNotificationViewedStatus(@PathVariable int notificationId, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			ErrorClass errorClass = new ErrorClass(5, "Unauthorized access.. please login");
			return new ResponseEntity<ErrorClass>(errorClass, HttpStatus.UNAUTHORIZED);
		}
		notificationDao.updateNotification(notificationId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
