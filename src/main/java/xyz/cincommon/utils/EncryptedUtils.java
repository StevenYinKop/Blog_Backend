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
	public static void main(String[] args) {
		System.out.println(encrypt("jdbc:mysql://47.102.152.87:3306/ynwa?characterEncoding=UTF-8&serverTimezone=GMT%2B8", Constant.DEFAULT_ENCRYPT_KEY));
		System.out.println(decrypt("9DqWB84slc+eh742SPz3lc/adeuXeX2pY9GmFzhM1+PGQOTdYBNBvsaYxvrTvS1ctgdm6VLOJFuVkHeQIt2aV75BBZvGVnqzLIG7snNSvB4HQyMiObfSU72ox6ji5sK3", Constant.DEFAULT_ENCRYPT_KEY));
	}
}
