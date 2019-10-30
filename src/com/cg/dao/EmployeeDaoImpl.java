package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.bean.EmployeeMaster;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.ParticipantNotFoundException;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public boolean checkFaculty(int employeeid) throws FacultyDoesNotExist {
		Connection conn = null;
		EmployeeMaster employee = null;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(checkFaculty);
			stmt.setInt(1, employeeid);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacultyDoesNotExist("Faculty Not Found");
		} finally {
			try {
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean checkParticipant(int employeeId) throws ParticipantNotFoundException {
		Connection conn = null;
		EmployeeMaster employee = null;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(checkParticipant);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("check true");
				return true;
				}
			else {
				System.out.println("check false");
			return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ParticipantNotFoundException("Participant with given ID does not exist");
		} finally {
			try {
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
