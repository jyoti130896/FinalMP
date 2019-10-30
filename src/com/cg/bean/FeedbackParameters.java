package com.cg.bean;

public class FeedbackParameters {
	private double presentationCommunication;
	private double clarifyDoubts;
	private double timeManagement;
	private double handOuts;
	private double hwswNetwork;
	
	public FeedbackParameters() {
	}

	public FeedbackParameters(double presentationCommunication, double clarifyDoubts, double timeManagement,
			double handOuts, double hwswNetwork) {
		super();
		this.presentationCommunication = presentationCommunication;
		this.clarifyDoubts = clarifyDoubts;
		this.timeManagement = timeManagement;
		this.handOuts = handOuts;
		this.hwswNetwork = hwswNetwork;
	}



	public double getPresentationCommunication() {
		return presentationCommunication;
	}

	public void setPresentationCommunication(double presentationCommunication) {
		this.presentationCommunication = presentationCommunication;
	}

	public double getClarifyDoubts() {
		return clarifyDoubts;
	}

	public void setClarifyDoubts(double clarifyDoubts) {
		this.clarifyDoubts = clarifyDoubts;
	}

	public double getTimeManagement() {
		return timeManagement;
	}

	public void setTimeManagement(double timeManagement) {
		this.timeManagement = timeManagement;
	}

	public double getHandOuts() {
		return handOuts;
	}

	public void setHandOuts(double handOuts) {
		this.handOuts = handOuts;
	}

	public double getHwswNetwork() {
		return hwswNetwork;
	}

	public void setHwswNetwork(double hwswNetwork) {
		this.hwswNetwork = hwswNetwork;
	}

	@Override
	public String toString() {
		return "Average Scores: Presentation & Communication=" + presentationCommunication + ", Clarification of Doubts="
				+ clarifyDoubts + ", Time Management=" + timeManagement + ", Handouts=" + handOuts + ", H/W S/W Network="
				+ hwswNetwork ;
	}
	
	
	
}
