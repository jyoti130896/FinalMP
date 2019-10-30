package com.cg.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.TrainingProgram;
import com.cg.exception.TrainingProgramNotFoundException;

public class TrainingProgramDaoImplTest {

	TrainingProgramDao dao;
	
	@Before
	public void init() {
		dao = new TrainingProgramDaoImpl();
	}
	
	@After
	public void release() {
		dao = null;
	}
	
	@Test
	public void testAddTraining() throws TrainingProgramNotFoundException {
		Date startDate = new Date(2020, 02, 20);
		Date endDate = new Date(2020, 03, 20);
		TrainingProgram tp = new TrainingProgram(800, 1002, 200, startDate, endDate);
		assertEquals(800, dao.addTraining(tp));
	}
	
	//Not working
	@Test
	public void fetchByCode() throws TrainingProgramNotFoundException {
		TrainingProgram tp = dao.fetchTrainingProgramByTrainingCode(1002);
		java.util.Date date = new java.util.Date(2020, 5, 12);
		//SimpleDateFormat sdf =  new 
		Date startDate = new Date(2020, 02, 20);
		Date endDate = new Date(date.getTime());
		System.out.println(endDate);
		assertEquals(800, tp.getTrainingCode());
		assertEquals(1001, tp.getCourseCode());
		assertEquals(200, tp.getFacultyCode());
		assertEquals(startDate, tp.getStartDate()); //date is not proper
		assertEquals(endDate, tp.getEndDate());
	}
	
	@Test
	public void testGetTrainingByCode() {
		TrainingProgram tp = null;
		try {
			tp = dao.fetchTrainingProgramByTrainingCode(801);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Invalid training code provided.");
		}
		assertNotNull(tp);
	}
	
	@Test(expected = AssertionError.class)
	public void testGetTrainingByWrongCode() {
		TrainingProgram tp = null;
		try {
			tp = dao.fetchTrainingProgramByTrainingCode(802);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Invalid training code provided.");
		}
		assertNotNull(tp);
	}
	
	@Test
	public void testGetAllTrainingProgram() throws TrainingProgramNotFoundException {
		assertEquals(3, dao.fetchAllTrainingProgram().size());
	}
	
	@Test(expected = AssertionError.class)
	public void testGetAllTrainingProgramWithWrongSize() throws TrainingProgramNotFoundException {
		assertEquals(2, dao.fetchAllTrainingProgram().size());
	}
	
	@Test
	public void testRemoveTrainingProgram() {
			try {
				assertTrue("Training program is successfully removed.",dao.removeTrainingProgram(8000));
			} catch (TrainingProgramNotFoundException e) {
				System.out.println("Invalid Training code provided.");
			}
	}
	
	@Test(expected = AssertionError.class)
	public void testRemoveTrainingProgramWithWrongId() {
			try {
				assertTrue("Training program is successfully removed.",dao.removeTrainingProgram(8000));
			} catch (TrainingProgramNotFoundException e) {
				System.out.println("Invalid Training code provided.");
			}
	}
	
	//not working & also delete exception
	@Test
	public void testUpdateTrainingProgram() {
		Date startDate = new Date(2030, 02, 20);
		Date endDate = new Date(2030, 03, 20);
		TrainingProgram tp = new TrainingProgram(1002, 102, 1001, startDate, endDate);
		TrainingProgram utp = null;
		try {
			utp = dao.updateTrainingProgram(tp);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Invalid details provided.");
		}
		assertEquals(1001, utp.getCourseCode());
	}
}
