package com.nioos.leanspool.printjobs;



/**
 * Print job status enumeratiom.
 * @author Hipolito Jimenez.
 */
public enum PrintJobStatus {
	
	
	/**
	 * New status.
	 */
	NEW("New");
	
	
	/**
	 * The status.
	 */
	private final String status;
	
	
	/**
	 * Constructor.
	 * @param theStatus the status.
	 */
	private PrintJobStatus(final String theStatus) {
		status = theStatus;
	}
	
	
	@Override
	public String toString() {
		return status;
	}
	
	
}
