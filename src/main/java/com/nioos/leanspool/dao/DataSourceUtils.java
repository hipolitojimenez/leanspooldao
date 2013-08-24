package com.nioos.leanspool.dao;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;



/**
 * Utility class to build datasources.
 * @author Hipolito Jimenez.
 *
 */
public final class DataSourceUtils {
	
	
	/**
	 * Constructor.
	 */
	private DataSourceUtils() {
		//
	}
	
	
	/**
	 * Build a new datasource.
	 * @param propertiesFile the properties file name as String.
	 * @return the new datasource.
	 * @throws Exception on error.
	 */
	public static DataSource buildDataSource(final String propertiesFile)
			throws Exception { // NOPMD
		final Properties dataSourceProperties =
			getDataSourceProperties(propertiesFile);
		return BasicDataSourceFactory.createDataSource(dataSourceProperties);
	}
	
	
	/**
	 * Gets the properties file used to build the new datasource.
	 * @param propertiesFile the properties file name as String.
	 * @return the properties file.
	 * @throws IOException on error.
	 */
	private static Properties getDataSourceProperties(
			final String propertiesFile) throws IOException {
		final Properties properties = new Properties();
		InputStream inputStream = null; // NOPMD
		try {
			inputStream =
				DataSourceUtils.class.getResourceAsStream(propertiesFile);
			properties.load(inputStream);
			return properties;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	
}
