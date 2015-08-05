package com.goeuro.test.exception;

public class GoEuroTestException extends RuntimeException {

	public GoEuroTestException() {
		super();
	}

	public GoEuroTestException(String s) {
		super(s);
	}

	public GoEuroTestException(String message, Throwable cause) {
		super(message, cause);
	}

	public GoEuroTestException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -8029060644813507786L;
}
