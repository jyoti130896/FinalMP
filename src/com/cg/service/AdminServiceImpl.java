package com.cg.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.CourseMaster;
import com.cg.bean.Faculty;
import com.cg.dao.CourseMasterDao;
import com.cg.dao.CourseMasterDaoImpl;
import com.cg.dao.FacultyDao;
import com.cg.dao.FacultyDaoImpl;
import com.cg.exception.CourseNotFoundException;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;

public class AdminServiceImpl implements AdminService {
	static Logger myLogger =  Logger.getLogger(AdminServiceImpl.class);
	private CourseMasterDao courseDao;
	private FacultyDao facultyDao;
	
	public AdminServiceImpl() {
		courseDao = new CourseMasterDaoImpl();
		facultyDao = new FacultyDaoImpl();
	}
	
	@Override
	public int saveCourse(CourseMaster course) throws InvalidCourseException {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Validating Course>>");
		myLogger.info("Course has enrolled in course_master.");
		return courseDao.addCourse(course);
	}

	@Override
	public List<CourseMaster> getAllCourses() throws CourseNotFoundException {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("Fetching all courses from course_master.");
		return courseDao.fetchAllCourses();
	}

	@Override
	public CourseMaster getCourseByCourseId(int id) throws InvalidCourseException {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("Fetching course by course_id from course_master.");
		return courseDao.fetchCourseByCourseId(id);
	}

	@Override
	public CourseMaster updateCourse(CourseMaster course) throws InvalidCourseException {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("Updated course from course_master.");
		return courseDao.updateCourse(course);
	}

	@Override
	public boolean removeCourse(int id) throws InvalidCourseException {
		return courseDao.removeCourse(id);
	}

	@Override
	public int saveFacultySkillset(Faculty faculty) throws FacultyDoesNotExist {
		return facultyDao.addFacultySkill(faculty);
	}

}
