package com.project2.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project2.models.Job;

@Service
public interface JobDao {
	void addJob(Job job);
	List<Job> getAllJobs();
}
