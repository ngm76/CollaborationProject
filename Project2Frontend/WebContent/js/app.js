/**
 * Angular Module
 */

var app = angular.module("app", [ 'ngRoute', 'ngCookies' ])

app.config(function($routeProvider) {
	$routeProvider.when('/registration', {
		controller : 'UserCtrl',
		templateUrl : 'views/registrationform.html'
	}).when('/login', {
		controller : 'UserCtrl',
		templateUrl : 'views/login.html'
	}).when('/home', {
		templateUrl : 'views/home.html'
	}).when('/addjob', {
		controller : 'JobCtrl',
		templateUrl : 'views/jobform.html'
	}).when('/getalljobs', {
		controller : 'JobCtrl',
		templateUrl : 'views/listofjobs.html'
	}).when('/addblog', {
		controller : 'BlogCtrl',
		templateUrl : 'views/blogform.html'
	}).when('/blogsWaitingForApproval/:id', {
		controller : 'BlogCtrl',
		templateUrl : 'views/blogsWaitingForApproval.html'
	}).when('/blogsApproved/:id', {
		controller : 'BlogCtrl',
		templateUrl : 'views/blogApproved.html'
	}).when('/getBlogApprovalForm/:id', {
		controller : 'BlogInDetailCtrl',
		templateUrl : 'views/blogApprovalForm.html'
	}).when('/getBlogApproved/:id', {
		controller : 'BlogInDetailCtrl',
		templateUrl : 'views/blogdetails.html'
	}).when('/getnotification/:id', {
		controller : 'NotificationCtrl',
		templateUrl : 'views/notificationdetails.html'
	}).when('/getallnotifications',{
		controller:'NotificationCtrl'
	}).otherwise({
		templateUrl : 'views/home.html'
	})
})

app.run(function($cookieStore, $rootScope, UserService, $location) {
	if ($rootScope.user == undefined)
		$rootScope.user = $cookieStore.get('loggedInUser')// add variable to
		// the newly created
		// $rootScope object

	$rootScope.logout = function() {
		alert('logout function is called')
		UserService.logout().then(function(response) {
			delete $rootScope.user
			$cookieStore.remove('loggedInUser')
			$location.path('/login')
		}, function(response) {
			delete $rootScope.user
			$cookieStore.remove('loggedInUser')
			if (response.status == 401)
				$location.path('/login')
		})
	}

})
