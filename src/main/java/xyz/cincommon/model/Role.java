package xyz.cincommon.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2235541787529797481L;
	private Integer rid;
	private String name;
	private Set<Permission> permissions = new HashSet<>();
	private Set<User> users = new HashSet<>();
}
