package org.openbox.support.crypto.keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Properties;

import org.openbox.support.Utils;

/**
 * The Class KeyGenerator. Using configuration (keygen.properties) it can create
 * a public,private key pair for usage given some starting seed number. The
 * class uses SpongyCastle (a fork of bouncycastle.org)
 */
public class KeyGenerator {

	private static final String KEYPAIR_ALGO = "keypair.algorithm";
	private static final String KEYPAIR_PROV = "keypair.provider";
	private static final String KEYPAIR_SIZE = "keypair.size";

	private static Properties config = null;

	static {
		/** this registers the SC provider */
		Security.addProvider(new org.spongycastle.jce.provider.BouncyCastleProvider());
		if (config == null) {
			config = Utils.readProperties("keygen.properties");
		}
	}

	public KeyPair generateKeyPair(long seed) throws NoSuchAlgorithmException,
			NoSuchProviderException {
		SecureRandom randomSeed = new SecureRandom(Utils.toBytes(seed));
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(
				config.getProperty(KEYPAIR_ALGO),
				config.getProperty(KEYPAIR_PROV));
		keyGen.initialize(Integer.parseInt(config.getProperty(KEYPAIR_SIZE)),
				randomSeed);
		KeyPair keyPair = keyGen.generateKeyPair();
		return keyPair;
	}

}
