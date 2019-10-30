package com.cg.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.cg.bean.Participant;
import com.cg.exception.ParticipantNotFoundException;
import com.cg.exception.TrainingProgramNotFoundException;

public class ParticipantDaoImplTest {
	ParticipantDao dao;

	@Before
	public void init() {
		dao = new ParticipantDaoImpl();
	}

	@After
	public void release() {
		dao = null;
	}
	@Test
	public void testAddParticipant() throws ParticipantNotFoundException {

		Participant participant = new Participant(101,1003);
		assertEquals(3, dao.addParticipant(participant));
	}
	@Test
	public void testFindParticipantByTrainingCode() throws ParticipantNotFoundException {
		Participant p=dao.findParticipantByTrainingCode(1003);
		assertEquals(1003,p.getTrainingcode());
		assertEquals(101,p.getParticipantId());
	}
	@Test
	public void testFindParticipantByParticipantCode() throws ParticipantNotFoundException{
		Participant p=dao.findParticipantByParticipantCode(1001);
		assertEquals(1003,p.getTrainingcode());
		assertEquals(101,p.getParticipantId());
	}
	@Test
	public void testDeleteParticipantByTrainingCode() {
			assertTrue("Participant successfully deleted.",dao.deleteParticipantByTrainingCode(1003));
	}
	@Test
	public void testDeleteParticipantByParticipantId() {
			assertTrue("Participant successfully deleted.",dao.deleteParticipantByParticipantId(1001));
	}
	@Test
	public void testFetchAllParticipantsInATraining() throws TrainingProgramNotFoundException {
		assertEquals(3, dao.trainingByParticicpant(1001).size());
	}
	@Test
	public void testcheckIfEnrolled() {
		try {
			assertTrue("Is a Course",dao.checkIfEnrolled(1003,1001));
		} catch (ParticipantNotFoundException e) {
			System.out.println("Not Enrolled");
		}
	}
	}
