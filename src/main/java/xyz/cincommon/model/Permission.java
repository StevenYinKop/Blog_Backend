package xyz.cincommon.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4711278869422904781L;
	private Integer pid;
	private String name;
	private String url;
}
