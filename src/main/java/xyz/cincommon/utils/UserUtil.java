package xyz.cincommon.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.model.User;
import xyz.cincommon.vo.CodeMsg;

public class UserUtil {

	public static void putUser(User user) {
		HttpSession session = getRequest().getSession();
		session.setAttribute(Constant.CURRENT_USER, user);
	}
	
	public static User getUser() {
		HttpSession session = getRequest().getSession();
		Object user = session.getAttribute(Constant.CURRENT_USER);
		if(null == user){
			throw new BlogException(CodeMsg.LOGIN_EXPIRED);
		}
		return (User) user;
	}
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
