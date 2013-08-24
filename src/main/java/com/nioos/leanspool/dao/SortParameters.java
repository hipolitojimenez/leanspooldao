package com.nioos.leanspool.dao;



/**
 * Sort parameters used to build the query.
 * @author Hipolito Jimenez
 */
public class SortParameters {
	
	
	/**
	 * The sort field.
	 */
	private final transient String sortField;
	
	
	/**
	 * The sort direction.
	 */
	private final transient String sortDir;
	
	
	/**
	 * Constructor.
	 * @param theSortField the sort field.
	 * @param theSortDir the sort direction.
	 */
	public SortParameters(final String theSortField, final String theSortDir) {
		sortDir = theSortDir;
		sortField = theSortField;
	}
	
	
	/**
	 * Gets the sort clause used to build the query.
	 * @return the sort clause used to build the query.
	 */
	public final String getSortClause() {
		String sortClause;
		if (sortDir == null || sortField == null) {
			sortClause = "";
		} else {
			sortClause = " ORDER BY " + sortField + " " + sortDir;
		}
		return sortClause;
	}
	
	
}
