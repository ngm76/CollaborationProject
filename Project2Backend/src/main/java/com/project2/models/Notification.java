package com.project2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Notification")
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@ManyToOne
private User userToBeNotified;//FK constraint
private String approvalStatus;//either Approved or Rejected
private String blogTitle;
private String rejectionReason;
private boolean viewed;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public User getUserToBeNotified() {
	return userToBeNotified;
}
public void setUserToBeNotified(User email) {
	this.userToBeNotified = email;
}
public String getApprovalStatus() {
	return approvalStatus;
}
public void setApprovalStatus(String approvalStatus) {
	this.approvalStatus = approvalStatus;
}
public String getBlogTitle() {
	return blogTitle;
}
public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}
public String getRejectionReason() {
	return rejectionReason;
}
public void setRejectionReason(String rejectionReason) {
	this.rejectionReason = rejectionReason;
}
public boolean isViewed() {
	return viewed;
}
public void setViewed(boolean viewed) {
	this.viewed = viewed;
}

}

