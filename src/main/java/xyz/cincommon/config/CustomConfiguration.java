package xyz.cincommon.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;
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
	public JasyptStringEncryptor jasyptStringEncryptor() {
		return new JasyptStringEncryptor();
	}

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
