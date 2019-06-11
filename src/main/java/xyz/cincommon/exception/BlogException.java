package xyz.cincommon.exception;

import xyz.cincommon.vo.CodeMsg;

public class BlogException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private CodeMsg codeMsg;
	public BlogException(CodeMsg codeMsg) {
		super();
		this.codeMsg = codeMsg;
	}
	
	public BlogException() {
		super();
	}

	public BlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BlogException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlogException(String message) {
		super(message);
	}

	public BlogException(Throwable cause) {
		super(cause);
	}

	public CodeMsg getCodeMsg() {
		return codeMsg;
	}
}
