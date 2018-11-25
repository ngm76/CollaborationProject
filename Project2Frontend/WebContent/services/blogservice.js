app.factory('BlogService',function($http){
	var blogService={}
	
	blogService.addBlog=function(blog){
		return $http.post("http://localhost:8097/Project2Middleware/addblogpost",blog)
	}
	
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:8097/Project2Middleware/blogswaitingforapproval")
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:8097/Project2Middleware/blogsapproved")
	}
	
	blogService.getBlog=function(blogPostId){
		return $http.get("http://localhost:8097/Project2Middleware/getBlog/"+blogPostId)
	}
	
    blogService.approve=function(blogPost){
    	return $http.put("http://localhost:8097/Project2Middleware/approve",blogPost)
    }	
	
	blogService.reject=function(blogPost,rejectionReason){
		return $http.put("http://localhost:8097/Project2Middleware/reject/"+rejectionReason,blogPost)
	}
	
	blogService.hasUserLikedBlogPost=function(blogPostId){
		return $http.get("http://localhost:8097/Project2Middleware/hasUserLikedBlogPost/"+blogPostId);
	}
	
	blogService.updateLikes=function(blogPostId){
		return $http.put("http://localhost:8097/Project2Middleware/updatelikes/"+blogPostId);
	}
	
	blogService.addBlogComment=function(blogComment){
		//newly created blogcomment object with the values for two properties - blogPost, commentTxt
		//blogComment {'blogPost':{},'commentTxt':'Thanks'}
		return $http.post("http://localhost:8097/Project2Middleware/addblogcomment",blogComment)
	}
	
	blogService.getAllBlogComments=function(blogPostId){
	return $http.get("http://localhost:8097/Project2Middleware/getblogcomments/"+blogPostId)	
	}
	
	
	
	/*
	blogService.getAllNotifications=function(){
		alert("notificationService.getAllNotifications")
		return $http.get("http://localhost:8097/Project2Middleware/getallnotifications")
	}
	
	
	blogService.getNotification=function(notificationId){
		alert("notificationService.getNotification")
		return $http.get("http://localhost:8097/Project2Middleware/getnotification/"+notificationId)
	}
	
	blogService.updateNotification=function(notificationId){
		alert("notificationService.updateNotification")
		return $http.put("http://localhost:8097/Project2Middleware/updatenotification/"+notificationId);
	}
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	return blogService;
})

