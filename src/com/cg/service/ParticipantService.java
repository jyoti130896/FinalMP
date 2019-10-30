package com.cg.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.Feedback;
import com.cg.bean.Participant;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.FeedbackMasterNotExist;
import com.cg.exception.TrainingProgramNotFoundException;

public interface ParticipantService {
	static Logger myLogger =  Logger.getLogger(ParticipantService.class);
	String prs_cmmRule = "[1-5]{0,1}";
	String clrfy_doubtRule = "[1-5]{1}";
	String tmRule = "[1-5]{1}";
	String hnd_outRule = "[1-5]{1}";
	String hw_sw_ntwrkRule = "[1-5]{1}";
	String commRule="[a-z]{5,200}";
	String suggRule="[a-z]{5,200}";
	String trcodeRule="[0-9]{2,5}";
	default boolean validateprs_cmm(String  prs_cmm) {
		boolean x = prs_cmm.matches(prs_cmmRule);
		myLogger.info("feedback for presentation and communication validated");
		return x;
	}
	
	default boolean validateclrfy_doubt(String clrfy_doubt ) {
		boolean x =clrfy_doubt.matches(clrfy_doubtRule);
		myLogger.info("feedback for Clarifying doubt validated");
		return x;
	}
	
	default boolean validatetm(String tm ) {
		boolean x= tm.matches(tmRule);
		myLogger.info("feedback for Time Management validated");
		return x;
	}
	
	default boolean validatehnd_out(String hnd_out ) {
		boolean x= hnd_out.matches(hnd_outRule);
		myLogger.info("feedback for hand Out validated");
		return x;
	}
	
	 default boolean validatehw_sw_ntwrk(String hw_sw_ntwrk) {
		 boolean x= hw_sw_ntwrk.matches(hw_sw_ntwrkRule);
		 myLogger.info("feedback for hardware software and networking validated");
		return x;
	}
	default boolean validatecomment(String comm) {
		boolean x= comm.matches(commRule);
		myLogger.info("Comment validated");
		return x;	
	}
	default boolean validatesuggestion(String sugg) {
		boolean x= sugg.matches(suggRule);
		myLogger.info("Suggestion validated");
		return x;
	}
	default boolean validatetrainingcode(String trid) {
		boolean x= trid.matches(trcodeRule);
		myLogger.info("Training code validated");
		return x;
	}
	
	int saveFeedback(Feedback feed,Participant par) throws FeedbackMasterNotExist;

	List<Participant> getAllTrainingCode(int participantId,int trainingcode) throws TrainingProgramNotFoundException;

	

	

	

	
	
}
