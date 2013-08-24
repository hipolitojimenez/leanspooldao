package com.nioos.leanspool.printer;



/**
 * Exception thrown when there is an error related with the printers.
 * @author Hipolito Jimenez.
 *
 */
public class PrintersException extends Exception {
	
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -2556322505879507097L;
	
	
	/**
	 * Default constructor.
	 */
	public PrintersException() {
		super();
	}
	
	
	/**
	 * Contructor.
	 * @param message the detail message.
	 */
	public PrintersException(final String message) {
		super(message);
	}
	
	
	/**
	 * Constructor.
	 * @param cause the cause.
	 */
	public PrintersException(final Throwable cause) {
		super(cause);
	}
	
	
	/**
	 * Constructor.
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public PrintersException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	
}
