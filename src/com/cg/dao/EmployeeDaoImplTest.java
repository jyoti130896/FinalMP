package com.cg.dao;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.ParticipantNotFoundException;

public class EmployeeDaoImplTest {
	EmployeeDao dao;

	@Before
	public void init() {
		dao = new EmployeeDaoImpl();
	}

	@After
	public void release() {
		dao = null;
	}
@Test
public void testCheckFaculty() {
	try {
		assertTrue("Is a faculty",dao.checkFaculty(1001));
	} catch (FacultyDoesNotExist e) {
		System.out.println("Not a Faculty");
	}
}
	@Test
	public void testCheckParticipant() {
		try {
			assertTrue("Is a Participant",dao.checkParticipant(1002));
		} catch (ParticipantNotFoundException e) {
			System.out.println("Not a Participant");
		}
	
}
}
