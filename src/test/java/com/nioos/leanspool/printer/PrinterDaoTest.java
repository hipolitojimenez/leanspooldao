package com.nioos.leanspool.printer;



import org.junit.Assert;

import java.io.File;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import com.nioos.leanspool.dao.DataSourceUtils;
import com.nioos.leanspool.gwt.shared.PrinterModel;



/**
 * Tests the printer DAO.
 * @author Hipolito Jiménez.
 *
 */
public class PrinterDaoTest {
	
	
	/**
	 * Get printers tests.
	 * @throws Exception on error.
	 */
	@Test
	public final void testGetPrinters() throws Exception { // NOPMD
		final DataSource dataSource =
			DataSourceUtils.buildDataSource("/jdbc.properties");
		//
		final IDatabaseConnection dbConnection =
			new DatabaseDataSourceConnection(dataSource);
		final FlatXmlDataSetBuilder flatXmlDataSetBuilder =
			new FlatXmlDataSetBuilder();
		final IDataSet dataSet =
			flatXmlDataSetBuilder.build(
				new File("./src/test/resources/dbunit/PrintersTest.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
		dbConnection.getConnection().commit();
		dbConnection.close();
		//
		final PrinterDao printers = new PrinterDao();
		final List<PrinterModel> printerModelList = printers.getPrinters();
		Assert.assertEquals("invalid size",
			dataSet.getTable("Printer").getRowCount(),
			printerModelList.size());
		final PrinterModel printerModel = printerModelList.get(0);
		Assert.assertEquals("invalid key", "Printer01", printerModel.getKey());
	}
	
	
}
