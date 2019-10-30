package com.cg.cli;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.CourseMaster;
import com.cg.bean.EmployeeMaster;
import com.cg.bean.Faculty;
import com.cg.exception.CourseNotFoundException;
import com.cg.exception.FacultyDoesNotExist;
import com.cg.exception.InvalidCourseException;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;


public class AdminCli {
	
	private static Scanner console;
	static {
		console = new Scanner(System.in);
	}

	void AdminView(EmployeeMaster employee) {
		System.out.println("Admin -- Welcome");
		
		int option = 0;
		
		while(true) {
			do {
				try {
					System.out.println("1-Faculty Skill Maintenance.");
					System.out.println("2-Course Maintenance.");
					System.out.println("3-View Feedback Report.");
					System.out.println("4-Back to home page.");
					option = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				console.nextLine();
			} while(option < 0);
			
			switch(option) {
			case 1: facultySkillMaintenance(); break;
			case 2: courseMaintenance(); break;
			case 3: new FeedbackReportCli().feedbackReportView(); break;
			case 4: break;
			default: System.out.println("Please add valid information..");
			}
			
			if(option == 4)
				break;
		}
	}
	
	
	private void courseMaintenance() {
		System.out.println("course maintenance -- Welcome");
		
		int option = 0;
		while(true) {
			do {
				try {
					System.out.println("1-Add Course details.");
					System.out.println("2-Fetch Course details by course id.");
					System.out.println("3-Fetch All Course details.");
					System.out.println("4-Remove Course by course id.");
					System.out.println("5-Update Course by course id.");
					System.out.println("6-Back to admin.");
					option = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				} 
				console.nextLine();
			} while (option < 0);
			
			switch(option) {
			case 1: addCourseDetails(); break;
			case 2: fetchCourseDetails(); break;
			case 3: fetchAllCourseDetails(); break;
			case 4: removeCourseByCourseId(); break;
			case 5: updateCoureByCourseId(); break;
			case 6: break;
			default: System.out.println("Please add valid information..");
			}
			if(option == 6)
				break;
		}
	}


	private void facultySkillMaintenance() {
		System.out.println("faculty skill maintenance -- Welcome");
		
		int option = 0;
		while(true) {
			do {
				try {
					System.out.println("1-Add Faculty details.");
					System.out.println("2-Back to admin.");
					option = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				} 
					console.nextLine();
			} while(option < 0);
			
			switch(option) {
			case 1: addFacultyDetails(); break;
			case 2: break;
			default: System.out.println("Please add valid information..");
			}
			if(option == 2)
				break;
		}
	}


	private static void updateCoureByCourseId() {
		AdminService service = new AdminServiceImpl();
		String courseid, coursename, coursedays;
		
		do {
			System.out.println("Enter course Id: ");
			courseid = console.next();
		} while(!service.validateCourseId(courseid));
		
		do {
			System.out.println("Enter Course Name: ");
			coursename = console.next();
		} while(!service.validateName(coursename));
			
		do {
			System.out.println("Enter Course Days: ");
			coursedays = console.next();
		} while(!service.validateDays(coursedays));
		
		CourseMaster course = new CourseMaster();
		course.setCourseId(Integer.parseInt(courseid));
		course.setCourseName(coursename);
		course.setDays(Integer.parseInt(coursedays));
		
		try {
			CourseMaster c = service.updateCourse(course);
			System.out.println("Course details are updated with course id: " + courseid);
		} catch (InvalidCourseException e) {
			System.out.println("Course details not updated..");
		}
		
	}

	private static void removeCourseByCourseId() {
		AdminService service = new AdminServiceImpl();
		String courseid;
		
		do {
		System.out.println("Enter course id: ");
		courseid = console.next();
		} while(!service.validateCourseId(courseid));
		
		try {
			service.removeCourse(Integer.parseInt(courseid));
			System.out.println("Course removed with id: " + courseid);
		} catch (InvalidCourseException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void fetchAllCourseDetails() {
		AdminService service = new AdminServiceImpl();
		
		try {
			List<CourseMaster> courses = service.getAllCourses();
			for(CourseMaster course : courses) {
				System.out.println(course.toString());
			}
		} catch (CourseNotFoundException e) {
			System.out.println("Course details not found..");
		}
		
	}

	private static void fetchCourseDetails() {
		AdminService service = new AdminServiceImpl();
		String courseid;
		
		do {
		System.out.println("Enter course id: ");
		courseid = console.next();
		} while(!service.validateCourseId(courseid));
		
		try {
			CourseMaster course = service.getCourseByCourseId(Integer.parseInt(courseid));
			System.out.println("Course Id: " + course.getCourseId());
			System.out.println("Course Name: " + course.getCourseName());
			System.out.println("Course Days: " + course.getDays());
		} catch (InvalidCourseException e) {
			System.out.println("Course does not exist..");
		}
		
	}

	private static void addCourseDetails() {
		AdminService service = new AdminServiceImpl();
		String courseid, coursename, coursedays;
		
//		do {
//		System.out.println("Enter Course ID: ");
//		courseid = console.next();
//		} while(!service.validateId(courseid));
		
		do {
		System.out.println("Enter Course Name: ");
		coursename = console.next();
		} while(!service.validateName(coursename));
		
		do {
		System.out.println("Enter Course Days: ");
		coursedays = console.next();
		} while(!service.validateDays(coursedays));
		
		CourseMaster course = new CourseMaster();
		//course.setCourseId(Integer.parseInt(courseid));
		course.setCourseName(coursename);
		course.setDays(Integer.parseInt(coursedays));
		
		try {
			int id = service.saveCourse(course);
			System.out.println("Course details are saved with course id: " + id);
		} catch (InvalidCourseException e) {
			System.out.println("Course details not saved..");
		}
		
	}
	
	private static void addFacultyDetails() {
		AdminService service = new AdminServiceImpl();
		String facultyId, skillset;
		
		do {
			System.out.println("Enter Faculty Id: ");
			facultyId = console.next();
		} while(!service.validateFacultyId(facultyId));
			
		do {
			System.out.println("Enter Faculty skillset: ");
			skillset = console.next();
		} while(!service.validateFacultySkillset(skillset));
					
		Faculty faculty = new Faculty();
//		String previousSkillset = faculty.getSkillSet();
//		System.out.println("previous skillset: " + previousSkillset);
//		String newSkillset = previousSkillset.concat(skillset);
//		System.out.println("new skillset: " + newSkillset);
//		
		faculty.setFacultyId(Integer.parseInt(facultyId));
		faculty.setSkillSet(skillset);
		
		try {
			int id = service.saveFacultySkillset(faculty);
			System.out.println("Faculty details are saved with id: " + id);
		} catch (FacultyDoesNotExist e) {
			System.out.println("Faculty details are not saved..");
		}
		
		
	}
	
}
