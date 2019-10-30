package com.cg.daotesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Feedback;
import com.cg.bean.Participant;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.FeedbackMasterNotExist;
import com.cg.exception.TrainingProgramNotFoundException;
import com.cg.service.ParticipantService;
import com.cg.service.ParticipantServiceImpl;

public class FeedbackDaoTesting {
 private ParticipantService service;
 
 @Before
 public void init(){
	 service=new ParticipantServiceImpl();
 }
 @After
 public void release() {
	 service=null;
 }
@Test
public void matchIdCode(){
	List<Participant> par=null;
		try {
			par = service.getAllTrainingCode(101,201);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Tarining programme not matched with Participant Id");
		}
		assertNotNull(par);
	}
	
@Test
public void rejectIdCode()  {
	List<Participant> par=null;
			try {
				par = service.getAllTrainingCode(101,202);
			} catch (TrainingProgramNotFoundException e) {
				System.out.println("Tarining programme not matched with Participant Id");
			}
		    assertEquals(0,par.size());
	}

@Test
public void addFeedback() {
	Participant par=new Participant(201,101);
	Feedback feed=new Feedback(201,101,5,4,4,3,2,"commenets","Suggestions");
	int i=1;
	try {
		 i=service.saveFeedback(feed, par);
	} catch (FeedbackMasterNotExist e) {
		System.out.println("Feedback not inserted");
		e.printStackTrace();
	}
	assertEquals(0,i);
}
@Test
public void addAnotherFeedback() {
	Participant par=new Participant(302,101);
	Feedback feed=new Feedback(202,101,5,4,4,3,2,"comments","Suggestions");
	int i=1;
	try {
		 i=service.saveFeedback(feed, par);
		 System.out.println("Feedback Inserted");
	} catch (FeedbackMasterNotExist e) {
		System.out.println("Feedback not inserted");
	}
	assertNotEquals(0,i);
}


	
}

 