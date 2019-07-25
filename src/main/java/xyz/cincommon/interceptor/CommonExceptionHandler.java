package xyz.cincommon.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@ControllerAdvice
public class CommonExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@ExceptionHandler(BlogException.class)
	public ReturnResult<String> exceptionHandle(BlogException e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		return ReturnResult.error(e.getCodeMsg(), e.getMessage());
	}

//	@ExceptionHandler(AuthorizationException.class)
//	public ReturnResult<String> exceptionHandle(AuthorizationException e) {
//		e.printStackTrace();
//		LOGGER.error(e.getMessage());
//		return ReturnResult.error(CodeMsg.SERVER_EXCEPTION, e.getMessage());
//	}
//	@ExceptionHandler(UnauthenticatedException.class)
//	public ReturnResult<String> exceptionHandle(UnauthenticatedException e) {
//		e.printStackTrace();
//		LOGGER.error(e.getMessage());
//		return ReturnResult.error(CodeMsg.SERVER_EXCEPTION, e.getMessage());
//	}
	@ExceptionHandler(Exception.class)
	public ReturnResult<String> exceptionHandle(Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		return ReturnResult.error(CodeMsg.SERVER_EXCEPTION, e.getMessage());
	}
}
