package com.cg.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cg.bean.Faculty;
import com.cg.exception.FacultyDoesNotExist;

public class FacultyDaoImplTest {
	FacultyDao dao;

	@Before
	public void init() {
		dao = new FacultyDaoImpl();
	}

	@After
	public void release() {
		dao = null;
	}
	@Test
	public void testAddFaculty() throws FacultyDoesNotExist {
		Faculty faculty=new Faculty(1002,"CloudComputing");
		assertEquals(1002,dao.addFacultySkill(faculty));
	}
	@Test
	public void testIsFaculty() {
		try {
			assertTrue("Is a faculty",dao.isFaculty(1001));
		} catch (FacultyDoesNotExist e) {
			System.out.println("Not a Faculty");
		}
	}
}
