package xyz.cincommon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cincommon.ftp")
public class FTPConfigInfo {
	private String serverIp;
	private String port = "22";
	private String user;
	private String password;
	private String serverHttpPrefix;
}
