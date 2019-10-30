package com.cg.service;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.Participant;
import com.cg.bean.TrainingProgram;
import com.cg.dao.CourseMasterDao;
import com.cg.dao.CourseMasterDaoImpl;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.dao.FacultyDao;
import com.cg.dao.FacultyDaoImpl;
import com.cg.dao.ParticipantDao;
import com.cg.dao.ParticipantDaoImpl;
import com.cg.dao.TrainingProgramDao;
import com.cg.dao.TrainingProgramDaoImpl;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;
import com.cg.exception.ParticipantNotFoundException;
import com.cg.exception.TrainingProgramNotFoundException;

public class CoordinatorServiceImpl implements CoordinatorService {
	
	static Logger myLogger =  Logger.getLogger(CoordinatorServiceImpl.class);
	
	private TrainingProgramDao trainingProgramDao;
	private EmployeeDao employeeDao;
	private ParticipantDao participantDao;
	private CourseMasterDao courseDao;
	private FacultyDao facultyDao;
	
	public CoordinatorServiceImpl() {
		trainingProgramDao = new TrainingProgramDaoImpl();
		employeeDao = new EmployeeDaoImpl();
		participantDao = new ParticipantDaoImpl();
		facultyDao = new FacultyDaoImpl();
		courseDao = new CourseMasterDaoImpl();
	}
	
	@Override
	public List<TrainingProgram> fetchAllTrainingProgram() throws TrainingProgramNotFoundException {
		return trainingProgramDao.fetchAllTrainingProgram();
	}

	@Override
	public TrainingProgram fetchTrainingProgramByTrainingCode(int code) throws TrainingProgramNotFoundException {
		return trainingProgramDao.fetchTrainingProgramByTrainingCode(code);
	}

	@Override
	public TrainingProgram updateTrainingProgram(TrainingProgram training) throws TrainingProgramNotFoundException {
		return trainingProgramDao.updateTrainingProgram(training);
	}

	@Override
	public boolean removeTrainingProgram(int code) throws TrainingProgramNotFoundException {
		return trainingProgramDao.removeTrainingProgram(code);
	}

	@Override
	public Participant addParticipant(Participant participant) throws ParticipantNotFoundException {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Participant>>");
		
		if(employeeDao.checkParticipant(participant.getParticipantId())) {
			myLogger.info("Employee is valid participant.");
			if(!participantDao.checkIfEnrolled(participant.getTrainingcode(), participant.getParticipantId())) {
				myLogger.info("Participant has not enrolled in course.");
				java.util.Date date =  new java.util.Date();
				Date currentDate = new Date(date.getTime());

				Date courseDate = trainingProgramDao.getTrainingDate(participant.getTrainingcode());

				java.util.Date currentdate = new java.util.Date(currentDate.getTime());
				java.util.Date coursedate = new java.util.Date(courseDate.getTime());

				if(coursedate.after(currentdate)) {
					myLogger.info("The given course date is after current date.");
					participantDao.addParticipant(participant);
					return participant;
				}else {
					myLogger.error("Invalid Date.. Course date can not be greater than current date.");
					System.out.println("Invalid Date.. Course date can not be greater than current date.");
					return null;
				} 
			} else {
				myLogger.error("Participant has already enrolled in course.");
				System.out.println("Participant has already enrolled in course.");
				return null;
			}
		} else {
			myLogger.error("Employee is not a participant.");
			System.out.println("Employee is not a participant.");
			return null;
		}
	}

	@Override
	public TrainingProgram addTrainingProgram(TrainingProgram training) throws TrainingProgramNotFoundException, FacultyDoesNotExist, InvalidCourseException {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Training Program>>");

		if(courseDao.courseExist(training.getCourseCode())) {
			myLogger.info("The given course exist.");
			if(facultyDao.isFaculty(training.getFacultyCode())) {
				myLogger.info("Employee is valid faculty.");
				Date startDate = training.getStartDate();
				Date endDate = training.getEndDate();
				System.out.println(training);
				java.util.Date startdate = new java.util.Date(startDate.getTime());
				java.util.Date enddate = new java.util.Date(endDate.getTime());
				java.util.Date currdate = new java.util.Date();
				if(currdate.before(startdate))
				{
					myLogger.info("Training program start date is after current date.");
					if(startdate.before(enddate)) {
						myLogger.info("Training program end date is after start date.");
						trainingProgramDao.addTraining(training);
					} else {
						myLogger.error("Training program start date can not be greater than end date.");
						System.out.println("Start date cannot be greater than end date");
						return null;
					}
				} else {
					myLogger.error("Training program start date can not be less than current date.");
					System.out.println("Training program start date can not be less than current date.");
				}	
			} else {
				myLogger.error("Training program cannot be added. Employee is not a faculty.");
				System.out.println("Training program cannot be added. Employee is not a faculty.");
				return null;
			}
		} else {
			myLogger.error("Training program cannot be added. Course code does not exist.");
			System.out.println("Training program cannot be added. Course code does not exist.");
			return null;
		}
		return null;
	}

	@Override
	public boolean validateDate(String date) {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Date>>");
		
		boolean val =  date.matches(validateDate);
		if(val == false) {
			myLogger.error("Invalid date format provided.");
			System.out.println("Invalid date format provided.");
		}
		myLogger.info("Date validated successfully..");
		return val;
	}
	
	@Override
	public boolean validateCourseId(String courseId) {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Course Id>>");
		
		boolean val = courseId.matches(courseIdRule);
		if(val == false) {
			myLogger.error("Invalid Course Id format provided.");
			System.out.println("Invalid Course Id format provided.");
		}
		myLogger.info("Course Id validated successfully..");
		return val;
	}
		
	@Override
	public boolean validateTrainingId(String trainingId) {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Training Code>>");
		
		boolean val = trainingId.matches(trainingIdRule);
		if(val == false) {
			myLogger.error("Invalid Training Code format provided.");
			System.out.println("Invalid Training Code format provided.");
		}
		myLogger.info("Training Code validated successfully..");
		return val;
	}
	
	@Override
	public boolean validateFacultyId(String facultyid) {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Faculty Id>>");
		
		boolean val = facultyid.matches(facultyIdRule);
		if(val == false) {
			myLogger.error("Invalid Faculty Id format provided.");
			System.out.println("Invalid Faculty Id format provided.");
		}
		myLogger.info("Faculty Id validated successfully..");
		return val;
	}

}
