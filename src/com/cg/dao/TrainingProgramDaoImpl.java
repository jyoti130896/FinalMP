package com.cg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.TrainingProgram;
import com.cg.exception.TrainingProgramNotFoundException;

public class TrainingProgramDaoImpl implements TrainingProgramDao {

	static Logger myLogger =  Logger.getLogger(TrainingProgramDaoImpl.class);

	@Override
	public int addTraining(TrainingProgram training) throws TrainingProgramNotFoundException {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database for adding new training program");
			PreparedStatement stmt = conn.prepareStatement(addTrainingProgram);
			stmt.setInt(1, training.getCourseCode());
			stmt.setInt(2, training.getFacultyCode());
			stmt.setDate(3, (Date) training.getStartDate());
			stmt.setDate(4, (Date) training.getEndDate());
			stmt.executeUpdate();

			PreparedStatement stmt2 = conn.prepareStatement(fetchTrainingProgramByTrainingCode);
			stmt2.setInt(1, training.getTrainingCode());
			ResultSet rs = stmt2.executeQuery();
			myLogger.info("New Training program added with training code: "+training.getTrainingCode());

			if(rs.next()) 
				return rs.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.error("Failure to add new training program");
			throw new TrainingProgramNotFoundException("Failure to add new course");
		} finally {
			try {
				if(conn != null) {
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
	public List<TrainingProgram> fetchAllTrainingProgram() throws TrainingProgramNotFoundException {
	List<TrainingProgram> programs=new ArrayList<TrainingProgram>();
	Connection conn = null;
	try {
		conn = JdbcUtil.getConnection();
		myLogger.info("Connection successfully established with the database for fetching training programs from database");
		PreparedStatement stmt = conn.prepareStatement(fetchAllTrainingProgram);
		ResultSet result = stmt.executeQuery();
		while(result.next()) {
			TrainingProgram program = new TrainingProgram();
			program.setTrainingCode(result.getInt(1));
			program.setCourseCode(result.getInt(2));
			program.setFacultyCode(result.getInt(3));
			program.setStartDate(result.getDate(4));
			program.setEndDate(result.getDate(5));
			programs.add(program);
		}
		if(programs.size() == 0) {
			myLogger.error("No training programs exist");
			throw new TrainingProgramNotFoundException("No Training Programs exists as of now!!");
			}
		myLogger.info("All training programs fetched successfully");
		return programs;
	} catch (SQLException e) {
		myLogger.error("Failure to fetch training program");
		throw new TrainingProgramNotFoundException(e.getMessage());
	} finally {
		try {
			if(conn != null) {
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
	public TrainingProgram fetchTrainingProgramByTrainingCode(int code) throws TrainingProgramNotFoundException {
		Connection conn = null;
		TrainingProgram program=null;

		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database for fetching training program from database");
			PreparedStatement stmt = conn.prepareStatement(fetchTrainingProgramByTrainingCode);
			stmt.setInt(1, code);
			
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				program = new TrainingProgram();
				program.setTrainingCode(result.getInt(1));
				program.setCourseCode(result.getInt(2));
				program.setFacultyCode(result.getInt(3));
				program.setStartDate(result.getDate(4));
				program.setEndDate(result.getDate(5));
			} else {
				myLogger.error("No training program found with training code: "+code);
				throw new TrainingProgramNotFoundException("Training Program does not exist..");
			}
			myLogger.info("Training programs fetched successfully for training code: "+code);
			return program;
		} catch (SQLException e) {
			myLogger.error("Failure to fetch training program");
			throw new TrainingProgramNotFoundException(e.getMessage());
		} finally {
			try {
				if(conn != null) {
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
	public TrainingProgram updateTrainingProgram(TrainingProgram training) throws TrainingProgramNotFoundException {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database to update training program from database");
			PreparedStatement stmt1 = conn.prepareStatement(fetchTrainingProgramByTrainingCode);
			stmt1.setInt(1, training.getTrainingCode());
			ResultSet result = stmt1.executeQuery();

			if(!result.next())
			{
				myLogger.error("training program can not be updated.. training not found with training code: "+training.getTrainingCode());
				throw new TrainingProgramNotFoundException("training details can not be updated.. training code not found.");
			}
			else {
				PreparedStatement stmt = conn.prepareStatement(updateTrainingProgram);
				stmt.setInt(1, training.getCourseCode());
				stmt.setInt(2, training.getFacultyCode());
				stmt.setDate(3, (Date) training.getStartDate());
				stmt.setDate(4, (Date) training.getEndDate());
				stmt.setInt(5, training.getTrainingCode());
				stmt.executeUpdate();
				myLogger.info("Training program successfully updated for training id: "+training.getTrainingCode());
			}
			return training;
		} catch (SQLException e) {
			myLogger.error("Failure to update training program");
			e.printStackTrace();
			throw new TrainingProgramNotFoundException("Failure to update training program");
		} finally {
			try {
				if(conn != null) {
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
	public boolean removeTrainingProgram(int code) throws TrainingProgramNotFoundException {
		Connection conn = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database to remove training program from database");
			PreparedStatement stmt = conn.prepareStatement( removeTrainingProgram);
			stmt.setInt(1, code);
			stmt.executeUpdate();
			myLogger.info("Training program successfully removed for training code: "+code);
			return true;
		} catch (SQLException e) {
			myLogger.error("training program cannot be deleted.. training not found with code: "+code);
			throw new TrainingProgramNotFoundException(e.getMessage());
		} finally {
			try {
				if(conn != null) {
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
	public Date getTrainingDate(int trainingCode) {
		Connection conn = null;
		Date date = null;
		
		try {
			conn = JdbcUtil.getConnection();
			myLogger.info("Connection successfully established with the database to remove training program from database");
			PreparedStatement stmt = conn.prepareStatement(getTrainingDate);
			stmt.setInt(1, trainingCode);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				date = rs.getDate(1);
				myLogger.info("Training date successfully found for training code: "+trainingCode);
				return date;
			}
		} catch (SQLException e) {
			myLogger.error("training date cannot be found for training code: "+trainingCode);
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
					myLogger.info("Database connection closed and resources released");			
				}
			} catch (SQLException e) {
				myLogger.error("Unable to close connection and release resources");
				e.printStackTrace();
			}
		}
		return null;
	}
}
