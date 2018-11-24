package com.project2.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project2.models.BlogComment;

@Service
public interface BlogCommentDao {
	void addBlogComment(BlogComment blogComment);// insert into blogcomment table

	List<BlogComment> getAllBlogComments(int blogPostId);
	// select * from blogcomments where
}
