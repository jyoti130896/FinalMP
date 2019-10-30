package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.CourseMaster;
import com.cg.exception.CourseNotFoundException;
import com.cg.exception.InvalidCourseException;
import com.cg.service.AuthenticatorServiceImpl;

public class CourseMasterDaoImpl implements CourseMasterDao {
	static Logger myLogger =  Logger.getLogger(CourseMasterDaoImpl.class);
	@Override
	public int addCourse(CourseMaster course) throws InvalidCourseException {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database for adding new course");
			PreparedStatement stmt = conn.prepareStatement(saveCourseQuery);
			//stmt.setInt(1, course.getCourseId());
			stmt.setString(1, course.getCourseName());
			stmt.setInt(2, course.getDays());
			
			stmt.executeUpdate();
			ResultSet result = conn.createStatement().executeQuery(getCourseIdQuery);
			myLogger.info("New Course program added with course code: "+course.getCourseId());
			if(result.next()) {
				return result.getInt(1);
			}
			else 
				return 0;
		} catch (SQLException e) {
			myLogger.error("Failure to add new Course");
			throw new InvalidCourseException(e.getMessage());
		} finally {
			if(conn != null)
				try {
					conn.close();
					myLogger.info("Database connection closed and resources released");	
				} catch (SQLException e) {
					myLogger.error("Unable to close connection and release resources");
					e.printStackTrace();
				}
		}
	}

	@Override
	public List<CourseMaster> fetchAllCourses() throws CourseNotFoundException {
		List<CourseMaster> courses = new ArrayList<CourseMaster>();
		Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database");
			PreparedStatement stmt = conn.prepareStatement(getAllCoursesQuery);
			ResultSet result = stmt.executeQuery();
			myLogger.info("Fetching all courses");
			while(result.next()) {
				CourseMaster course = new CourseMaster();
				course.setCourseId(result.getInt(1));
				course.setCourseName(result.getString(2));
				course.setDays(result.getInt(3));
				courses.add(course);
			}
			if(courses.size() == 0)
				throw new CourseNotFoundException();
			return courses;
		} catch (SQLException e) {
			myLogger.error("Failure to fetch courses");
			throw new CourseNotFoundException(e.getMessage());
		} finally {
			try {
				if(conn != null)
					conn.close();
				myLogger.info("Database connection closed and resources released");	
			} catch (SQLException e) {
				e.printStackTrace();
				myLogger.error("Unable to close connection");	
			}
		}
		
	}

	@Override
	public CourseMaster fetchCourseByCourseId(int id) throws InvalidCourseException {
		myLogger.info("Connection successfully established with the database for fetching course from database");
		Connection conn = null;
		CourseMaster course = null;

		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(getCourseQuery);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if(!result.next()) {
				myLogger.error("No course found with course code: "+id);
				throw new InvalidCourseException("Course does not exist..");
			}
			else {
				course = new CourseMaster();
				course.setCourseId(result.getInt(1));
				course.setCourseName(result.getString(2));
				course.setDays(result.getInt(3));
				myLogger.info("Course fetched successfully for course code: "+id);
			}
				
			return course;
		} catch (SQLException e) {
			myLogger.error("Failure to fetch course");
			throw new InvalidCourseException(e.getMessage());
		} finally {
			try {
				if(conn != null)
					conn.close();
				myLogger.info("Database connection closed and resources released");	
			} catch (SQLException e) {
				myLogger.error("Unable to close connection and release resources");
				e.printStackTrace();
			}
		}
	}

	@Override
	public CourseMaster updateCourse(CourseMaster course) throws InvalidCourseException {
		Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established from database");
			PreparedStatement stmt1 = conn.prepareStatement(fetchCourseIdQuery);
			stmt1.setInt(1, course.getCourseId());
			ResultSet result = stmt1.executeQuery();
			if(!result.next()) {
				myLogger.error("Course can not be updated.. course not found with course code: "+course.getCourseId());
				throw new InvalidCourseException("Course details can not be updated.. course id not found.");
			}
			else {
				PreparedStatement stmt2 = conn.prepareStatement(updateCourseByIdQuery);
				stmt2.setString(1, course.getCourseName());
				stmt2.setInt(2, course.getDays());
				stmt2.setInt(3, course.getCourseId());
				stmt2.executeUpdate();
				stmt2.execute();
				myLogger.info("Course program successfully updated for course id: "+course.getCourseId());
			}
			return course;
		} catch (SQLException e) {
			myLogger.error("Failure to update Course program");
			throw new InvalidCourseException(e.getMessage());
		} finally {
			try {
				if(conn != null)
					conn.close();
				myLogger.info("Database connection closed and resources released");
			} catch (SQLException e) {
				e.printStackTrace();
				myLogger.error("Unable to close connection and release resources");
			}
		}
	}

	@Override
	public boolean removeCourse(int id) throws InvalidCourseException {
		Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database to remove course from database");
			PreparedStatement stmt1 = conn.prepareStatement(fetchCourseIdQuery);
			stmt1.setInt(1, id);
			ResultSet result = stmt1.executeQuery();
			if(!result.next())
				throw new InvalidCourseException("Course details can not be deleted.. course id not found.");
			else {
				PreparedStatement stmt = conn.prepareStatement(removeCourseQuery);
				stmt.setInt(1, id);
				stmt.executeUpdate();
				myLogger.info("Course program successfully removed for course code: "+id);
				return true;
			}
			
		} catch (SQLException e) {
			throw new InvalidCourseException(e.getMessage());
		} finally {
			try {
				if(conn != null)
					conn.close();
				myLogger.info("Database connection closed and resources released");	
			} catch (SQLException e) {
				myLogger.error("Unable to close connection and release resources");
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean courseExist(int id) throws InvalidCourseException {
		Connection conn = null;
		myLogger.info("Connection successfully established with the database");
		CourseMaster course = null;

		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(getCourseQuery);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				myLogger.info("Is a existing course");
				return true;
			} else {
				myLogger.error("Isn't an existing course");
				return false;
			}
				
		} catch (SQLException e) {
			throw new InvalidCourseException(e.getMessage());
		} finally {
			try {
				if(conn != null) {
					conn.close();
				myLogger.info("Database connection closed and resources released");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				myLogger.error("Unable to close connection and release resources");
			}
		}
	}
}
