package org.openbox.support.crypto.keys;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import org.openbox.support.Utils;

/**
 * The Class KeyGenerator. Using configuration (keygen.properties) it can create
 * a public,private key pair for usage given some starting seed number. The
 * class uses SpongyCastle (a fork of bouncycastle.org)
 */
public class KeyGenerator {

	static {
		/** this registers the SC provider */
		Security.addProvider(new org.spongycastle.jce.provider.BouncyCastleProvider());
	}

	public KeyPair generateKeyPair(long seed) throws NoSuchAlgorithmException,
			NoSuchProviderException {
		SecureRandom randomSeed = new SecureRandom(Utils.toBytes(seed));
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "SC");
		keyGen.initialize(512, randomSeed);
		KeyPair keyPair = keyGen.generateKeyPair();
		return keyPair;
	}

}
