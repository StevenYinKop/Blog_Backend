package xyz.cincommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;

@Configuration
public class CustomConfiguration {

	@Bean
	public JasyptEncryptorConfigurationProperties jasyptEncryptorConfigurationProperties() {
		JasyptEncryptorConfigurationProperties jasyptEncryptorConfigurationProperties = new JasyptEncryptorConfigurationProperties();
		jasyptEncryptorConfigurationProperties.setBean("jasyptStringEncryptor");
		return jasyptEncryptorConfigurationProperties;
	}
	
	@Bean
	public JasyptStringEncryptor jasyptStringEncryptor () {
		return new JasyptStringEncryptor();
	}
}
