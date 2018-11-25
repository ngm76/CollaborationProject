/**
 * FriendService
 */

app.factory('FriendService',function($http){
	var friendService={}

	friendService.getAllSuggestedUsers=function(){
		return $http.get("http://localhost:8097/Project2Middleware/suggestedusers")
	}
	
	friendService.sendFriendRequest=function(user){//value for toId property in Friend Entity
		return $http.post("http://localhost:8097/Project2Middleware/friendrequest",user)
	}
	
	friendService.getPendingRequests=function(){
		return $http.get("http://localhost:8097/Project2Middleware/pendingrequests")
	}
	
	friendService.acceptRequest=function(friendRequest){
		return $http.put("http://localhost:8097/Project2Middleware/acceptrequest",friendRequest)
	}
	friendService.deleteRequest=function(friendRequest){
		return $http.put("http://localhost:8097/Project2Middleware/deleterequest",friendRequest)
	}
	
	friendService.getAllFriends=function(){
		return $http.get("http://localhost:8097/Project2Middleware/friends")
	}
	
	return friendService;
})
