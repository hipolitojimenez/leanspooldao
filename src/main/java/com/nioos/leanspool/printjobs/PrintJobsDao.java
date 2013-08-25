package com.nioos.leanspool.printjobs;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nioos.leanspool.dao.BaseDao;
import com.nioos.leanspool.dao.DaoException;
import com.nioos.leanspool.dao.PaginationParameters;
import com.nioos.leanspool.dao.SortAndPaginationParameters;
import com.nioos.leanspool.gwt.shared.PrintJobModel;



/**
 * The print jobs DAO.
 * @author Hipolito Jimenez.
 *
 */
public class PrintJobsDao extends BaseDao {
	
	
	/**
	 * SQL statement to retrieve the number of print jobs for a given printer.
	 */
	private static final String NUMBEROFPRINTJOBSFORPRINTERSTMT =
		"SELECT COUNT(*) FROM PrintJob WHERE PrinterName = ?";
	
	
	/**
	 * SQL statement to retrieve the number of print jobs for a given status.
	 */
	private static final String NUMBEROFPRINTJOBSFORSTATUSSTMT =
		"SELECT COUNT(*) FROM PrintJob WHERE JobStatus = ?";
	
	
	/**
	 * Cannot get print jobs literal.
	 */
	private static final String CANNOT_GET_PRINT_JOBS =
		"Cannot get print jobs";
	
	
	/**
	 * The logger.
	 */
	private static final Log LOG = LogFactory.getLog(PrintJobsDao.class);
	
	
	/**
	 * SQL statement to retrieve the print jobs for a given printer.
	 */
	private static final String GETPRINTJOBSFORPRINTERSTMT =
		"SELECT JobId, JobStatus FROM PrintJob WHERE PrinterName = ?";
	
	
	/**
	 * SQL statement to retrieve all the print jobs.
	 */
	private static final String GETPRINTJOBSSTMT =
		"SELECT JobId, PrinterName, JobStatus FROM PrintJob";
	
	
	/**
	 * SQL statement to retrieve the print jobs for a given status.
	 */
	private static final String GETPRINTJOBSFORSTATUSSTMT =
		"SELECT JobId, PrinterName FROM PrintJob WHERE JobStatus = ?";
	
	
	/**
	 * SQL statement to insert a new print job.
	 */
	private static final String INSERTNEWPRINTJOBSTMT =
		"INSERT INTO PrintJob (JobId, PrinterName, JobStatus, JobData)"
			+ " VALUES (?, ?, ?, ?)";
	
	
	/**
	 * Constructor.
	 * @throws DaoException on error.
	 */
	public PrintJobsDao() throws DaoException {
		super();
	}
	
	
	/**
	 * Gets the print job list for the given printer.
	 * @param printer the printer name.
	 * @param sapp the sort and pagination parameters.
	 * @return the print job list for the given printer.
	 * @throws PrintJobsException on error.
	 * @throws DaoException on error.
	 */
	public final List<PrintJobModel> getPrintJobsForPrinter(
			final String printer,
			final SortAndPaginationParameters sapp)
				throws PrintJobsException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final String sql = GETPRINTJOBSFORPRINTERSTMT
			+ sapp.getSortParameters().getSortClause();
		final PaginationParameters paginationParameters =
			sapp.getPaginationParameters();
		final int maxRows = paginationParameters.getMaxRows();
		final PreparedStatement preparedStatement =
			getSelectPreparedStatement(connection, sql, maxRows);
		try {
			final List<PrintJobModel> printJobModelList =
				new ArrayList<PrintJobModel>();
			preparedStatement.setString(1, printer);
			final ResultSet resultSet = // NOPMD
				preparedStatement.executeQuery();
			final int offset = paginationParameters.getOffset();
			setResultSetOffset(resultSet, offset);
			while (resultSet.next()) {
				final String jobId = resultSet.getString(1);
				final String jobStatus = resultSet.getString(2);
				final PrintJobModel printJobModel =
					new PrintJobModelImpl(); // NOPMD
				printJobModel.setJobId(jobId);
				printJobModel.setPrinterName(printer);
				printJobModel.setJobStatus(jobStatus);
				printJobModelList.add(printJobModel);
			}
			resultSet.close();
			return printJobModelList;
		} catch (SQLException sqle) {
			LOG.error(CANNOT_GET_PRINT_JOBS, sqle);
			throw new PrintJobsException(CANNOT_GET_PRINT_JOBS, sqle);
		} finally {
			silentCloseStatement(preparedStatement);
			silentCloseConnection(connection);
		}
	}


	/**
	 * Gets the print job list.
	 * @param sapp the sort and pagination parameters. 
	 * @return the print job list.
	 * @throws PrintJobsException on error.
	 * @throws DaoException on error.
	 */
	public final List<PrintJobModel> getPrintJobs(
				final SortAndPaginationParameters sapp)
			throws PrintJobsException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final PaginationParameters paginationParameters =
			sapp.getPaginationParameters();
		final int maxRows = paginationParameters.getMaxRows();
		final Statement statement = // NOPMD
			getSelectStatement(connection, maxRows);
		try {
			final List<PrintJobModel> printJobModelList =
				new ArrayList<PrintJobModel>();
			final String sql = GETPRINTJOBSSTMT
				+ sapp.getSortParameters().getSortClause();
			final ResultSet resultSet = statement.executeQuery(sql); // NOPMD
			final int offset = paginationParameters.getOffset();
			setResultSetOffset(resultSet, offset);
			while (resultSet.next()) {
				final String jobId = resultSet.getString(1);
				final String printerName = resultSet.getString(2);
				final String jobStatus = resultSet.getString(3);
				final PrintJobModel printJobModel =
					new PrintJobModelImpl(); // NOPMD
				printJobModel.setJobId(jobId);
				printJobModel.setPrinterName(printerName);
				printJobModel.setJobStatus(jobStatus);
				printJobModelList.add(printJobModel);
			}
			resultSet.close();
			return printJobModelList;
		} catch (SQLException sqle) {
			LOG.error(CANNOT_GET_PRINT_JOBS, sqle);
			throw new PrintJobsException(CANNOT_GET_PRINT_JOBS, sqle);
		} finally {
			silentCloseStatement(statement);
			silentCloseConnection(connection);
		}
	}
	
	
	/**
	 * Gets the print job list with the given status.
	 * @param status the print job status.
	 * @param sapp the sort and pagination parameters. 
	 * @return the print job list with the given status.
	 * @throws PrintJobsException on error.
	 * @throws DaoException  on error.
	 */
	public final List<PrintJobModel> getPrintJobsForStatus(final String status,
				final SortAndPaginationParameters sapp)
			throws PrintJobsException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final String sql = GETPRINTJOBSFORSTATUSSTMT
			+ sapp.getSortParameters().getSortClause();
		final PaginationParameters paginationParameters =
			sapp.getPaginationParameters();
		final int maxRows = paginationParameters.getMaxRows();
		final PreparedStatement preparedStatement =
			getSelectPreparedStatement(connection, sql, maxRows);
		try {
			final List<PrintJobModel> printJobModelList =
				new ArrayList<PrintJobModel>();
			preparedStatement.setString(1, status);
			final ResultSet resultSet = // NOPMD
				preparedStatement.executeQuery();
			final int offset = paginationParameters.getOffset();
			setResultSetOffset(resultSet, offset);
			while (resultSet.next()) {
				final String jobId = resultSet.getString(1);
				final String printerName = resultSet.getString(2);
				final PrintJobModel printJobModel =
					new PrintJobModelImpl(); // NOPMD
				printJobModel.setJobId(jobId);
				printJobModel.setPrinterName(printerName);
				printJobModel.setJobStatus(status);
				printJobModelList.add(printJobModel);
			}
			resultSet.close();
			return printJobModelList;
		} catch (SQLException sqle) {
			LOG.error(CANNOT_GET_PRINT_JOBS, sqle);
			throw new PrintJobsException(CANNOT_GET_PRINT_JOBS, sqle);
		} finally {
			silentCloseStatement(preparedStatement);
			silentCloseConnection(connection);
		}
	}
	
	
	/**
	 * Gets the number of available print jobs.
	 * @return the number of available print jobs.
	 * @throws PrintJobsException on error.
	 * @throws DaoException on error.
	 */
	public final int getNumberOfPrintJobs()
			throws PrintJobsException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final Statement statement = getSelectStatement(connection); // NOPMD
		try {
			final ResultSet resultSet = // NOPMD
				statement.executeQuery("SELECT COUNT(*) FROM PrintJob");
			resultSet.next();
			final int countOfResults = resultSet.getInt(1);
			resultSet.close();
			return countOfResults;
		} catch (SQLException sqle) {
			LOG.error(CANNOT_GET_PRINT_JOBS, sqle);
			throw new PrintJobsException(CANNOT_GET_PRINT_JOBS, sqle);
		} finally {
			silentCloseStatement(statement);
			silentCloseConnection(connection);
		}
	}
	
	
	/**
	 * Gets the number of available print jobs for the given status.
	 * @param status the status.
	 * @return the number of available print jobs for the given status.
	 * @throws PrintJobsException on error.
	 * @throws DaoException on error.
	 */
	public final int getNumberOfPrintJobsForStatus(final String status)
			throws PrintJobsException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final PreparedStatement preparedStatement =
			getPreparedStatement(connection,
				NUMBEROFPRINTJOBSFORSTATUSSTMT);
		try {
			preparedStatement.setString(1, status);
			final ResultSet resultSet =  // NOPMD
				preparedStatement.executeQuery();
			resultSet.next();
			final int countOfResults = resultSet.getInt(1);
			resultSet.close();
			return countOfResults;
		} catch (SQLException sqle) {
			LOG.error(CANNOT_GET_PRINT_JOBS, sqle);
			throw new PrintJobsException(CANNOT_GET_PRINT_JOBS, sqle);
		} finally {
			silentCloseStatement(preparedStatement);
			silentCloseConnection(connection);
		}
	}
	
	
	/**
	 * Gets the number of available print jobs for the given printer.
	 * @param printer the printer.
	 * @return the number of available print jobs for the given status.
	 * @throws PrintJobsException on error.
	 * @throws DaoException on error.
	 */
	public final int getNumberOfPrintJobsForPrinter(final String printer)
			throws PrintJobsException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final PreparedStatement preparedStatement =
			getPreparedStatement(connection,
				NUMBEROFPRINTJOBSFORPRINTERSTMT);
		try {
			preparedStatement.setString(1, printer);
			final ResultSet resultSet =  // NOPMD
				preparedStatement.executeQuery();
			resultSet.next();
			final int countOfResults = resultSet.getInt(1);
			resultSet.close();
			return countOfResults;
		} catch (SQLException sqle) {
			LOG.error(CANNOT_GET_PRINT_JOBS, sqle);
			throw new PrintJobsException(CANNOT_GET_PRINT_JOBS, sqle);
		} finally {
			silentCloseStatement(preparedStatement);
			silentCloseConnection(connection);
		}
	}
	
	
	/**
	 * Inserts a new print job.
	 * @param printJobModel the new print job.
	 * @return the new job id key.
	 * @throws DaoException on error.
	 * @throws PrintJobsException on error.
	 */
	public final String insertNewJob(final PrintJobModel printJobModel)
			throws DaoException, PrintJobsException {
		final Connection connection = getInsertConnection(); // NOPMD
		final PreparedStatement preparedStatement =
				getPreparedStatement(connection, INSERTNEWPRINTJOBSTMT);
		try {
			final String jobId = getHashKey(printJobModel.getJobData());
			preparedStatement.setString(1, jobId);
			preparedStatement.setString(2, printJobModel.getPrinterName());
			preparedStatement.setString(3, printJobModel.getJobStatus());
			preparedStatement.setBytes(4, printJobModel.getJobData());
			preparedStatement.executeUpdate();
			return jobId;
		} catch (SQLException sqle) {
			LOG.error("Cannot insert new print job", sqle);
			throw new PrintJobsException("Cannot insert new print job", sqle);
		} finally {
			silentCloseStatement(preparedStatement);
			silentCloseConnection(connection);
		}
	}
	
	
}
