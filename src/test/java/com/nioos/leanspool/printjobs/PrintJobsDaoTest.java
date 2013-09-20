package com.nioos.leanspool.printjobs;



import java.io.File;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;

import org.junit.Test;

import com.nioos.leanspool.dao.DataSourceUtils;
import com.nioos.leanspool.dao.PaginationParameters;
import com.nioos.leanspool.dao.SortAndPaginationParameters;
import com.nioos.leanspool.dao.SortParameters;
import com.nioos.leanspool.gwt.shared.PrintJobModel;



/**
 * Tests the print job DAO.
 * @author Hipolito Jimenez.
 */
public class PrintJobsDaoTest {
	
	
	/**
	 * New status.
	 */
	private static final String NEW = "New";
	
	
	/**
	 * Data file used to fill the database.
	 */
	private static final String DB_DATA_FILE =
		"./src/test/resources/dbunit/PrintJobsTest.xml";
	
	
	/**
	 * JDBC properties file.
	 */
	private static final String JDBC_PROPERTIES = "/jdbc.properties";
	
	
	/**
	 * Number of print jobs for "Printer01".
	 */
	private static final int NUMBEROFPRINTJOBSFORPRINTER01 = 99;
	
	
	/**
	 * Number of print jobs with status "New".
	 */
	private static final int NUMBEROFPRINTJOBSFORSTATUSNEW = 97;
	
	
	/**
	 * Get Print Jobs For Printer Test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetPrintJobsForPrinter() throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(new File(DB_DATA_FILE));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final SortParameters sortParameters =
			new SortParameters("JobId", "ASC");
		final PaginationParameters pagParameters =
			new PaginationParameters(50, 0);
		final SortAndPaginationParameters sapp =
			new SortAndPaginationParameters(sortParameters, pagParameters);
		final List<PrintJobModel> printJobList =
			printJobsDao.getPrintJobsForPrinter("Printer01", sapp); // NOPMD
		Assert.assertEquals("Invalid print jobs list size",
			PrintJobModel.PAGESIZE, printJobList.size());
		final PrintJobModel printJob = printJobList.get(0);
		final String printerName = printJob.getPrinterName();
		Assert.assertEquals("Invalid print name", "Printer01", printerName);
		final String jobId = printJob.getJobId();
		Assert.assertEquals("Invalid print job ID", "jobId01", jobId.trim());
	}
	
	
	/**
	 * Get Print Jobs Test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetPrintJobs() throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(new File(DB_DATA_FILE));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final SortParameters sortParameters =
			new SortParameters("JobId", "ASC");
		final PaginationParameters pagParameters =
			new PaginationParameters(50, 0);
		final SortAndPaginationParameters sapp =
			new SortAndPaginationParameters(sortParameters, pagParameters);
		final List<PrintJobModel> printJobList =
			printJobsDao.getPrintJobs(sapp);
		Assert.assertEquals("Invalid print jobs list size",
			PrintJobModel.PAGESIZE, printJobList.size());
		final PrintJobModel printJob = printJobList.get(0);
		final String printerName = printJob.getPrinterName();
		Assert.assertEquals("Invalid print name", "Printer01", printerName);
		final String jobId = printJob.getJobId();
		Assert.assertEquals("Invalid print job ID", "jobId01", jobId.trim());
	}
	
	
	/**
	 * Get Print Jobs For Status Test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetPrintJobsForStatus() throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(new File(DB_DATA_FILE));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final SortParameters sortParameters =
			new SortParameters("JobId", "ASC");
		final PaginationParameters pagParameters =
			new PaginationParameters(50, 0);
		final SortAndPaginationParameters sapp =
			new SortAndPaginationParameters(sortParameters, pagParameters);
		final List<PrintJobModel> printJobList =
			printJobsDao.getPrintJobsForStatus(NEW, sapp);
		Assert.assertEquals("Invalid print jobs list size",
			PrintJobModel.PAGESIZE, printJobList.size());
		final PrintJobModel printJob = printJobList.get(0);
		final String printerName = printJob.getPrinterName();
		Assert.assertEquals("Invalid print name", "Printer01", printerName);
		final String jobId = printJob.getJobId();
		Assert.assertEquals("Invalid print job ID", "jobId01", jobId.trim());
	}
	
	
	/**
	 * Gets the number of Print Jobs For Printer Test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetNumberOfPrintJobsForPrinter()
			throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(new File(DB_DATA_FILE));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final int countOfResults =
			printJobsDao.getNumberOfPrintJobsForPrinter("Printer01");
		Assert.assertEquals("Invalid count of print jobs",
			NUMBEROFPRINTJOBSFORPRINTER01, countOfResults);
	}
	
	
	/**
	 * Gets the number of Print Jobs test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetNumberOfPrintJobs() throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(new File(DB_DATA_FILE));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final int countOfResults = printJobsDao.getNumberOfPrintJobs();
		Assert.assertEquals("Invalid count of print jobs",
			dataSet.getTable("PrintJob").getRowCount() , countOfResults);
	}
	
	
	/**
	 * Gets the number of Print Jobs For Status Test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetNumberOfPrintJobsForStatus()
			throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(new File(DB_DATA_FILE));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final int countOfResults =
			printJobsDao.getNumberOfPrintJobsForStatus(NEW);
		Assert.assertEquals("Invalid count of print jobs",
			NUMBEROFPRINTJOBSFORSTATUSNEW, countOfResults);
	}
	
	
	/**
	 * Insert New Job Test.
	 * @throws Exception on error.
	 */
	@Test
	public final void testInsertNewJob()
			throws Exception { // NOPMD
		final PrintJobsDao printJobsDao = new PrintJobsDao();
		final PrintJobModelImpl printJobModel = new PrintJobModelImpl();
		printJobModel.setPrinterName("testPrinter");
		final byte[] buffer = "Test Data".getBytes("UTF-8"); // NOPMD
		printJobModel.setJobData(buffer);
		final String jobId = printJobsDao.insertNewJob(printJobModel);
		//
		final DataSource dataSource =
			DataSourceUtils.buildDataSource(JDBC_PROPERTIES);
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final String sql =
			"SELECT PrinterName, JobStatus, JobData, JobSize FROM "
			+ " PrintJob WHERE JobId = '" + jobId + "'";
		final ITable queryResult =
			dbConnection.createQueryTable("TEST_RESULT", sql);
		final String printerName =
			(String) queryResult.getValue(0, "PrinterName");
		final String jobStatus = (String) queryResult.getValue(0, "JobStatus");
		final byte[] data = (byte[]) queryResult.getValue(0, "JobData");
		final int jobSize = (Integer) queryResult.getValue(0, "JobSize");
		dbConnection.close();
		//
		Assert.assertEquals("Invalid jobId len", 36, jobId.length());
		Assert.assertEquals("Invalid printerName", "testPrinter", printerName);
		Assert.assertEquals("Invalid jobStatus", NEW, jobStatus);
		Assert.assertArrayEquals("Invalid data", buffer, data);
		Assert.assertEquals("Invalid job size", buffer.length, jobSize);
	}
	
	
}
