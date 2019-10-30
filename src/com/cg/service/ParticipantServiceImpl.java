package com.cg.service;


import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.Feedback;
import com.cg.bean.Participant;
import com.cg.dao.FeedbackDao;
import com.cg.dao.FeedbackDaoImpl;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.FeedbackMasterNotExist;
import com.cg.exception.TrainingProgramNotFoundException;


public class ParticipantServiceImpl implements ParticipantService {
	
	private FeedbackDao dao;
	
	public ParticipantServiceImpl() {
		dao = new FeedbackDaoImpl();
	}

	@Override
	public int saveFeedback(Feedback feed,Participant par) throws FeedbackMasterNotExist{
		return dao.persistFeedback(feed,par);
	
	}

	@Override
	public List<Participant> getAllTrainingCode(int participantId,int trainingcode) throws TrainingProgramNotFoundException {
		return dao.fetchAllTrainingCode(participantId,trainingcode);
	}
	
	

}
