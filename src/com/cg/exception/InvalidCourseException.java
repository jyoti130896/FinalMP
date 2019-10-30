package com.cg.exception;

public class InvalidCourseException extends Exception {

	public InvalidCourseException() {
		super();
	}

	public InvalidCourseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidCourseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidCourseException(String arg0) {
		super(arg0);
	}

	public InvalidCourseException(Throwable arg0) {
		super(arg0);
	}

	
}
