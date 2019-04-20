package xyz.cincommon.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.cincommon.model.User;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;


@RequestMapping("/auth")
@RestController
public class UserAuthController {
	@PostMapping("/login")
	public ReturnResult<Map<String, Object>> login(LoginUserInfo loginUserInfo) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginUserInfo.getUsername(), loginUserInfo.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            user.setPassword(null);
            Map<String, Object> result = new HashMap<>();
            result.put("userInfo", user);
            return ReturnResult.success(result);
        } catch (Exception e) {
        	return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
        }
	}
	@GetMapping("/logout")
	public ReturnResult<String> logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return ReturnResult.success("logout success!");
	}
}