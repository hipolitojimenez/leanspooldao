package com.nioos.leanspool.printer;



import com.nioos.leanspool.gwt.shared.PrinterModel;



/**
 * PrinterModel implementation.
 * @author Hipolito Jimenez.
 *
 */
public class PrinterModelImpl implements PrinterModel {
	
	
	/**
	 * The key.
	 */
	private String key;
	
	
	
	/**
	 * Constructor.
	 * @param theKey the key.
	 */
	public PrinterModelImpl(final String theKey) {
		key = theKey;
	}
	
	
	/**
	 * Gets the key.
	 * @return the key.
	 */
	public final String getKey() {
		return key;
	}
	
	
	/**
	 * Sets the key.
	 * @param value the key.
	 */
	public final void setKey(final String value) {
		key = value;
	}
	
	
}
