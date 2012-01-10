package org.openbox.support;
 
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
}
