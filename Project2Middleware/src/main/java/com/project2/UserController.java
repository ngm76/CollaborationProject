package com.project2;

import javax.servlet.http.HttpSession;

import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project2.dao.UserDao;
import com.project2.models.ErrorClass;
import com.project2.models.User;
@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	public UserController() {
		System.out.println("UserController bean is instantiated ");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user){
		
		//check email is unique
		if(!userDao.isEmailUnique(user.getEmail())) {
			ErrorClass errorClass = new ErrorClass(2,"Email already exists..Please choose some different email id");
			return new ResponseEntity<ErrorClass>(errorClass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			userDao.RegisterUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e) {
			//constraint violation
			ErrorClass errorClass = new ErrorClass(1,"Unable to register user details"+e.getMessage());
			return new ResponseEntity<ErrorClass>(errorClass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//i/p is user= {email:'adam.e@niit.com',password:'123'}
	//o/p validUser={email:'adam.e@niit.com',password:'123',firstname:'Adam',lastname:'E',.....}
	@RequestMapping(value="/login",method=RequestMethod.PUT)
	public ResponseEntity<?> login(@RequestBody User user , HttpSession session){
		User validUser = userDao.login(user);
		if(validUser == null) {
			ErrorClass errorClass = new ErrorClass(3,"Invalid email id/password..please enter valid credentials");
			return new ResponseEntity<ErrorClass>(errorClass,HttpStatus.UNAUTHORIZED);
		}else {
			validUser.setOnline(true);
			userDao.updateUser(validUser);
			System.out.println("Session Id is "+session.getId());
			System.out.println("Session Creation Time is "+session.getCreationTime());
			session.setAttribute("email", validUser.getEmail());
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		String email = (String)session.getAttribute("email");
		if(email == null) {//user not logged in
			ErrorClass errorClass = new ErrorClass(5,"Please login");
			return new ResponseEntity<ErrorClass>(errorClass,HttpStatus.UNAUTHORIZED);
			
		}
		//update online status to false
		//remove session attribute "email"
		//invalidate the session
		//return success status code
		
		User user = userDao.getUser(email);
		user.setOnline(false);
		userDao.updateUser(user);//update online_status to false
		session.removeAttribute("email");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
