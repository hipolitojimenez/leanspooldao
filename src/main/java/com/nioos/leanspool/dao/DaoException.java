package com.nioos.leanspool.dao;



/**
 * Exception thrown when there is an error related with the DAO.
 * @author Hipolito Jimenez.
 *
 */
public class DaoException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9028593754068695588L;
	
	
	/**
	 * Default constructor.
	 */
	public DaoException() {
		super();
	}
	
	
	/**
	 * Contructor.
	 * @param message the detail message.
	 */
	public DaoException(final String message) {
		super(message);
	}
	
	
	/**
	 * Constructor.
	 * @param cause the cause.
	 */
	public DaoException(final Throwable cause) {
		super(cause);
	}
	
	
	/**
	 * Constructor.
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	
}
