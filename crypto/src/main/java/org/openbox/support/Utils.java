package org.openbox.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openbox.support.logging.Log;
 
/**
 * Global support utility methods gathered here.
 */
public final class Utils {

	
	/**
	 * Covert a byte[] to a long.
	 *
	 * @param data the data
	 * @return the long
	 */
	public static final long toLong(byte[] data) {
		return ((data[0] & 0xFFL) << 56) |
		         ((data[1] & 0xFFL) << 48) |
		         ((data[2] & 0xFFL) << 40) |
		         ((data[3] & 0xFFL) << 32) |
		         ((data[4] & 0xFFL) << 24) |
		         ((data[5] & 0xFFL) << 16) |
		         ((data[6] & 0xFFL) <<  8) |
		         ((data[7] & 0xFFL) <<  0) ;
	}
	
	/**
	 * convert a long to bytes.
	 *
	 * @param v the v
	 * @return the byte[]
	 */
	public static final byte[] toBytes(long v) {
	    byte[] writeBuffer = new byte[ 8 ];

	    writeBuffer[0] = (byte)(v >>> 56);
	    writeBuffer[1] = (byte)(v >>> 48);
	    writeBuffer[2] = (byte)(v >>> 40);
	    writeBuffer[3] = (byte)(v >>> 32);
	    writeBuffer[4] = (byte)(v >>> 24);
	    writeBuffer[5] = (byte)(v >>> 16);
	    writeBuffer[6] = (byte)(v >>>  8);
	    writeBuffer[7] = (byte)(v >>>  0);
	    return writeBuffer;
	}
	
	public static final Properties readProperties(String propertiesName) {
		InputStream in = Utils.class.getResourceAsStream("/"+propertiesName);
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			Log.error("Failed to load properties from "+propertiesName);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return p;
	}

	/**
	 * 
	 * @param providerName
	 * @return
	 */
	public static <T> T createClass(String providerName) {
		Class clazz = null;
		
		try {
			clazz = Class.forName(providerName);
			Object obj = clazz.newInstance();
			return (T)obj;
		} catch (ClassNotFoundException e) {
			if (!Log.READY) {
				e.printStackTrace();
			} else {
				Log.error("Class not found, "+e.getMessage(),e);
			}
		} catch (InstantiationException e) {
			if (!Log.READY) {
				e.printStackTrace();
			} else {
				Log.error("Failed to instanciate class, "+e.getMessage(),e);
			}
		} catch (IllegalAccessException e) {
			if (!Log.READY) {
				e.printStackTrace();
			} else {
				Log.error("Failed to access class, "+e.getMessage(),e);
			}
		}
		throw new RuntimeException("createClass() failed - please check previous log entries for causes");
	}
}
