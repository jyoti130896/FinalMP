package com.cg.bean;

import java.sql.Date;

public class FeedbackReport {
	private Date startDate;
	private Date endDate;
	private int trainingCode;
	private String facultyName;
	private String participantName;
	private int presentationCommunication;
	private int clarifyDoubts;
	private int timeManagement;
	private int handOuts;
	private int hwswNetwork;
	
	public FeedbackReport(Date startDate, Date endDate, int trainingCode, String facultyName, String participantName,
			int presentationCommunication, int clarifyDoubts, int timeManagement, int handOuts, int hwswNetwork) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainingCode = trainingCode;
		this.facultyName = facultyName;
		this.participantName = participantName;
		this.presentationCommunication = presentationCommunication;
		this.clarifyDoubts = clarifyDoubts;
		this.timeManagement = timeManagement;
		this.handOuts = handOuts;
		this.hwswNetwork = hwswNetwork;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
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
	public int getTrainingCode() {
		return trainingCode;
	}
	public void setTrainingCode(int trainingCode) {
		this.trainingCode = trainingCode;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public int getPresentationCommunication() {
		return presentationCommunication;
	}
	public void setPresentationCommunication(int presentationCommunication) {
		this.presentationCommunication = presentationCommunication;
	}
	public int getClarifyDoubts() {
		return clarifyDoubts;
	}
	public void setClarifyDoubts(int clarifyDoubts) {
		this.clarifyDoubts = clarifyDoubts;
	}
	public int getTimeManagement() {
		return timeManagement;
	}
	public void setTimeManagement(int timeManagement) {
		this.timeManagement = timeManagement;
	}
	public int getHandOuts() {
		return handOuts;
	}
	public void setHandOuts(int handOuts) {
		this.handOuts = handOuts;
	}
	public int getHwswNetwork() {
		return hwswNetwork;
	}
	public void setHwswNetwork(int hwswNetwork) {
		this.hwswNetwork = hwswNetwork;
	}
	
	public FeedbackReport() {
	}
	@Override
	public String toString() {
		return startDate +"\t"+ endDate +"\t\t"+  trainingCode +"\t\t"+ facultyName +"\t\t"+ participantName
				+"\t\t\t"+ presentationCommunication +"\t\t"+ clarifyDoubts +"\t\t"+ timeManagement +"\t\t"+ handOuts +"\t\t"+ hwswNetwork;
	}
	
}
