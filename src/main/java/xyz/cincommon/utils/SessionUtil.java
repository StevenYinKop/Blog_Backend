package xyz.cincommon.utils;

import javax.servlet.http.HttpServletRequest;

import xyz.cincommon.model.User;

public class SessionUtil {

	public static void putUser(HttpServletRequest request, User user) {
		request.setAttribute(Constant.CURRENT_USER, user);
	}
	
	public static User getUser(HttpServletRequest request) {
		return (User) request.getAttribute(Constant.CURRENT_USER);
	}
}
