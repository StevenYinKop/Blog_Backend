//package xyz.cincommon.interceptor;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
//
//public class CustomAuthenticationFilter extends FormAuthenticationFilter {
//	private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);
//
//	@Override
//	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//		if (isLoginRequest(request, response)) {
//			if (isLoginSubmission(request, response)) {
//				if (log.isTraceEnabled()) {
//					log.trace("Login submission detected.  Attempting to execute login.");
//				}
//				return executeLogin(request, response);
//			} else {
//				if (log.isTraceEnabled()) {
//					log.trace("Login page view.");
//				}
//				// allow them to see the login page ;)
//				return true;
//			}
//		} else {
//			if (log.isTraceEnabled()) {
//				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
//						+ "Authentication url [" + getLoginUrl() + "]");
//			}
//			saveRequest(request);
//			ReturnResult.error(CodeMsg.SESSION_NOT_EXSIST);
//			// throw new AuthorizationException("登录信息已失效!");
//			// saveRequestAndRedirectToLogin(request, response);
//			return false;
//		}
//		HttpServletResponse res = (HttpServletResponse)response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setStatus(HttpServletResponse.SC_OK);
//        res.setCharacterEncoding("UTF-8");
//        PrintWriter writer = res.getWriter();
//        Map<String, Object> map= new HashMap<>();
//        map.put("code", 702);
//        map.put("msg", "未登录");
//        writer.write();
//        writer.close();
//		throw new Exception(11+ "");
//	}
//}