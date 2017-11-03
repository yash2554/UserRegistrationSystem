package com.fdmgroup.jparegsystem;

/**
 * This class extends Exception and which maintains custom exception message
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public class RegisterException extends Exception {
	private static final long serialVersionUID = 1L;

	public RegisterException(String msg) {
		super(msg);
	}
}
