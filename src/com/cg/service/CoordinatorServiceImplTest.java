package com.cg.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.TrainingProgram;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;
import com.cg.exception.TrainingProgramNotFoundException;

public class CoordinatorServiceImplTest {

	CoordinatorService service;
	
	@Before
	public void init() {
	service = new CoordinatorServiceImpl();
	}
	
	@After
	public void release() {
	service = null;
	}
	
	@Test
	public void testAddTraining() {
		Date startDate = new Date(2020, 02, 20);
		Date endDate = new Date(2020, 03, 20);
		TrainingProgram tp = new TrainingProgram(802, 1002, 200, startDate, endDate);
		try {
			assertEquals(802, service.addTrainingProgram(tp));
		} catch (TrainingProgramNotFoundException e) {
			e.printStackTrace();
		} catch (FacultyDoesNotExist e) {
			e.printStackTrace();
		} catch (InvalidCourseException e) {
			e.printStackTrace();
		}
	}
	
	//not working
	@Test
	public void testGetTrainingProgramInfoByTrainingCode() {
		TrainingProgram tp = null;
		try {
			tp = service.fetchTrainingProgramByTrainingCode(800);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("No record found for the given training code.");
		}
		Date startDate = new Date(2020, 02, 20);
		Date endDate = new Date(2020, 03, 20);
		System.out.println(startDate.toString());
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
			tp = service.fetchTrainingProgramByTrainingCode(801);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Invalid training code provided.");
		}
		assertNotNull(tp);
	}
	
	@Test(expected = AssertionError.class)
	public void testGetTrainingByWrongCode() {
		TrainingProgram tp = null;
		try {
			tp = service.fetchTrainingProgramByTrainingCode(802);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Invalid training code provided.");
		}
		assertNotNull(tp);
	}
	
	@Test
	public void testGetAllTrainingProgram() {
		try {
			assertEquals(3, service.fetchAllTrainingProgram().size());
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("No record found.");
		}
	}
	
	@Test(expected = AssertionError.class)
	public void testGetAllTrainingProgramWithWrongSize() {
		try {
			assertEquals(2, service.fetchAllTrainingProgram().size());
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("No record found.");
		}
	}
	
	@Test
	public void testRemoveTrainingProgram() {
			try {
				assertTrue(service.removeTrainingProgram(804));
			} catch (TrainingProgramNotFoundException e) {
				System.out.println("Invalid Training code provided.");
			}
	}
	
	@Test(expected = AssertionError.class)
	public void testRemoveTrainingProgramWithWrongId() {
			try {
				assertTrue(service.removeTrainingProgram(8000));
			} catch (TrainingProgramNotFoundException e) {
				System.out.println("Invalid Training code provided.");
			}
	}
	
	//not working & also delete exception
	@Test
	public void testUpdateTrainingProgram() {
		Date startDate = new Date(2030, 02, 20);
		Date endDate = new Date(2030, 03, 20);
		TrainingProgram tp = new TrainingProgram(801, 1001, 200, startDate, endDate);
		TrainingProgram utp = null;
		try {
			utp = service.updateTrainingProgram(tp);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Invalid details provided.");
		}
		assertEquals(1001, utp.getCourseCode());
	}
	
	@Test
	public void testValidateDate() {
		assertTrue(service.validateDate("20/02/2020"));
	}
	
	@Test
	public void testValidateTrainingId() {
		assertTrue("Correct Training Id", service.validateTrainingId("1001"));
	}
	
	@Test
	public void testValidateCourseId() {
		assertTrue("Correct Course Id", service.validateCourseId("1020"));
	}
	
	@Test
	public void testValidateFacultyId() {
		assertTrue("Correct Faculty Id", service.validateFacultyId("2000"));
	}
	
	@Test(expected = AssertionError.class)
	public void testValidateDateWithWrongDate() {
		assertTrue(service.validateDate("2019/02/20"));
	}
	
	@Test(expected = AssertionError.class)
	public void testValidateTrainingIdWithWrongId() {
		assertTrue(service.validateTrainingId("Ab2"));
	}
	
	@Test(expected = AssertionError.class)
	public void testValidateCourseIdWithWrongId() {
		assertTrue(service.validateCourseId("2hg"));
	}
	
	@Test(expected = AssertionError.class)
	public void testValidateFacultyIdWithWrongId() {
		assertTrue(service.validateFacultyId("i8h"));
	}
}
