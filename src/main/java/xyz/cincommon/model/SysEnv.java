package xyz.cincommon.model;

import java.io.Serializable;

public class SysEnv implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8844182196471447816L;

	private String envName;

    private String envValue;

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName == null ? null : envName.trim();
    }

    public String getEnvValue() {
        return envValue;
    }

    public void setEnvValue(String envValue) {
        this.envValue = envValue == null ? null : envValue.trim();
    }
}