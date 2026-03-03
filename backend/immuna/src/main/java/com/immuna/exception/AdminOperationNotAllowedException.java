package com.immuna.exception;

public class AdminOperationNotAllowedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminOperationNotAllowedException(String message) {
        super(message);
    }

}
