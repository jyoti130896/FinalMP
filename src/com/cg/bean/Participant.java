package com.cg.bean;

public class Participant {
	private int trainingcode;
	private int participantId;
	
	public Participant() {
	}
	
	public Participant(int trainingcode, int participantId) {
		this.trainingcode = trainingcode;
		this.participantId = participantId;
	}

	public int getTrainingcode() {
		return trainingcode;
	}
	public void setTrainingcode(int trainingcode) {
		this.trainingcode = trainingcode;
	}
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	@Override
	public String toString() {
		return "Participant [trainingcode=" + trainingcode + ", participantId=" + participantId + "]";
	}
	
}
