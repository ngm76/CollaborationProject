/**
 * BlogCtrl
 */
app.controller('BlogCtrl',function($scope,BlogService,$location,$rootScope,$routeParams){
	$scope.addBlog=function(blog){//call this function addBlog from blogform.html
		BlogService.addBlog(blog).then(
				function(response){
					alert('Blog details inserted successfully and it is waiting for approval')
					$location.path('/home')
				},
				function(response){
					$scope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
	}
	
	
	//List of Blogs Waiting for approval - Statement
	if($rootScope.user!=undefined){//Checking authentication
	if($rootScope.user.role=='ADMIN' && $routeParams.id==1) //checking authorization
	BlogService.getBlogsWaitingForApproval().then(
			function(response){
				//response.data? - result of which query? ,select * from blogpost where approved=0
				$scope.blogsWaitingForApproval=response.data
			},function(response){
				$scope.error=response.data
				console.log($scope.error)
				if(response.status==401 && $scope.error.errorCode==5)    //Not Authenticated
					$location.path('/login')
			})
	}else{
		$location.path('/login')
	}
	
	if($rootScope.user!=undefined){
		if($routeParams.id==2)
	BlogService.getBlogsApproved().then(function(response){
		//response.data? - result of which query ? -select * from blogpost where approved=1
		$scope.blogs=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})}else{
		$location.path('/login')
	}
	
	
	
/*	var notificationId = $routeParams.id
	function getAllNotifications() {
		BlogService.getAllNotifications().then(function(response) {
			// response.data ? Array of notifications not yet viewed
			
			$rootScope.notifications = response.data
			console.log($rootScope.notifications)
			$rootScope.notificationCount = $rootScope.notifications.length
		}, function(response) {
			if (response.status == 401)
				$location.path('/login')
		})
	}
	if (notificationId != undefined) {
		BlogService.getNotification(notificationId).then(
				function(response) {
					// response.data ? select * from notification where id=?
					// single notification object
					$scope.notification = response.data // notificationDetails.html
				}, function(response) {
					if (response.status == 401)
						$location.path('/login')
				})

		BlogService.updateNotification(notificationId).then(
				function(response) {
					getAllNotifications()
					// call the function to execute the statement
					// NotificationService.getAllNotifications()
				}, function(response) {
					if (response.status == 401)
						$location.path('/login')
				})

	}

	getAllNotifications()// call the function to execute the statement
							// NotificationService.getAllNotifications()
	
	
	
	*/
	
	
	
	
	
	
	
})