/**
 * JobService
 */

app.factory('JobService', function($http) {
	var jobService = {}

	console.log("JobService");
	
	jobService.addJob = function(job) {
		return $http.post("http://localhost:8097/Project2Middleware/addjob",
				job)
	}

	jobService.getAllJobs = function() {
		alert('inside getalljobs service')
		return $http.get("http://localhost:8097/Project2Middleware/listofjobs")
	}

	return jobService;
})