package xyz.cincommon.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ReturnResult<String> exceptionHandle(Exception e) {
		return ReturnResult.error(CodeMsg.SERVER_EXCEPTION, e.getMessage());
	}
}
