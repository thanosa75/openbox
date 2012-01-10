package org.openbox.support;

/**
 * a service provider interface for pluggable logging.
 * 
 */
public interface LoggingSpi {

	/**
	 * a method to record a log entry on INFO level.
	 * @param message the message
	 */
	public void info(String message);
	/**
	 * a method to record a log entry on ERROR level.
	 * @param message the message
	 */
	public void error(String message);
	/**
	 * a method to record a log entry on ERROR level, together with an Exception/Throwable.
	 * @param message the message
	 * @param t the throwable or exception
	 */
	public void error(String message, Throwable t);
	/**
	 * a method to record a log entry on WARN level.
	 * @param message the message
	 */
	public void warn(String message);
}
