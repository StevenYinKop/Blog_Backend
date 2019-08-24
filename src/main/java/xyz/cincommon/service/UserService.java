package xyz.cincommon.service;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpSession;

import xyz.cincommon.model.Role;
import xyz.cincommon.model.User;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;

public interface UserService {
	ReturnResult<String> logout();

	ReturnResult<User> login(LoginUserInfo loginUserInfo);
	
	Collection<Role> queryRoleByUser(User user);
	
	void checkCurrentUserRole(HttpSession session, Constant.Role ... roles);

	ReturnResult<String> verifyLogin();

	ReturnResult<Map<String, Object>> initDashboard(User user) throws Exception;

}
