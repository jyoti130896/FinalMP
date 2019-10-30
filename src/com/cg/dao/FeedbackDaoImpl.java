package com.cg.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.Feedback;
import com.cg.bean.Participant;
import com.cg.exception.FeedbackMasterNotExist;
import com.cg.exception.TrainingProgramNotFoundException;
import com.cg.service.ParticipantServiceImpl;

public class FeedbackDaoImpl implements FeedbackDao {
	static Logger myLogger = Logger.getLogger(FeedbackDaoImpl.class);

	@Override
	public int persistFeedback(Feedback feed, Participant par) throws FeedbackMasterNotExist {
		Connection conn = null;

		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database for adding Feedaback");
			PreparedStatement stmt = conn.prepareStatement(saveFeedbackQuery);
			stmt.setInt(1, par.getTrainingcode());
			stmt.setInt(2, par.getParticipantId());
			stmt.setInt(3, feed.getPresentationAndCommunication());
			stmt.setInt(4, feed.getDoubtClarification());
			stmt.setInt(5, feed.getTimeManagement());
			stmt.setInt(6, feed.getHandout());
			stmt.setInt(7, feed.getHwSwNwAvailability());
			stmt.setString(8, feed.getComments());
			stmt.setString(9, feed.getSuggestion());
			stmt.executeUpdate();
			myLogger.info("Update executed in feedback management table by adding new feedback");
			return 0;
		} catch (SQLException e) {
			myLogger.error("Failure to add new feedback");
			throw new FeedbackMasterNotExist();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					myLogger.info("Database connection closed and resources released");
				}
			} catch (SQLException e) {
				myLogger.error("Unable to close connection and release resources");
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Participant> fetchAllTrainingCode(int participantId, int trainingcode)
			throws TrainingProgramNotFoundException {
		Participant p = null;
		Connection conn = null;
		List<Participant> participant;
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info(
					"Connection successfully established with the database for matching training code with particiapnt Id");
			PreparedStatement stmt = conn.prepareStatement(getTrainingCodeQuery);
			stmt.setInt(1, trainingcode);
			stmt.setInt(2, participantId);
			ResultSet result = stmt.executeQuery();
			myLogger.info("Query for matching participant Id and training code executd successfully");
			participant = new ArrayList<Participant>();
			if (result.next()) {
				Participant par = new Participant();
				par.setTrainingcode(result.getInt(1));
				par.setParticipantId(result.getInt(2));
				participant.add(par);
				myLogger.info("Participant added in list successfully");
			}
			return participant;
		} catch (SQLException e) {
			myLogger.error("Failure to add new feedback");
			throw new TrainingProgramNotFoundException(e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
					myLogger.info("Database connection closed and resources released");
				} catch (SQLException e) {
					myLogger.error("Unable to close connection and release resources");
					e.printStackTrace();
				}
		}
	}
}
