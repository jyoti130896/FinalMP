package com.cg.dao;

import java.sql.Connection;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.bean.Faculty;
import com.cg.exception.FacultyDoesNotExist;

public class FacultyDaoImpl implements FacultyDao {
	Logger myLogger =  Logger.getLogger(FacultyDaoImpl.class);
	@Override
	public int addFacultySkill(Faculty faculty) throws FacultyDoesNotExist {
		
		
		Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database to add Faculty skill from database");
			PreparedStatement stmt = conn.prepareStatement(fetchFacultyIdQuery);
			stmt.setInt(1, faculty.getFacultyId());
			ResultSet result = stmt.executeQuery();
			
			PreparedStatement stmt2 = conn.prepareStatement(fetchPreviousSkillsetQuery);
			stmt2.setInt(1, faculty.getFacultyId());
			ResultSet rs = stmt2.executeQuery();
			myLogger.info("New Faculty skill added with faculty id: "+faculty.getFacultyId());
			
			if(!result.next()) {
				System.out.println("no fetch query result");
				myLogger.error("Faculty id not exist");
				throw new FacultyDoesNotExist("Faculty id does not exist..");
				
			} else if(!rs.next()){
				PreparedStatement stmt1 = conn.prepareStatement(saveFacultySkillsetQuery);
				stmt1.setInt(1, faculty.getFacultyId());
				stmt1.setString(2, faculty.getSkillSet());
				stmt1.executeUpdate();
				myLogger.info("Faculty Skillset saved");
			} else {
					PreparedStatement stmt3 = conn.prepareStatement(updateFacultySkillsetQuery);
					stmt3.setInt(2, faculty.getFacultyId());
					
					String newSkillset = null, delimiter = " , ";
					String previousSkillset = rs.getString(2);
					System.out.println("previous skillset: " + previousSkillset);
					previousSkillset = previousSkillset.concat(delimiter);
					newSkillset = previousSkillset.concat(faculty.getSkillSet());
					stmt3.setString(1, newSkillset);
					stmt3.executeUpdate();
					myLogger.info("faculty skill updated");
					}
				return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Faculty does not exist");
			throw new FacultyDoesNotExist(e.getMessage());
			
		} finally {
			if(conn != null)
			try {
					conn.close();
					myLogger.info("Database connection closed and resources released");
			} catch (SQLException e) {
				e.printStackTrace();
				myLogger.error("Unable to close connection and release resources");
			}
		}

	}

	@Override
	public boolean isFaculty(int id) throws FacultyDoesNotExist {
		
Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database");
			PreparedStatement stmt2 = conn.prepareStatement(fetchPreviousSkillsetQuery);
			stmt2.setInt(1, id);
			ResultSet rs = stmt2.executeQuery();
			if(rs.next()) {
				myLogger.info("Faculty exists with id"+id);
				return true;
			} else {
				myLogger.error("No faculty exists");
				return false;
			}
		} catch (SQLException e) {
			myLogger.error("No faculty exists");
			throw new FacultyDoesNotExist(e.getMessage());
		} finally {
			if(conn != null)
			try {
					conn.close();
					myLogger.info("Database connection closed and resources released");	
			} catch (SQLException e) {
				e.printStackTrace();
				myLogger.error("Unable to close connection and release resources");
			}
		}
	}

}
