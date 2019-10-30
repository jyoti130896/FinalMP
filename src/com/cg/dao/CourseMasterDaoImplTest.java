package com.cg.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.CourseMaster;

import com.cg.exception.CourseNotFoundException;
import com.cg.exception.InvalidCourseException;
public class CourseMasterDaoImplTest {
	CourseMasterDao dao;

	@Before
	public void init() {
		dao = new CourseMasterDaoImpl();
	}

	@After
	public void release() {
		dao = null;
	}

	@Test
	public void testAddTraining() throws InvalidCourseException {

		CourseMaster course = new CourseMaster(3, "DotNetCloud", 15);
		assertEquals(3, dao.addCourse(course));
	}
	@Test
	public void testfetchCourseByCourseId() throws InvalidCourseException {
		CourseMaster course = dao.fetchCourseByCourseId(3);
		assertEquals(3, course.getCourseId());
		assertEquals("DotNetCloud", course.getCourseName());
		assertEquals(15, course.getDays());
	}
	@Test
	public void testFetchAllCourses() throws CourseNotFoundException {
		assertEquals(3, dao.fetchAllCourses().size());
	}
	@Test
	public void testUpdateCourse() {
		
		CourseMaster course = new CourseMaster(5,"JavaCloud", 90);
		CourseMaster upd = null;
		try {
			upd = dao.updateCourse(course);
		} catch (InvalidCourseException e) {
			System.out.println("Invalid details provided.");
		}
		assertEquals(8, upd.getCourseId());
	}
	@Test
	public void testRemoveCourse() {
		try {
			assertTrue("Course is successfully removed.",dao.removeCourse(3));
		} catch (InvalidCourseException e) {
			System.out.println("Invalid Course code provided.");
		}
	}
	@Test
	public void testCourseExist() {
		try {
			assertTrue("Is a Course",dao.courseExist(100));
		} catch (InvalidCourseException e) {
			System.out.println("Not a Course");
		}
	}
	
}
