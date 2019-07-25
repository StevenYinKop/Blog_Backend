package xyz.cincommon.service;

import xyz.cincommon.model.User;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;

public interface UserService {
	ReturnResult<String> logout();

	ReturnResult<User> login(LoginUserInfo loginUserInfo);
}
