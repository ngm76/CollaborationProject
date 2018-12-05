package com.project2;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.project2.dao.ProfilePictureDao;
import com.project2.models.ErrorClass;
import com.project2.models.ProfilePicture;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;

	@RequestMapping(value = "/uploadprofilepic", method = RequestMethod.POST)
	public ResponseEntity<?> saveOrUpdateProfilePicture(@RequestParam CommonsMultipartFile image, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			ErrorClass errorClass = new ErrorClass(5, "Unauthorized access.. please login");
			return new ResponseEntity<ErrorClass>(errorClass, HttpStatus.UNAUTHORIZED);
		}
		ProfilePicture profilePicture = new ProfilePicture();
		profilePicture.setEmail(email);
		profilePicture.setImage(image.getBytes());
		profilePictureDao.saveOrUpdateProfilePic(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture, HttpStatus.OK);
	}

	// logged in user -> john.s@niit.com
	// in blogpost , author of the blogpost "smith.s@niit.com"
	// url to get profilepic of john.s@niit.com <img
	// src=".../getimage?email='john.s@niit.com"
	// url to get profilepic of smith.s@niit.com <img
	// src=".../getimage?email='smith.s@niit.com"
	// url <img src
	@RequestMapping(value = "/getimage/{email}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable String email, HttpSession session) {
		String auth = (String) session.getAttribute("email");
		if (auth == null)
			return null;// src attribute will get null value, no image
		ProfilePicture profilePicture = profilePictureDao.getImage(auth);
		if (profilePicture == null)
			return null;
		else
			return profilePicture.getImage();// this data will be return to src attribute of img tag

	}
}
