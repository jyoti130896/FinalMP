package com.cg.bean;

public class CourseMaster {
	private int courseId;
	private String courseName;
	private int days;
	
	public CourseMaster() {
	}
	
	public CourseMaster(int courseId, String courseName, int days) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.days = days;
	}

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	@Override
	public String toString() {
		return "Course ID: \t" + courseId + "\t, Course Name: \t" + courseName + "\t, Duration: \t" + days + " days";
	}
}
