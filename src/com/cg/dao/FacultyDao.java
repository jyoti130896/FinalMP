package com.cg.dao;

import com.cg.bean.Faculty;

import com.cg.exception.FacultyDoesNotExist;

public interface FacultyDao {

	String saveFacultySkillsetQuery = "INSERT INTO faculty_skill VALUES(?,?)";
	String updateFacultySkillsetQuery = "UPDATE faculty_skill SET skill_set=? WHERE faculty_id=?";
	String fetchFacultyIdQuery = "SELECT * FROM employee_master WHERE employee_id=?";
	String fetchPreviousSkillsetQuery = "SELECT * FROM faculty_skill WHERE faculty_id=?";
	
	int addFacultySkill(Faculty faculty) throws FacultyDoesNotExist;

	boolean isFaculty(int id) throws FacultyDoesNotExist;
}
