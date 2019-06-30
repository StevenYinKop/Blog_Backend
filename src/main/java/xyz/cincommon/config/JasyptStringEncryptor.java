package xyz.cincommon.config;

import org.jasypt.encryption.StringEncryptor;

import xyz.cincommon.utils.Constant;
import xyz.cincommon.utils.EncryptedUtils;

public class JasyptStringEncryptor implements StringEncryptor{

	@Override
	public String encrypt(String message) {
		return EncryptedUtils.encrypt(message, Constant.DEFAULT_ENCRYPT_KEY);
	}

	@Override
	public String decrypt(String encryptedMessage) {
		return EncryptedUtils.decrypt(encryptedMessage, Constant.DEFAULT_ENCRYPT_KEY);
	}
}
