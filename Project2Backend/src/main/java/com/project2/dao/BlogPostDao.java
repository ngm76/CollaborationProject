package com.project2.dao;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project2.models.BlogPost;

@Service
public interface BlogPostDao {
	void  addBlogPost(BlogPost blogPost);
	List<BlogPost> blogsWaitingForApproval();
	List<BlogPost> blogsApproved();
	BlogPost getBlog(int blogPostId);
	void updateBlogPost(BlogPost blogPost);
	void deleteBlogPost(BlogPost blogPost);
}
