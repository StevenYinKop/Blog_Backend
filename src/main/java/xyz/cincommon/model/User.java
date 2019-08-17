package xyz.cincommon.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -277094511094640752L;
	private Integer uid;
	private String username;
	private String password;
	private String profile;
	private String avatarUrl;
	private String status;
	private Date createDate;
	private Date updateDate;
	private String updateUser;
	private Set<Role> roles = new HashSet<>();
}
