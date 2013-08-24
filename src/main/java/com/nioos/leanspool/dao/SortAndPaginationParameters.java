package com.nioos.leanspool.dao;



/**
 * Sort and pagination parameters used to build the query.
 * @author Hipolito Jimenez.
 */
public class SortAndPaginationParameters {
	
	
	/**
	 * Sort parameters used to build the query.
	 */
	private final transient SortParameters sortParameters;
	
	
	/**
	 * Pagination parameters used to build the query.
	 */
	private final transient PaginationParameters pagParameters;
	
	
	/**
	 * Constrcutor.
	 * @param theSortParameters the sort parameters.
	 * @param thePagParameters the pagination parameters.
	 */
	public SortAndPaginationParameters(final SortParameters theSortParameters,
		final PaginationParameters thePagParameters) {
		sortParameters = theSortParameters;
		pagParameters = thePagParameters;
	}
	
	
	/**
	 * Gets the sort parameters.
	 * @return the sort parameters.
	 */
	public final SortParameters getSortParameters() {
		return sortParameters;
	}
	
	
	/**
	 * Gets the pagination parameters.
	 * @return the pagination parameters.
	 */
	public final PaginationParameters getPaginationParameters() {
		return pagParameters;
	}
	
	
}
