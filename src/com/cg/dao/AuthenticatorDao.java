package com.cg.dao;

import com.cg.bean.EmployeeMaster;

public interface AuthenticatorDao {
	
	/*
	 * Table : CREATE TABLE USERDETAILS (
	 			USERNAME VARCHAR2(20) NOT NULL,
	  			ENCPASSWORD VARCHAR(256) NOT NULL,
	 			ROLE VARCHAR2(20) NOT NULL,
	  			USERSALT VARCHAR2(256) NOT NULL);
	  			
	  			CREATE TABLE EMPLOYEE_MASTER
				(
				EMPLOYEE_ID NUMBER(5) PRIMARY KEY,
				EMPLOYEE_NAME VARCHAR(50) NOT NULL,
				ENCPASSWORD VARCHAR(256) NOT NULL,
				ROLE VARCHAR(20) NOT NULL,
				USERSALT VARCHAR2(256) NOT NULL
				);
	 */
	String save = "insert into employee_master values (?,?,?,?,?)";
	String get = "select * from employee_master where employee_id = ?";
	
	
	boolean addUser(EmployeeMaster employee);
	
	EmployeeMaster getInfo(int employeeId);
	
}
