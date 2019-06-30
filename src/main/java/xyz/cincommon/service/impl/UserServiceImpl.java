package xyz.cincommon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.cincommon.mapper.UserInfoMapper;
import xyz.cincommon.model.User;
import xyz.cincommon.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public User findByUsername(String username) {
		return userInfoMapper.findByUsername(username);
	}

}
