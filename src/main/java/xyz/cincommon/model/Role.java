package xyz.cincommon.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Role {
	private Integer rid;
	private String name;
	private Set<Permission> permissions = new HashSet<>();
	private Set<User> users = new HashSet<>();
}
