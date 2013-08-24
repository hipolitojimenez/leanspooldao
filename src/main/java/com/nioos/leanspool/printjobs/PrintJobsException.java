package com.nioos.leanspool.printjobs;



/**
 * Exception thrown when there is an error related with the print jobs.
 * @author Hipolito Jimenez.
 *
 */
public class PrintJobsException extends Exception {
	
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5151996133833559858L;
	
	
	/**
	 * Default constructor.
	 */
	public PrintJobsException() {
		super();
	}
	
	
	/**
	 * Contructor.
	 * @param message the detail message.
	 */
	public PrintJobsException(final String message) {
		super(message);
	}
	
	
	/**
	 * Constructor.
	 * @param cause the cause.
	 */
	public PrintJobsException(final Throwable cause) {
		super(cause);
	}
	
	
	/**
	 * Constructor.
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public PrintJobsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	
}
