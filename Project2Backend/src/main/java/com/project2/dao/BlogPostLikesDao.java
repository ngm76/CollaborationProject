package com.project2.dao;

import org.springframework.stereotype.Service;

import com.project2.models.BlogPost;
import com.project2.models.BlogPostLikes;

@Service
public interface BlogPostLikesDao {
	BlogPostLikes hasUserLikedBlogPost(int blogPostId, String email);

	BlogPost updateLikes(int blogPostId, String email);
}
