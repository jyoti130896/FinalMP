package com.cg.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.EmployeeMaster;

public class AuthenticatorDaoImplTest {

	AuthenticatorDao dao;
	
	@Before
	public void init() {
		dao = new AuthenticatorDaoImpl();
	}
	
	@After
	public void release() {
		dao = null;
	}
	
	@Test
	public void testAddUser() {
		EmployeeMaster emp1 = new EmployeeMaster(7000, "Ram", "ram", "Participant", "vgdaoixychdbkk");
		assertTrue(dao.addUser(emp1));
	}
	
	@Test
	public void testGetInfoWithValidInputParameters() {
		EmployeeMaster emp1 = dao.getInfo(7000);
		assertEquals(7000, emp1.getEmployeeId());
		assertEquals("Ram", emp1.getEmployeeName());
		assertEquals("ram", emp1.getPassword());
		assertEquals("Participant", emp1.getRole());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetInfoWithWrongInputParameters() {
		EmployeeMaster emp1 = dao.getInfo(2200);
		assertEquals("Ram", emp1.getEmployeeName());
		assertEquals("Participant", emp1.getRole());
	}
	
}
