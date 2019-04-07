package xyz.cincommon.service;

import xyz.cincommon.model.User;

public interface UserService {
	User findByUsername(String username);
}
