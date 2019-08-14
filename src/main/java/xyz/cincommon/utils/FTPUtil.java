package xyz.cincommon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.cincommon.config.FTPConfigInfo;

public class FTPUtil {

	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

	public FTPUtil(String ip, int port, String user, String pass) {
		super();
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}

	private String ip;
	private int port;
	private String user;
	private String pass;
	private FTPClient ftpClient;

	public static String uploadFile(List<File> fileList) throws IOException {
		FTPConfigInfo ftpConfigInfo = SpringUtils.getBean(FTPConfigInfo.class);
		FTPUtil ftpUtil = new FTPUtil(ftpConfigInfo.getServerIp(), 23, ftpConfigInfo.getUser(), ftpConfigInfo.getPassword());
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String pwd = new StringBuffer().append("blog_img").append(Constant.SEPARATOR).append(year).append(Constant.SEPARATOR)
		.append(month).append(Constant.SEPARATOR).append(day).toString();
		boolean isUploaded = ftpUtil.uploadFile(pwd, fileList);
		if (isUploaded) {
			return pwd;
		}
		return null;
	}

	private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
		logger.info("remotePath: {}", remotePath);
		boolean uploaded = false;
		FileInputStream fis = null;
		if (connectServer(ip, port, user, pass)) {
			try {
				String[] directories = remotePath.split(Constant.SEPARATOR);
				for (String directory : directories) {
					if (StringUtils.isNotBlank(directory)) {
						ftpClient.makeDirectory(directory);
						ftpClient.changeWorkingDirectory(directory);
					}
				}
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				for (File fileItem : fileList) {
					fis = new FileInputStream(fileItem);
					uploaded = ftpClient.storeFile(fileItem.getName(), fis);
				}
			} catch (IOException e) {
				logger.error("上传文件异常", e);
				uploaded = false;
				e.printStackTrace();
			} finally {
				fis.close();
				ftpClient.disconnect();
			}
		} else {
			logger.error("ftp服务器登录异常!");
		}
		return uploaded;
	}

	private boolean connectServer(String ip, int port, String user, String pass) {
		ftpClient = new FTPClient();
		boolean success = false;
		try {
			ftpClient.connect(ip);
			success = ftpClient.login(user, pass);
		} catch (IOException e) {
			logger.error("服务器连接异常");
			e.printStackTrace();
		}
		return success;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}
}
