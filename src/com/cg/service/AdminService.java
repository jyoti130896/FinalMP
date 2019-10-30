package com.cg.service;

import java.util.List;

import com.cg.bean.CourseMaster;
import com.cg.bean.Faculty;
import com.cg.exception.CourseNotFoundException;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;

public interface AdminService {

	String courseIdRule = "[0-9]{1,}";
	String nameRule = "[A-Z][a-z]{1,}";
	String daysRule = "[0-9]{1,}";
	String facultyIdRule = "[0-9]{1,}";
	String skillsetRule = "[A-Za-z]{1,}";
	
	default boolean validateCourseId(String courseId) {
		return courseId.matches(courseIdRule);
	}
	
	default boolean validateName(String courseName) {
		return courseName.matches(nameRule);
	}
	
	default boolean validateDays(String courseDays) {
		return courseDays.matches(daysRule);
	}
	
	default boolean validateFacultyId(String facultyId) {
		return facultyId.matches(facultyIdRule);
	}
	
	default boolean validateFacultySkillset(String skillset) {
		return skillset.matches(skillsetRule);
	}
	
	
	//BL methods
	int saveCourse(CourseMaster course) throws InvalidCourseException;
	
	List<CourseMaster> getAllCourses() throws CourseNotFoundException;
	
	CourseMaster getCourseByCourseId(int id) throws InvalidCourseException;
	
	CourseMaster updateCourse(CourseMaster course) throws InvalidCourseException;
	
	boolean removeCourse(int id) throws InvalidCourseException;
	
	int saveFacultySkillset(Faculty faculty) throws FacultyDoesNotExist;

}
