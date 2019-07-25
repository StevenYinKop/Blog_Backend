package xyz.cincommon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.cincommon.model.User;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;


@RequestMapping("/auth")
@RestController
public class UserAuthController {
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ReturnResult<User> login(LoginUserInfo loginUserInfo, HttpServletRequest request) {
		ReturnResult<User> result = userService.login(loginUserInfo);
		if(result.isOk()) {
			request.setAttribute(Constant.CURRENT_USER, result.getData());
		}
		return result;
//        try {
////            subject.login(token);
////            User user = (User) subject.getPrincipal();
////            user.setPassword(null);
//            Map<String, Object> result = new HashMap<>();
//            result.put("userInfo", user);
//            return ReturnResult.success(result);
//        } catch (Exception e) {
//        	return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
//        }
	}
	@GetMapping("/logout")
	public ReturnResult<String> logout() {
		return userService.logout();
//		Subject subject = SecurityUtils.getSubject();
//		if (subject != null) {
//			subject.logout();
//		}
	}
}