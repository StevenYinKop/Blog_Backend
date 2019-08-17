package xyz.cincommon.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2544012210593415601L;

	private Integer rid;

    private Integer pid;
}