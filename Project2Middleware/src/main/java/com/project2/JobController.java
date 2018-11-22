package com.project2;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project2.dao.JobDao;
import com.project2.dao.UserDao;
import com.project2.models.ErrorClass;
import com.project2.models.Job;

@Controller
public class JobController {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody Job job , HttpSession session){
		//Authentication and Authorization
		
		String email=(String) session.getAttribute("email");
		//to check if the user is loggedIn
		System.out.println("Session Id in addJob() is "+session.getId());
		System.out.println("Session Attribute email in addJob() is "+session.getAttribute("email"));
		if(email == null) {
			ErrorClass errorClass = new ErrorClass(5,"Unauthorized access.please login");
			return new ResponseEntity<ErrorClass>(errorClass , HttpStatus.UNAUTHORIZED);
			
		}
		
		//user is authenticated , check for authorization[i.e if the role of the user is ADMIN]
	
	try {
		System.out.println(new Date());
		job.setPostedOn(new Date());
		jobDao.addJob(job);
	}catch(Exception e) {
		ErrorClass errorClass = new ErrorClass(7,"Unable to insert job details "+e.getMessage());
		return new ResponseEntity<ErrorClass>(errorClass , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="/listofjobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		System.out.println("getalljobs called");
		String email=(String) session.getAttribute("email");
		if(email == null) {
			ErrorClass errorClass = new ErrorClass(5,"Unauthorized access,please login");
			return new ResponseEntity<ErrorClass>(errorClass,HttpStatus.UNAUTHORIZED);
		}
		
		List<Job> jobs = jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		
	}
	
	
}
