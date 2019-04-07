package xyz.cincommon.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import xyz.cincommon.model.User;
import xyz.cincommon.utils.Md5;

@RestController
public class ShiroTestController {
	
	@RequestMapping("/index")
	@RequiresAuthentication
	public String index() {
		return "index";
	}
	
	// 演示角色为admin的人才可以访问的请求
	@RequestMapping("/admin")
	@RequiresRoles({"admin"})
	public String admin() {
		return "admin access success!";
	}
	// 演示拥有query权限的人才可以访问的请求
	@RequestMapping("/query")
	@RequiresPermissions({"query"})
	public String query() {
		return "query access success!";
	}
	
	// 演示只有拥有delete权限的人才可以访问的请求
	@RequestMapping("/delete")
	@RequiresPermissions({"delete"})
	public String delete() {
		return "delete access success!";
	}
	/**
	 * 用户登录
	 * @param username 账号
	 * @param password 密码
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("loginUser")
	public Map<String, Object> loginUser(@RequestParam("username") String username, @RequestParam("password")String password, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UsernamePasswordToken token = new UsernamePasswordToken(username, Md5.EncoderByMd5(password));
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		User user = (User) subject.getPrincipal();
		session.setAttribute("user", user);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("message", "success");
		result.put("user", user);
		return result;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return "logout success!";
	}
	@RequestMapping("logOn")
	public String logOn(@RequestParam("code")String code) {
		RestTemplate t = new RestTemplate();
		Map<String, String> var = new HashMap<>();
		var.put("appid", "wxe9609631e9dfe11a");
		var.put("secret", "633806dd61094662e88b26c48cc3320d");
		var.put("js_code", code);
		var.put("grant_type", "authorization_code");
		Map forObject = t.getForObject("https://api.weixin.qq.com/sns/jscode2session", Map.class, var);
		forObject.forEach((k, v) -> {
			System.out.println(k + ", " + v.toString());
		});
		return "";
	}
	
}
