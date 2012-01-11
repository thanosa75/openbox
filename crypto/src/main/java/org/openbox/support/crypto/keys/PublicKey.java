package org.openbox.support.crypto.keys;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.openbox.support.logging.Log;

public class PublicKey {

	private java.security.PublicKey key;

	public PublicKey() {
		
	}
	
	public PublicKey(KeyPair pair) {
		key = pair.getPublic();
	}
	
	/*
	
	for private key, you need
	// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				privateKey.getEncoded());
	
	
		// Read Private Key.
	PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
	*/
	public byte[] exportKey() throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		String algorithm = key.getAlgorithm();
		oos.writeObject(algorithm);
		String format = key.getFormat();
		oos.writeObject(format);
		byte[] encoded = key.getEncoded();
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
				encoded);
		byte[] x509key = x509EncodedKeySpec.getEncoded();
		oos.writeInt(x509key.length);
		oos.write(x509key);
		oos.flush();
		Log.info("Wrote "+format+" "+algorithm+" of size "+x509key.length);
		return bout.toByteArray();
	}
	
	public void importKey(byte[] data) throws IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
		ByteArrayInputStream bin = new ByteArrayInputStream(data);
		ObjectInputStream oin = new ObjectInputStream(bin);
		String algorithm = (String) oin.readObject();
		String format = (String) oin.readObject();
		int size = oin.readInt();
		byte[] encoded = new byte[size];
		if (oin.read(encoded, 0, size) != size) {
			throw new IOException("Stream does not contain enough data (expected "+size+" bytes");
		}
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerator.config.getProperty(KeyGenerator.KEYPAIR_ALGO));
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encoded);
		key = keyFactory.generatePublic(publicKeySpec);
 
		Log.info("Read "+format+" "+algorithm+" of size "+encoded.length);
	}
}
