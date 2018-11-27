
app.controller('UserCtrl', function($scope, UserService , $location , $rootScope , $cookieStore) {

	
	
	$scope.registration = function(user) {// user object from view
		alert("inside registration of usercontroller")
		UserService.registration(user)// to service
		.then(function(response) {
			$location.path('/login')
		}, function(response) {
			$scope.error = response.data
		})
	}

	$scope.login = function(user) {
		console.log("inside login of usercontroller")
		UserService.login(user).then(function(response) {
			$rootScope.user=response.data
			$cookieStore.put('loggedInUser',response.data)
			$location.path('/home')
		}, function(response) {
			$scope.error = response.data
		})
	}
	
	$rootScope.logout=function(){
		//function is called from index.html
		alert('logout function is called')
		UserService.logout().then(function(response){
			delete $rootScope.user
			$location.path('/login')
		},function(response){
			delete $rootScope.user
			if(response.status==401)//Unauthorized
				$location.path('/login')
		})
	}
})
