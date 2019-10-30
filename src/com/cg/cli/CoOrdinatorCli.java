package com.cg.cli;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.EmployeeMaster;
import com.cg.bean.Participant;
import com.cg.bean.TrainingProgram;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;
import com.cg.exception.ParticipantNotFoundException;
import com.cg.exception.TrainingProgramNotFoundException;
import com.cg.service.CoordinatorService;
import com.cg.service.CoordinatorServiceImpl;

public class CoOrdinatorCli {
	private static CoordinatorService service;
	private static Scanner console;
	
	static {
		console = new Scanner(System.in);
	}
	
	public CoOrdinatorCli() {
		service = new CoordinatorServiceImpl();
	}
	
	void coordinatorView(EmployeeMaster employee) {
		System.out.println("Co-Ordinator -- Welcome");
		int option = 0;

		while (true) {
			do {
				try {
				System.out.println("Enter Option");
				System.out.println("1 - Training Program Maintenance");
				System.out.println("2 - Participant Enrollment");
				System.out.println("3 - View Feedback Report");
				System.out.println("4 - Back to home page");
	
				option = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				} 
				console.nextLine();
			} while(option < 0);
			
			switch (option) {
			case 1:
				trainingProgramMaintenance();
				break;
			case 2:
				participantEnrollment();
				break;
			case 3:
				new FeedbackReportCli().feedbackReportView();
				break;
			case 4:
				break;
			default:
				System.out.println("Please enter correct option");
			}
			if(option == 4) break;
		}		
	}

	
	private void participantEnrollment() {
		
		int option = 0;
		
		while (true) {
			do {
				try {
				System.out.println("Enter Option");
				System.out.println("1 - Add Participant");
				System.out.println("2 - Back");
				
				option = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				console.nextLine();
			} while (option < 0);
			
			switch (option) {
			case 1:
				addParticipant();
				break;
			case 2:
				break;
			default:
				System.out.println("Please enter correct option");
			}
			if(option == 2) break;
		}	
	}
	
	private void trainingProgramMaintenance() {
		
		int option = 0;
		
		while (true) {
			do {
				try {
					System.out.println("Enter Option");
					System.out.println("1 - Add Training Program");
					System.out.println("2 - Show All Training Program");
					System.out.println("3 - Show Training Program by Training Code");
					System.out.println("4 - Update Training Program");
					System.out.println("5 - Remove Training Program");
					System.out.println("6 - Back");
		
					option = console.nextInt();
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				console.nextLine();
			} while (option < 0);
			
			switch (option) {
			case 1:
				addTrainingProgram();
				break;
			case 2:
				showAllTrainingProgram();
				break;
			case 3:
				showTrainingProgramById();
				break;
			case 4:
				updateTrainingProgram();
				break;
			case 5:
				removeTrainingProgram();
				break;
			case 6:
				break;
			default:
				System.out.println("Please enter correct option");
			}
			if(option == 6) break;
		}
	}

	

	private static void removeTrainingProgram() {

		int code = 0;
		System.out.println("Enter Course Code: ");
		code = console.nextInt();
		try {
			service.removeTrainingProgram(code);
			System.out.println("Training Program Removed Successfully");
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Training Code Not Found !!");
		}
		
	}	


	private static void updateTrainingProgram() {
		CoordinatorService service = new CoordinatorServiceImpl();
		String trainingCode, courseCode, facultyCode, startDate, endDate;
		TrainingProgram tp = new TrainingProgram();


		do {
		System.out.println("Enter training code: ");
		trainingCode = console.next();
		} while(!service.validateTrainingId(trainingCode));
		tp.setTrainingCode(Integer.parseInt(trainingCode));
		
		do {
		System.out.println("Enter Course Code: ");
		courseCode = console.next();
		} while(!service.validateCourseId(courseCode));
		tp.setCourseCode(Integer.parseInt(courseCode));
		
		do {
		System.out.println("Enter Faculty Code: ");
		facultyCode = console.next();
		} while(!service.validateFacultyId(facultyCode));
		tp.setFacultyCode(Integer.parseInt(facultyCode));
		
		do{
			System.out.println("Enter Start Date in dd/mm/yyyy format");
			startDate = console.next();
		} while (!service.validateDate(startDate));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date sdate;
		try {
			sdate = sdf.parse(startDate);
			System.out.println(sdate);
			Date sDate = new Date(sdate.getTime());
			tp.setStartDate(sDate);
			System.out.println(sDate);
		} catch (ParseException e1) {
			System.out.println("Invalid Date Format");
			e1.printStackTrace();
		}
		
		
		do {
		System.out.println("Enter End Date in dd/mm/yyyy format");
		endDate = console.next();
		} while (!service.validateDate(endDate));
		
		try {
			java.util.Date edate = sdf.parse(endDate);
			Date eDate = new Date(edate.getTime());
			tp.setEndDate(eDate);
		} catch (ParseException e1) {
			System.out.println("Invalid Date Format");
		}
				
		try {
		TrainingProgram t = service.updateTrainingProgram(tp);
		System.out.println("Training program details are updated with Training id: " + trainingCode);
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Training Program Does Not Exists");
		} 
	}

	private static void showTrainingProgramById() {
		int code = 0;
		System.out.println("Enter Training Id: ");
		code = console.nextInt();
		try {
			TrainingProgram result = service.fetchTrainingProgramByTrainingCode(code);
			System.out.println(result.toString());
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Training Program with code: " + code + " not found");
		}
 	
	}

	private static void showAllTrainingProgram() {

		try {
			List<TrainingProgram> result = service.fetchAllTrainingProgram();
			for (TrainingProgram t : result) {
				System.out.println(t.toString());
			}
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("No Training Program exists for the moment..Sorry!");
			e.printStackTrace();
		}

	}

	private static void addTrainingProgram() {
		String startDate, endDate;
		TrainingProgram tp = new TrainingProgram();
		System.out.println("Enter Training Code:");
		tp.setTrainingCode(console.nextInt());
		System.out.println("Enter Course Code");
		tp.setCourseCode(console.nextInt());
		System.out.println("Enter Faculty Code :");
		tp.setFacultyCode(console.nextInt());
		
		do{
			System.out.println("Enter Start Date");
			startDate = console.next();
		} while (!service.validateDate(startDate));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date sdate;
		try {
			sdate = sdf.parse(startDate);
			System.out.println(sdate);
			Date sDate = new Date(sdate.getTime());
			tp.setStartDate(sDate);
			System.out.println(sDate);
		} catch (ParseException e1) {
			System.out.println("Invalid Date Format");
			e1.printStackTrace();
		}
		
		
		do {
		System.out.println("Enter End Date");
		endDate = console.next();
		} while (!service.validateDate(endDate));
		
		try {
			java.util.Date edate = sdf.parse(endDate);
			Date eDate = new Date(edate.getTime());
			tp.setEndDate(eDate);
		} catch (ParseException e1) {
			System.out.println("Invalid Date Format");
		}
		
		try {
			service.addTrainingProgram(tp);
			System.out.println("Training Added Successfully");
		} catch (TrainingProgramNotFoundException e) {
			System.out.println("Training Program Does Not Exists");
		} catch (FacultyDoesNotExist e) {
			System.out.println("Faculty Does Not Exist");
		} catch (InvalidCourseException e) {
			System.out.println("Invalid Course Code");
		}
	}

	private static void addParticipant() {
		Participant participant = new Participant();
		
		System.out.println("Enter Training Code");
		participant.setTrainingcode(console.nextInt());
		
		System.out.println("Enter Participant ID");
		participant.setParticipantId(console.nextInt());
		System.out.println(participant);
		try {
			service.addParticipant(participant);
		} catch (ParticipantNotFoundException e) {
			System.out.println("Participant Does Not Exist Or Already ENrolled");
		}
		
	}
}
