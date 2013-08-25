package com.nioos.leanspool.printjobs;



import com.nioos.leanspool.gwt.shared.PrintJobModel;



/**
 * Print job model implementation.
 * 
 * @author Hipolito Jimenez.
 *
 */
public class PrintJobModelImpl implements PrintJobModel {
	
	
	/**
	 * The job Id.
	 */
	private String jobId;
	
	
	/**
	 * The printer name.
	 */
	private String printerName;
	
	
	/**
	 * The job status.
	 */
	private String jobStatus = PrintJobStatus.NEW.toString();
	
	
	/**
	 * The job data.
	 */
	private byte[] jobData;
	
	
	/**
	 * Gets the job id.
	 * @return the job id.
	 */
	public final String getJobId() {
		return jobId;
	}
	
	
	/**
	 * Sets the job id.
	 * @param value the job id.
	 */
	public final void setJobId(final String value) {
		jobId = value;
	}
	
	
	/**
	 * Gets the printer name.
	 * @return the printer name.
	 */
	public final String getPrinterName() {
		return printerName;
	}
	
	
	/**
	 * Sets the printer name.
	 * @param value the printer name.
	 */
	public final void setPrinterName(final String value) {
		printerName = value;
	}
	
	
	/**
	 * Gets the job status.
	 * @return the job status.
	 */
	public final String getJobStatus() {
		return jobStatus;
	}
	
	
	/**
	 * Sets the job status.
	 * @param value the job status.
	 */
	public final void setJobStatus(final String value) {
		jobStatus = value;
	}
	
	
	/**
	 * Gets the job data.
	 * @return the job data.
	 */
	public final byte[] getJobData() {
		return jobData; // NOPMD
	}
	
	
	/**
	 * Sets the job data.
	 * @param value the job data.
	 */
	public final void setJobData(final byte[] value) { // NOPMD
		jobData = value;
	}
	
	
}
