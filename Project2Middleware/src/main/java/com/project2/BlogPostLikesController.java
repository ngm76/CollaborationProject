package com.project2;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project2.dao.BlogPostLikesDao;
import com.project2.models.BlogPost;
import com.project2.models.BlogPostLikes;
import com.project2.models.ErrorClass;

@Controller
public class BlogPostLikesController {
	@Autowired
	private BlogPostLikesDao blogPostLikesDao;

	public BlogPostLikesController() {
		System.out.println("BlogPostLikesController instantiated");
	}

	@RequestMapping(value = "/hasUserLikedBlogPost/{blogPostId}", method = RequestMethod.GET)
	public ResponseEntity<?> hasUserLikedBlogPost(@PathVariable int blogPostId, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			ErrorClass errorClass = new ErrorClass(5, "Unauthorized access.. please login");
			return new ResponseEntity<ErrorClass>(errorClass, HttpStatus.UNAUTHORIZED);
		}
		BlogPostLikes blogPostLikes = blogPostLikesDao.hasUserLikedBlogPost(blogPostId, email);
		return new ResponseEntity<BlogPostLikes>(blogPostLikes, HttpStatus.OK);
	}

	@RequestMapping(value = "/updatelikes/{blogPostId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLikes(@PathVariable int blogPostId, HttpSession session) {
		System.out.println("UPDATELIKES " + blogPostId);
		String email = (String) session.getAttribute("email");
		if (email == null) {
			ErrorClass errorClass = new ErrorClass(5, "Unauthorized access.. please login");
			return new ResponseEntity<ErrorClass>(errorClass, HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost = blogPostLikesDao.updateLikes(blogPostId, email);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

}
