package com.cg.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.EmployeeMaster;

public class AuthenticatorServiceImplTest {

	AuthenticatorService service;
	
	@Before
	public void init(){
		service=new AuthenticatorServiceImpl();
	}
	
	@After
	public void release() {
		service=null;
	}
	
	@Test
	public void addUserTest1() {
		assertTrue(service.addUser(8000, "Laxman", "laxman", "Participant"));
	}
	
	@Test
	public void authenticateUserWithCorrectInputParameters() {
		EmployeeMaster emp1 = service.authenticateUser(8000, "laxman");
		assertEquals(8000, emp1.getEmployeeId());
		assertEquals("Laxman", emp1.getEmployeeName());
		assertEquals("Participant", emp1.getRole());
	}
	
	@Test(expected = NullPointerException.class)
	public void authenticateUserWithWrongInputParameters() {
		EmployeeMaster emp1 = service.authenticateUser(7000, "jjjjj");
		assertEquals("Laxman", emp1.getEmployeeName());
	}
	
	
}
