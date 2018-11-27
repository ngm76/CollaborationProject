/**
 * NotificationService
 */
app.factory('NotificationService',function($http){
	var notificationService={}
	
	console.log("NotificationService");
	
	notificationService.getAllNotifications=function(){
		alert("notificationService.getAllNotifications")
		return $http.get("http://localhost:8097/Project2Middleware/getallnotifications")
	}
	
	
	notificationService.getNotification=function(notificationId){
		alert("notificationService.getNotification")
		return $http.get("http://localhost:8097/Project2Middleware/getnotification/"+notificationId)
	}
	
	notificationService.updateNotification=function(notificationId){
		alert("notificationService.updateNotification")
		return $http.put("http://localhost:8097/Project2Middleware/updatenotification/"+notificationId);
	}
	
	return notificationService;
})

