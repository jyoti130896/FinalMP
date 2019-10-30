package com.cg.daotesting;


import java.sql.Date;

import com.cg.bean.CourseMaster;
import com.cg.bean.Faculty;
import com.cg.bean.Feedback;
import com.cg.bean.Participant;
import com.cg.bean.TrainingProgram;
import com.cg.dao.CourseMasterDaoImpl;
import com.cg.dao.FacultyDaoImpl;
import com.cg.dao.FeedbackDaoImpl;
import com.cg.dao.ParticipantDaoImpl;
import com.cg.dao.TrainingProgramDaoImpl;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;
import com.cg.exception.ParticipantNotFoundException;
import com.cg.exception.TrainingProgramNotFoundException;

public class Testing {

	public static void main(String[] args) {
		Faculty kasambe = new Faculty(1000, "MEMS, ICT");
		FacultyDaoImpl facultyDao = new FacultyDaoImpl();
		try {
			facultyDao.addFacultySkill(kasambe);
		} catch (FacultyDoesNotExist e) {
			e.printStackTrace();
		}
		
		CourseMaster mems = new CourseMaster(0, "MEMS", 41);
		CourseMasterDaoImpl courseDao = new CourseMasterDaoImpl();
		try {
			courseDao.addCourse(mems);
		} catch (InvalidCourseException e) {
			e.printStackTrace();
		}
		
		Date start = new Date(2015, 7, 25);
		Date end = new Date(2017, 7, 25);
		System.out.println(start);
		System.out.println(end);
		TrainingProgram trainingProgram = new TrainingProgram(0, 2022, 3000, start, end);
		TrainingProgramDaoImpl trainingDao = new TrainingProgramDaoImpl();
		try {
			trainingDao.addTraining(trainingProgram);
		} catch (TrainingProgramNotFoundException e) {
			e.printStackTrace();
		}
		
		Participant participant = new Participant(1001, 2000);
		ParticipantDaoImpl daoImpl = new ParticipantDaoImpl();
		try {
			daoImpl.addParticipant(participant);
		} catch (ParticipantNotFoundException e) {
			e.printStackTrace();
		}
		
//		Feedback fb = new Feedback(1001, 2000, 5, 5, 5, 5, 5);
//		FeedbackDaoImpl fbDaoImpl = new FeedbackDaoImpl();
//		try {
//			fbDaoImpl.persistFeedback(fb, participant);
//		} catch (TrainingProgramNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Participant participant = new Participant(200,2000);
//		Date start = new Date(2015, 7, 25);
//		Date end = new Date(2017, 7, 25);
//		
//		TrainingProgram trainingProgram = new TrainingProgram(200,20,1000,start, end);
//		TrainingProgramDaoImpl trainingDao = new TrainingProgramDaoImpl();
//		try {
//			trainingDao.addTraining(trainingProgram);
//		} catch (TrainingProgramNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		ParticipantDaoImpl daoImpl = new ParticipantDaoImpl();
//		try {
//			daoImpl.addParticipant(participant);
//		} catch (ParticipantNotFoundException e) {
//			e.printStackTrace();
//		}
	}

}
