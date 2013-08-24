package com.nioos.leanspool.dao;



/**
 * Pagination parameters.
 * @author Hipolito Jimenez.
 */
public class PaginationParameters {
	
	
	/**
	 * Pagination offset.
	 */
	private final transient int offset;
	
	
	/**
	 * Maximum number of rows returned.
	 */
	private final transient int maxRows;
	
	
	/**
	 * Constructor.
	 * @param theLimit the pagination limit.
	 * @param theOffset the pagination offset.
	 */
	public PaginationParameters(final int theLimit, final int theOffset) {
		offset = theOffset;
		maxRows = theLimit + theOffset;
	}
	
	
	/**
	 * Gets the maximum number of rows.
	 * @return the maximum number of rows.
	 */
	public final int getMaxRows() {
		return maxRows;
	}
	
	
	/**
	 * Get the pagination offset.
	 * @return the pagination offset.
	 */
	public final int getOffset() {
		return offset;
	}
	
	
}
