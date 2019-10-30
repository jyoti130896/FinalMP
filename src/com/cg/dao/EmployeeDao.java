package com.cg.dao;

import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.ParticipantNotFoundException;

public interface EmployeeDao {
	
	String checkFaculty = "SELECT * FROM EMPLOYEE_MASTER WHERE EMPLOYEE_ID = ? AND ROLE = faculty";
	String checkParticipant = "SELECT * FROM EMPLOYEE_MASTER WHERE EMPLOYEE_ID = ? AND ROLE = 'Participant'";
	
	boolean checkFaculty(int employeeid) throws FacultyDoesNotExist;
	
	boolean checkParticipant(int employeeId) throws ParticipantNotFoundException;
}
