package com.cg.bean;

public class EmployeeMaster {
	private int employeeId;
	private String employeeName;
	private String password;
	private String role;
	private String userSalt;
	
	public EmployeeMaster() {
	}
	
	public EmployeeMaster(int employeeId, String employeeName, String password, String role, String userSalt) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
		this.role = role;
		this.userSalt = userSalt;
	}

	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "EmployeeMaster [employeeId=" + employeeId + ", employeeName=" + employeeName + ", password=" + password
				+ ", role=" + role + "]";
	}
	
}
