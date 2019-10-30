package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.FeedbackParameters;
import com.cg.bean.FeedbackReport;
import com.cg.exception.FeedbackNotFoundException;

public class FeedbackReportDaoImpl implements FeedbackReportDao {

	@Override
	public List<FeedbackReport> byMonth(int month) throws FeedbackNotFoundException {
		Connection conn = null;
		List<FeedbackReport> feedback = new ArrayList<FeedbackReport>();
		FeedbackReport f;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(byMonth);
			stmt.setInt(1, month);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				f = new FeedbackReport();
				f.setTrainingCode(rs.getInt(1));
				f.setStartDate(rs.getDate(2));
				f.setEndDate(rs.getDate(3));
				f.setFacultyName(rs.getString(4));
				f.setParticipantName(rs.getString(5));
				f.setPresentationCommunication(rs.getInt(6));
				f.setClarifyDoubts(rs.getInt(7));
				f.setTimeManagement(rs.getInt(8));
				f.setHandOuts(rs.getInt(9));
				f.setHwswNetwork(rs.getInt(10));
				feedback.add(f);
			}
			if(feedback.size() == 0 || feedback == null) {
				throw new FeedbackNotFoundException("Feedback Not Found Exception");
			}
			return feedback;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FeedbackReport> byFacultyForMonth(int facultyCode, int month) throws FeedbackNotFoundException {
		Connection conn = null;
		List<FeedbackReport> feedback = new ArrayList<FeedbackReport>();
		FeedbackReport f;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(byFacultyAndMonth);
			stmt.setInt(1, month);
			stmt.setInt(2, facultyCode);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				f = new FeedbackReport();
				f.setTrainingCode(rs.getInt(1));
				f.setStartDate(rs.getDate(2));
				f.setEndDate(rs.getDate(3));
				f.setFacultyName(rs.getString(4));
				f.setParticipantName(rs.getString(5));
				f.setPresentationCommunication(rs.getInt(6));
				f.setClarifyDoubts(rs.getInt(7));
				f.setTimeManagement(rs.getInt(8));
				f.setHandOuts(rs.getInt(9));
				f.setHwswNetwork(rs.getInt(10));
				feedback.add(f);
			}
			if(feedback.size() == 0 || feedback == null) {
				throw new FeedbackNotFoundException("Feedback Not Found");
			}
			return feedback;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FeedbackReport> feedbackDefaultersByMonth(int month) throws FeedbackNotFoundException {
		Connection conn = null;
		List<FeedbackReport> feedback = new ArrayList<FeedbackReport>();
		FeedbackReport f;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(feedbackDefaulters);
			stmt.setInt(1, month);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				f = new FeedbackReport();
				f.setTrainingCode(rs.getInt(1));
				f.setStartDate(rs.getDate(2));
				f.setEndDate(rs.getDate(3));
				f.setFacultyName(rs.getString(4));
				f.setParticipantName(rs.getString(5));
				f.setPresentationCommunication(rs.getInt(6));
				f.setClarifyDoubts(rs.getInt(7));
				f.setTimeManagement(rs.getInt(8));
				f.setHandOuts(rs.getInt(9));
				f.setHwswNetwork(rs.getInt(10));
				feedback.add(f);
			}
			if(feedback.size() == 0 || feedback == null) {
				throw new FeedbackNotFoundException("Feedback Not Found Exception");
			}
			return feedback;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FeedbackParameters getAvgByMonth(int month) {
		Connection conn = null;
		FeedbackParameters fp = null;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(avgByMonth);
			stmt.setInt(1, month);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				fp = new FeedbackParameters();
				fp.setPresentationCommunication(rs.getDouble(1));
				fp.setClarifyDoubts(rs.getDouble(2));
				fp.setTimeManagement(rs.getDouble(3));
				fp.setHandOuts(rs.getDouble(4));
				fp.setHwswNetwork(rs.getDouble(5));
			}
				return fp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FeedbackParameters getAvgByMonthAndFaculty(int month, int facultyCode) {
		Connection conn = null;
		FeedbackParameters fp = null;
		
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(avgByFacultyAndMonth);
			stmt.setInt(1, month);
			stmt.setInt(2, facultyCode);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				fp = new FeedbackParameters();
				fp.setPresentationCommunication(rs.getDouble(1));
				fp.setClarifyDoubts(rs.getDouble(2));
				fp.setTimeManagement(rs.getDouble(3));
				fp.setHandOuts(rs.getDouble(4));
				fp.setHwswNetwork(rs.getDouble(5));
			}
			return fp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
