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
	}).when('/listofjobs', {
		controller : 'JobCtrl',
		templateUrl : 'views/listofjobs.html'
	}).otherwise({
		templateUrl : 'views/index.html'
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
