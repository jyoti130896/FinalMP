package com.cg.bean;

import java.sql.Date;

public class TrainingProgram {
	private int trainingCode;
	private int courseCode;
	private int facultyCode;
	private Date startDate;
	private Date endDate;
	
	public TrainingProgram() {
	}
	
	public TrainingProgram(int trainingCode, int courseCode, int facultyCode, Date startDate, Date endDate) {
		super();
		this.trainingCode = trainingCode;
		this.courseCode = courseCode;
		this.facultyCode = facultyCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getTrainingCode() {
		return trainingCode;
	}
	public void setTrainingCode(int trainingCode) {
		this.trainingCode = trainingCode;
	}
	public int getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	public int getFacultyCode() {
		return facultyCode;
	}
	public void setFacultyCode(int facultyCode) {
		this.facultyCode = facultyCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Training Code: " + trainingCode + " Course Code: " + courseCode + " Faculty Code: "
				+ facultyCode + " Start Date: " + startDate + " End Date: " + endDate ;
	}	
}
