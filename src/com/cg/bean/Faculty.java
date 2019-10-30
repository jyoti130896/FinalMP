package com.cg.bean;

import java.util.List;

public class Faculty {
	private int facultyId;
	private String skillSet;
	
	public Faculty() {
	}
	
	public Faculty(int facultyId, String skillSet) {
		super();
		this.facultyId = facultyId;
		this.skillSet = skillSet;
	}


	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", skillSet=" + skillSet + "]";
	}
	
	
	
}
