package xyz.cincommon.exception;

import xyz.cincommon.vo.CodeMsg;

public class BlogException extends Exception {

	private static final long serialVersionUID = 1L;

	private CodeMsg codeMsg;
	public BlogException(CodeMsg codeMsg) {
		super();
		this.codeMsg = codeMsg;
	}
	public CodeMsg getCodeMsg() {
		return codeMsg;
	}
}
