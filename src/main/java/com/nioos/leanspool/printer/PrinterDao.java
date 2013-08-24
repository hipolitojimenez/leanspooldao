package com.nioos.leanspool.printer;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nioos.leanspool.dao.BaseDao;
import com.nioos.leanspool.dao.DaoException;
import com.nioos.leanspool.gwt.shared.PrinterModel;



/**
 * The printer DAO.
 * @author Hipolito Jimenez.
 *
 */
public class PrinterDao extends BaseDao {
	
	
	/**
	 * Constructor.
	 * @throws DaoException on error.
	 */
	public PrinterDao() throws DaoException {
		super();
	}
	
	
	/**
	 * The logger.
	 */
	private static final Log LOG = LogFactory.getLog(PrinterDao.class);
	
	
	/**
	 * Retrieves the printer list.
	 * @return the printer list.
	 * @throws PrintersException on error.
	 * @throws DaoException on error.
	 */
	public final List<PrinterModel> getPrinters()
			throws PrintersException, DaoException {
		final Connection connection = getSelectConnection(); // NOPMD
		final Statement statement = getSelectStatement(connection); // NOPMD
		try {
			final List<PrinterModel> printerModelList =
				new ArrayList<PrinterModel>();
			final ResultSet resultSet = // NOPMD
				statement.executeQuery(
					"SELECT PrinterName FROM Printer ORDER BY PrinterName");
			while (resultSet.next()) {
				final String printerKey = resultSet.getString(1);
				final PrinterModel printerModel =
					new PrinterModelImpl(printerKey); // NOPMD
				printerModelList.add(printerModel);
			}
			resultSet.close();
			return printerModelList;
		} catch (SQLException sqle) {
			LOG.error("Cannot get printers", sqle);
			throw new PrintersException("Cannot get printers", sqle);
		} finally {
			silentCloseStatement(statement);
			silentCloseConnection(connection);
		}
	}
	
	
}
