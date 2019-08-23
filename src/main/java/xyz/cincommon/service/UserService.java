package xyz.cincommon.service;

import java.util.Collection;

import xyz.cincommon.model.Role;
import xyz.cincommon.model.User;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;

public interface UserService {
	/**
	 * 用户登出
	 * @return
	 */
	ReturnResult<String> logout();
	/**
	 * 用户登录
	 * @param loginUserInfo 账号密码, 登录时选择的角色
	 * @return
	 */
	ReturnResult<User> login(LoginUserInfo loginUserInfo);
	/**
	 * 查询当前用户的角色
	 * @param user 
	 * @return
	 */
	Collection<Role> queryRoleByUser(User user);
	/**
	 * Cin
	 * 2019/08/24
	 * @param session
	 * @param roles
	 */
	void checkCurrentUserRole(xyz.cincommon.utils.Constant.Role ... roles);
	ReturnResult<String> verifyLogin();

}
