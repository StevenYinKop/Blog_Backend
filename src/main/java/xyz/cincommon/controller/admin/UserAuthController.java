package xyz.cincommon.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.cincommon.model.User;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;


@RequestMapping("/admin/auth")
@RestController
public class UserAuthController {
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ReturnResult<User> login(@RequestBody LoginUserInfo loginUserInfo, HttpServletRequest request) {
		ReturnResult<User> result = userService.login(loginUserInfo);
		if(result.isOk()) {
			HttpSession session = request.getSession();
			session.setAttribute(Constant.CURRENT_USER, result.getData());
		}
		return result;
	}
	@GetMapping("/logout")
	public ReturnResult<String> logout(HttpSession session) {
		session.removeAttribute(Constant.CURRENT_USER);
		return userService.logout();
	}
	@GetMapping("/verifyLogin")
	public ReturnResult<String> verifyLogin(){
		return userService.verifyLogin();
	}
}