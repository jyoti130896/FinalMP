package com.cg.bean;

public class Feedback {
	public Feedback(int trainingCode, int participantId, int presentationAndCommunication, int doubtClarification,
			int timeManagement, int handout, int hwSwNwAvailability, String comments, String suggestion) {
		super();
		this.trainingCode = trainingCode;
		this.participantId = participantId;
		this.presentationAndCommunication = presentationAndCommunication;
		this.doubtClarification = doubtClarification;
		this.timeManagement = timeManagement;
		this.handout = handout;
		this.hwSwNwAvailability = hwSwNwAvailability;
		this.comments = comments;
		this.suggestion = suggestion;
	}

	private int trainingCode;
	private int participantId;
	private int presentationAndCommunication;
	private int doubtClarification;
	private int timeManagement;
	private int handout;
	private int hwSwNwAvailability;
	private String comments;
	private String suggestion;
	
	public int getTrainingCode() {
		return trainingCode;
	}
	
	public Feedback() {
	}

	public void setTrainingCode(int trainingCode) {
		this.trainingCode = trainingCode;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public int getPresentationAndCommunication() {
		return presentationAndCommunication;
	}

	public void setPresentationAndCommunication(int presentationAndCommunication) {
		this.presentationAndCommunication = presentationAndCommunication;
	}

	public int getDoubtClarification() {
		return doubtClarification;
	}

	public void setDoubtClarification(int doubtClarification) {
		this.doubtClarification = doubtClarification;
	}

	public int getTimeManagement() {
		return timeManagement;
	}

	public void setTimeManagement(int timeManagement) {
		this.timeManagement = timeManagement;
	}

	public int getHandout() {
		return handout;
	}

	public void setHandout(int handout) {
		this.handout = handout;
	}

	public int getHwSwNwAvailability() {
		return hwSwNwAvailability;
	}

	public void setHwSwNwAvailability(int hwSwNwAvailability) {
		this.hwSwNwAvailability = hwSwNwAvailability;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public String toString() {
		return "Feedback [trainingCode=" + trainingCode + ", participantId=" + participantId
				+ ", presentationAndCommunication=" + presentationAndCommunication + ", doubtClarification="
				+ doubtClarification + ", timeManagement=" + timeManagement + ", handout=" + handout
				+ ", hwSwNwAvailability=" + hwSwNwAvailability + ", comments=" + comments + ", suggestion=" + suggestion
				+ "]";
	}	
}
