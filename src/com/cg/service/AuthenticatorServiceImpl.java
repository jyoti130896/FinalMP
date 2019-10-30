package com.cg.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;

import com.cg.bean.EmployeeMaster;
import com.cg.dao.AuthenticatorDao;
import com.cg.dao.AuthenticatorDaoImpl;


public class AuthenticatorServiceImpl implements AuthenticatorService {
	static Logger myLogger =  Logger.getLogger(AuthenticatorServiceImpl.class);
	
	private static final SecureRandom RAND = new SecureRandom();
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 160; //512
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final int SALT_LENGTH = 160; //512
	
	private AuthenticatorDao dao;
	
	public AuthenticatorServiceImpl() {
		dao = new AuthenticatorDaoImpl(); 
	}
	
	private static Optional<String> generateSalt(final int length){
		if(length < 1) {
			System.out.println("error in generateSalt : length must be > 0 ");
			return Optional.empty();
		}
		
		byte[] salt = new byte[length];
		RAND.nextBytes(salt);
		return Optional.of(Base64.getEncoder().encodeToString(salt));
	}
	
private static Optional<String> hashPassword (String password, String salt){
		
		// converting the password from string to char array
		char[] chars = password.toCharArray();
		// converting the salt from get salt to byte array
		byte[] bytes = salt.getBytes();
		// Cannot keep password int string as strings are immutable 
		
		// Method to generate the key where chars is password, bytes is salt
		PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
		
		// clearing the array as it contains password in plain text format by setting everything to null
		Arrays.fill(chars, Character.MIN_VALUE);
		
		try {
			
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			return Optional.of(Base64.getEncoder().encodeToString(securePassword));
	
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			
			System.err.println("Exception occured in hashPassword()");
			return Optional.empty();
		
		} finally {
			
			spec.clearPassword();
		}
	}

	
	@Override
	public EmployeeMaster authenticateUser(int employeeId, String password) {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Authenticating Employee>>");
		EmployeeMaster employee = dao.getInfo(employeeId);
		if (employee == null) {
			return null;
		} else {
			String salt = employee.getUserSalt();
			String calculatedHash = hashPassword(password, salt).get();
			if(calculatedHash.equals(employee.getPassword())) {
				myLogger.info("Login SUCCESSFULL");
				return employee;
			} else {
				myLogger.error("Login FAILED!!! Invalid credentials");
				return null;
			}
		}
	}

	@Override
	public boolean addUser(int employeeId, String name, String password, String role) {
		myLogger.info("-----------------------------------------------------------------------------------------------------");
		myLogger.info("<<Adding a new Employee>>");
		String salt = generateSalt(SALT_LENGTH).get();
		String encryptedPassword = hashPassword(password, salt).get();
		EmployeeMaster employee = new EmployeeMaster();
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(name);
		employee.setPassword(encryptedPassword);
		employee.setRole(role);
		employee.setUserSalt(salt);
		myLogger.info("New employee ADDEDD");
		return dao.addUser(employee);
	}

	@Override
	public boolean validateEmployeeId(String employeeId) {
		 if(employeeId.matches(validateEmployeeId)) {
			 return true;
		 }
		 System.out.println("Invalid Employee ID can only be number");
		 return false;
	}
}
