package xyz.cincommon.service.impl;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.mapper.UserInfoMapper;
import xyz.cincommon.model.Role;
import xyz.cincommon.model.User;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.utils.EncryptedUtil;
import xyz.cincommon.utils.UserUtil;
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
		return ReturnResult.success("Logout success");
	}

	@Override
	public ReturnResult<User> login(LoginUserInfo loginUserInfo) {
		String username = loginUserInfo.getUsername();
		if (StringUtils.isBlank(username)) {
			return ReturnResult.error(CodeMsg.PARAMETER_ISNULL);
		}
		User user = userInfoMapper.findByUsername(username);
		if (user == null) {
			return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
		}
		String dbPassword = user.getPassword();
		String inputPassword = loginUserInfo.getPassword();
		if (StringUtils.equals(EncryptedUtil.decrypt(dbPassword, Constant.DEFAULT_ENCRYPT_KEY), inputPassword)) {
			user.setPassword(null);
			return ReturnResult.success(user);
		}
		return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
	}

	@Override
	public void checkCurrentUserRole(Constant.Role ... roles) {
		User user = UserUtil.getUser();
		Collection<Role> dbRole = queryRoleByUser(user);
		boolean b = false;
		for (Role role : dbRole) {
			if (b) {
				break;
			}
			for (Constant.Role roleEnum : roles) {
				if (roleEnum.getId() == role.getRid()) {
					b = true;
					break;
				}
			}
		}
		if (!b) {
			throw new BlogException(CodeMsg.NOT_PERMISSION);
		}
	}
	@Override
	public Collection<Role> queryRoleByUser(User user) {
		User userRole = userInfoMapper.findByUsername(user.getUsername());
		Set<Role> roles = userRole.getRoles();
		return roles;
	}

	@Override
	public ReturnResult<String> verifyLogin() {
		User user = UserUtil.getUser();
		if (null == user) {
			throw new BlogException(CodeMsg.LOGIN_EXPIRED);
		}
		return ReturnResult.success();
	}
}
