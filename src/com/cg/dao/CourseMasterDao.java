package com.cg.dao;

import java.util.List;

import com.cg.bean.CourseMaster;
import com.cg.exception.CourseNotFoundException;
import com.cg.exception.InvalidCourseException;

public interface CourseMasterDao {
	
	String saveCourseQuery = "INSERT INTO COURSE_MASTER VALUES(courseid_seq.nextval,?,?)";
	String getCourseQuery = "SELECT * FROM COURSE_MASTER WHERE COURSE_ID=?";
	String getAllCoursesQuery = "SELECT * FROM COURSE_MASTER";
	String removeCourseQuery = "DELETE FROM COURSE_MASTER WHERE COURSE_ID=?";
	String getCourseIdQuery = "SELECT courseid_seq.currval FROM dual";
	String fetchCourseIdQuery = "SELECT * FROM COURSE_MASTER WHERE COURSE_ID=?";
	String updateCourseByIdQuery = "UPDATE COURSE_MASTER SET COURSE_NAME=?, NO_OF_DAYS=? WHERE COURSE_ID=?";
	
	int addCourse(CourseMaster course) throws InvalidCourseException;
	
	List<CourseMaster> fetchAllCourses() throws CourseNotFoundException;
	
	CourseMaster fetchCourseByCourseId(int id) throws InvalidCourseException;
	
	CourseMaster updateCourse(CourseMaster course) throws InvalidCourseException;
	
	boolean removeCourse(int id) throws InvalidCourseException;
	
	boolean courseExist(int id) throws InvalidCourseException;
}
