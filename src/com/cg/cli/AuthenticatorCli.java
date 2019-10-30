package com.cg.cli;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.cg.bean.EmployeeMaster;
import com.cg.exception.TrainingProgramNotFoundException;
import com.cg.service.AuthenticatorService;
import com.cg.service.AuthenticatorServiceImpl;

public class AuthenticatorCli {

	private static Scanner console;

	static {
		console = new Scanner(System.in);
	}
	
	public static void main(String[] args) throws InterruptedException, TrainingProgramNotFoundException, SQLException {
		PropertyConfigurator.configure("src/log4j.properties");
		
		System.out.println(" ________  ____    ____   ______   \r\n" + 
							"|_   __  ||_   \\  /   _|.' ____ \\  \r\n" + 
							"  | |_ \\_|  |   \\/   |  | (___ \\_| \r\n" + 
							"  |  _|     | |\\  /| |   _.____`.  \r\n" + 
							" _| |_     _| |_\\/_| |_ | \\____) | \r\n" + 
							"|_____|   |_____||_____| \\______.' \r\n" + 
							"                                   ");
		
		int option = 0;

		while (true) {
			do { 
				try {
					System.out.println("======================================");
					System.out.println("Welcome to Feedback Management System ");
					System.out.println("Enter Option");
					System.out.println("1 - Signup 2 - Login 3 - Exit");
					option = console.nextInt();	
				} catch (InputMismatchException e) {
					System.out.println("Invalid Selection"); 
				}
				console.nextLine(); // to clear the buffer
			} while(option < 0);
			
			switch (option) {
			case 1: signup();
				break;
			case 2: login();
				break;
			case 3: System.exit(0);
			default: System.out.println("Please enter correct option");
			}
		}
	}
	
	
	private static void login() throws InterruptedException, TrainingProgramNotFoundException, SQLException {
		AuthenticatorService service = new AuthenticatorServiceImpl();
		int employeeId;
		String id,password;
		
		do{
			System.out.println("Enter Employee ID");
			id = console.next();
		}while(!service.validateEmployeeId(id));
		employeeId = Integer.parseInt(id);
		
		System.out.println("Enter Password");
		password = console.next();
		
		EmployeeMaster employee = service.authenticateUser(employeeId, password);
		if(employee != null) {
		switch (employee.getRole()) {
			case "Participant": new ParticipantCli().participantView(employee);
				break;
			case "Admin": new AdminCli().AdminView(employee);
				break;
			case "Co-Ordinator": new CoOrdinatorCli().coordinatorView(employee);
			}
		}
		else {
			System.out.println("Invalid Credentiails");
		}
	}

	private static void signup() {
		AuthenticatorService service = new AuthenticatorServiceImpl();
		String id = null;
		
		do {
			System.out.println("Enter Employee ID");
			id = console.next();
		} while(!service.validateEmployeeId(id));
		
		int employeeId = Integer.parseInt(id);
		
		System.out.println("Enter Name");
		String name = console.next();
		
		System.out.println("Enter Password");
		String password = console.next();
		
		int option = 0;
		String role = null;
		
		while (true) {
			do{
				try {
					System.out.println("Enter Role");
					System.out.println("1 - Participant 2- Co-Ordinator 3- Admin 4- Exit");
					option = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Selection");
				}
				console.nextLine();
			} while(option < 0);
			
			switch (option) {
			case 1: role = "Participant";
				break;
			case 2: role = "Co-Ordinator";
				break;
			case 3: role = "Admin";
				break;
			case 4: System.exit(0);
			default:{
				System.out.println("Please enter correct option");
				continue;
			}
			}
			break;
			
		}
		service.addUser(employeeId, name, password, role);
		System.out.println("Employee Added Successfully");
	}
}
	

