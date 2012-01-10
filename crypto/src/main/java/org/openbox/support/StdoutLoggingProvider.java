package org.openbox.support;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A LoggingSpi provider using System.out/err for loggging
 */
public class StdoutLoggingProvider implements LoggingSpi {

	/* (non-Javadoc)
	 * @see org.openbox.support.LoggingSpi#info(java.lang.String)
	 */
	@Override
	public void info(String message) {
		System.out.println(conv(message));
	}

	/* (non-Javadoc)
	 * @see org.openbox.support.LoggingSpi#error(java.lang.String)
	 */
	@Override
	public void error(String message) {
		System.err.println(conv(message));
	}

	/* (non-Javadoc)
	 * @see org.openbox.support.LoggingSpi#error(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void error(String message, Throwable t) {
		System.err.println(conv(message));
		t.printStackTrace(System.err);
	}

	/* (non-Javadoc)
	 * @see org.openbox.support.LoggingSpi#warn(java.lang.String)
	 */
	@Override
	public void warn(String message) {
		System.err.println(conv(message));
	}

	/**
	 * converts a message adding the date/time.
	 * @param message
	 * @return
	 */
	private String conv(String message) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder bld = new StringBuilder(fmt.format(new Date()));
		return bld.append(" ").append(message).toString();
	}

}
