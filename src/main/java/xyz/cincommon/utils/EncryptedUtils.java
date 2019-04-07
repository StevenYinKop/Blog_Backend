package xyz.cincommon.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptedUtils {
	private static final Base64.Decoder DECODER = Base64.getDecoder();
	private static final Base64.Encoder ENCODER = Base64.getEncoder();
	private static final Logger logger = LoggerFactory.getLogger(EncryptedUtils.class); 

	public static String encrypt(String src, String key){
		String result = src;
		try {
			byte[] raw = key.getBytes("UTF-8");
			SecretKeySpec spec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, spec);
			byte[] encrypted = cipher.doFinal(src.getBytes("UTF-8"));
			result = ENCODER.encodeToString(encrypted);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public static String decrypt(String src, String key) {
		String result = src;
		try {
			byte[] raw = key.getBytes("UTF-8");
			SecretKeySpec spec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] encrytped = DECODER.decode(src);
			byte[] original = cipher.doFinal(encrytped);
			result = new String(original, "UTF-8");
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
