package org.openbox.support;

import java.util.Properties;


/**
 * The Class Log.
 */
public final class Log {

	public static boolean READY = false; 
	
	/** The Constant PROVIDER_KEY. */
	private static final String PROVIDER_KEY = "logging.provider";
	
	/** The provider. */
	private static LoggingSpi provider = null;
	
	static {
		Properties logProperties = Utils.readProperties("logging.properties");
		String providerName = logProperties.getProperty(PROVIDER_KEY, null);
		if (null == providerName) {
			provider = new NullLoggingProvider();
		} else {
			provider = Utils.createClass(providerName);
		}
		READY = true;
	}
	
	
	/**
	 * Info.
	 *
	 * @param message the message
	 */
	public static void info(String message) {
		provider.info(message);
	}
 
	/**
	 * Error.
	 *
	 * @param message the message
	 */
	public static void error(String message) {
		provider.error(message);
	}
 
	/**
	 * Warn.
	 *
	 * @param message the message
	 */
	public static void warn(String message) {
		provider.warn(message);
	}
	
	public static void error(String message, Throwable t) {
		provider.error(message, t);
	}
}

/**
 * A Null provider that writes nothing.
 *
 */
class NullLoggingProvider implements LoggingSpi {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void info(String message) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(String message) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(String message) {
		
	}

	@Override
	public void error(String message, Throwable t) {
		
	}
	
}
