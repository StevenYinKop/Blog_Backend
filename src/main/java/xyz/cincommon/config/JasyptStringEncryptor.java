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

	public static void main(String[] args) {
		System.out.println(EncryptedUtils.decrypt("9DqWB84slc+eh742SPz3lc/adeuXeX2pY9GmFzhM1+PGQOTdYBNBvsaYxvrTvS1ctgdm6VLOJFuVkHeQIt2aV75BBZvGVnqzLIG7snNSvB4HQyMiObfSU72ox6ji5sK3", Constant.DEFAULT_ENCRYPT_KEY));
	}
}
