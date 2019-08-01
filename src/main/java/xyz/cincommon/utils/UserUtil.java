package xyz.cincommon.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.model.User;
import xyz.cincommon.vo.CodeMsg;

public class UserUtil {

	public static void putUser(User user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute(Constant.CURRENT_USER, user);
	}
	
	public static User getUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object user = request.getAttribute(Constant.CURRENT_USER);
		if(null == user){
			throw new BlogException(CodeMsg.LOGIN_EXPIRED);
		}
		return (User) user;
	}
}
