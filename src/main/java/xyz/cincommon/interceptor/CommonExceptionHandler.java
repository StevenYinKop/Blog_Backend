package xyz.cincommon.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@RestControllerAdvice
public class CommonExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@ExceptionHandler(BlogException.class)
	public ReturnResult<String> exceptionHandle(BlogException e) {
		LOGGER.error("BlogException: {}", e);
		return ReturnResult.error(e.getCodeMsg());
	}

	@ExceptionHandler(Exception.class)
	public ReturnResult<String> exceptionHandle(Exception e) {
		LOGGER.error("Exception: {}", e);
		return ReturnResult.error(CodeMsg.SERVER_EXCEPTION, e.getMessage());
	}
}
