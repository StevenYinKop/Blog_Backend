package xyz.cincommon.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.cincommon.mapper.UserInfoMapper;
import xyz.cincommon.model.User;
import xyz.cincommon.service.UserService;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public User findByUsername(String username) {
		return userInfoMapper.findByUsername(username);
	}

	@Override
	public ReturnResult<String> logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnResult<User> login(LoginUserInfo loginUserInfo) {
		String username = loginUserInfo.getUsername();
		if (StringUtils.isBlank(username)) {
			return ReturnResult.error(CodeMsg.PARAMETER_ISNULL);
		}
		User user = userInfoMapper.findByUsername(username);
		String dbPassword = user.getPassword();
		String inputPassword = loginUserInfo.getPassword();
		if (StringUtils.equals(dbPassword, inputPassword)) {
			user.setPassword(null);
			return ReturnResult.success(user);
		}
		return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
	}

}
