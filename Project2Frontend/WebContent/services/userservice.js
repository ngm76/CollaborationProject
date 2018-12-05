/**
 * UserService
 */
app.factory('UserService',function($http){
	var userService={}
	
	console.log("UserService");
	
	userService.registration=function(user){//user from controller
		alert("inside user service registration")
		return $http.post("http://localhost:8097/Project2Middleware/register",user)
	}
	
	userService.login=function(user){
		alert("inside user service login")
		return $http.put("http://localhost:8097/Project2Middleware/login",user)
	}	
	
	userService.logout=function(){
		alert("inside user service logout")
		return $http.put("http://localhost:8097/Project2Middleware/logout")
	}
	
	userService.update=function(user){
		alert("inside user service update")
		return $http.put("http://localhost:8097/Project2Middleware/update",user)

	}
	
	userService.getUser = function(){
		alert("inside user service getuser")
		return $http.get("http://localhost:8097/Project2Middleware/getuser")

	}
	
	return userService;
})