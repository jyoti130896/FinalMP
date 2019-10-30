package com.cg.service;

import java.util.List;

import com.cg.bean.Participant;
import com.cg.bean.TrainingProgram;
import com.cg.dao.ParticipantDao;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;
import com.cg.exception.ParticipantNotFoundException;
import com.cg.exception.TrainingProgramNotFoundException;

public interface CoordinatorService {
	
	String validateDate = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
	String facultyIdRule = "[0-9]{1,}";
	String trainingIdRule = "[0-9]{1,}";
	String courseIdRule = "[0-9]{1,}";
	
	List<TrainingProgram> fetchAllTrainingProgram() throws TrainingProgramNotFoundException;
	
	TrainingProgram fetchTrainingProgramByTrainingCode(int code) throws TrainingProgramNotFoundException;
	
	TrainingProgram updateTrainingProgram(TrainingProgram training) throws TrainingProgramNotFoundException;
	
	boolean removeTrainingProgram(int code) throws TrainingProgramNotFoundException;
	
	Participant addParticipant(Participant participant) throws ParticipantNotFoundException;
	// check whether available & check role in employee master
	// query : select * from employee_master where employee_id = ? and role = ?
	// for given training code not already enrolled
	// query: select * from training_participant_master where training_code = ? and participant_id = ?
	// for given training check whether available and upcoming date mai hai
	// query : select start_date from training_master where training_code = ?
	// Then, add...
	// query: insert into training_participant_master values (?, ?)
	
	TrainingProgram addTrainingProgram(TrainingProgram training) throws TrainingProgramNotFoundException, FacultyDoesNotExist, InvalidCourseException;
	// first getcourse_code match course table if exist
	// get course: select * from course_master where course_id = ?;
	// check for faculty code exist and role is faculty
	// query : select * from faculty_master where faculty_id = ? and role = ?
	// then add : insert into 	training_master values(?, ?, ?, ? ,?)
	// for service ONLY -->> 
	// date validation : date should be valid, end date should be greater than start date
	// current date se jyada dono
	
	boolean validateDate(String date);

	boolean validateFacultyId(String facultyid);

	boolean validateTrainingId(String trainingId);
	
	boolean validateCourseId(String courseId);
}
