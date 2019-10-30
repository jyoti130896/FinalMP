package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.bean.EmployeeMaster;

public class AuthenticatorDaoImpl implements AuthenticatorDao{
	static Logger myLogger =  Logger.getLogger(AuthenticatorDaoImpl.class);

	@Override
	public boolean addUser(EmployeeMaster employee) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database for adding new employee");
			PreparedStatement stmt = conn.prepareStatement(save);
			stmt.setInt(1, employee.getEmployeeId());
			stmt.setString(2, employee.getEmployeeName());
			stmt.setString(3, employee.getPassword());
			stmt.setString(4, employee.getRole());
			stmt.setString(5, employee.getUserSalt());		
			int update = stmt.executeUpdate();
			
			if(update > 0) {
				myLogger.info("New employee added with employee id: "+employee.getEmployeeId());
				return true;
				}
			else { 
				myLogger.error("Failure to add new employee");
				return false;
			}
		} catch (SQLException e) {
			myLogger.error("Failure to add new training program");
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
					myLogger.info("Database connection closed and resources released");	
					}
			} catch (SQLException e) {
				myLogger.error("Unable to close connection and release resources");
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public EmployeeMaster getInfo(int employeeId) {
		Connection conn = null;
		EmployeeMaster employee = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database for getting employee information");
			PreparedStatement stmt = conn.prepareStatement(get);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				employee = new EmployeeMaster();
				employee.setEmployeeId(rs.getInt(1));
				employee.setEmployeeName(rs.getString(2));
				employee.setPassword(rs.getString(3));
				employee.setRole(rs.getString(4));
				employee.setUserSalt(rs.getString(5));
			}
			else {
				myLogger.error("Unable to find employee with employee id: "+employeeId);
				System.out.println("Unable to find employee with employee id: "+employeeId); 
				return null;
			}
			myLogger.info("Employee found with employee id: "+employeeId);
			return employee;
		} catch (SQLException e) {
			myLogger.error("Failed to perform fetch operation");
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) { 
					conn.close();
					myLogger.info("Database connection closed and resources released");		
				}
			} catch (SQLException e) {
				myLogger.error("Unable to close connection and release resources");
				e.printStackTrace();
			}
		}
		return null;
	}
}
