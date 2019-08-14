package xyz.cincommon.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtils {

	private static ApplicationContext context;
	
	public static void init(ApplicationContext ctx) {
		context = ctx;
	}
	
	public static Object getBean(String name) {
		return context.getBean(name);
	}
	
	public static <T> T getBean(Class<T> requiredType) {
		return context.getBean(requiredType);
	}
	
}
