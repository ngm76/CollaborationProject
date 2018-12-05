package com.project2.testcases;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project2.configuration.DBConfiguration;
import com.project2.dao.JobDao;
import com.project2.dao.JobDaoImpl;
import com.project2.models.Job;

import junit.framework.TestCase;

public class JobDaoTestCase {
	
static JobDao jobDao;	
	
@BeforeClass
public static void setUp() {	
ApplicationContext context = new AnnotationConfigApplicationContext(DBConfiguration.class,JobDaoImpl.class);
jobDao = (JobDao) context.getBean("jobDaoImpl");
}
@Test
public void testAddJob() {
	Job job = new Job();
	job.setJobTitle("Software Developer");
	job.setJobDescription("Software Developer Job for freshers");
	job.setLocation("Pune");
	job.setPostedOn(new Date());
	job.setSalary("30000");
	job.setSkillsRequired("AngularJS");
	job.setYrsOfExp("0");
	job.setCompanyName("ABC");
	
	jobDao.addJob(job);
	assertTrue(job.getId()>0);
}

@Test
public void testGetAllJobs() {
	List<Job> jobs = jobDao.getAllJobs();
	assertTrue(jobs.size()>0);
	
}

}
