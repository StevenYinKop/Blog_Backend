package xyz.cincommon.vo;

public class ReturnResult<T> {
	private String message;
	private int retCode;
	private T data;
	private ReturnResult(T data) {
		this.retCode = 0;
		this.message = "成功";
		this.data = data;
	}
	private ReturnResult(CodeMsg cm) {
		if(cm == null){
			return;
		}
		this.retCode = cm.getRetCode();
		this.message = cm.getMessage();
	}
	/**
	 * 成功时候的调用
	 * @return
	 */
	public static <T> ReturnResult<T> success(T data){
		return new ReturnResult<T>(data);
	}
	/**
	 * 成功，不需要传入参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ReturnResult<T> success(){
		return (ReturnResult<T>) success("");
	}
	/**
	 * 失败时候的调用
	 * @return
	 */
	public static <T> ReturnResult<T> error(CodeMsg cm){
		return new ReturnResult<T>(cm);
	}
	/**
	 * 失败时候的调用,扩展消息参数
	 * @param cm
	 * @param msg
	 * @return
	 */
	public static <T> ReturnResult<T> error(CodeMsg cm,String msg){
		cm.setMessage(cm.getMessage()+"--"+msg);
		return new ReturnResult<T>(cm);
	}
	public T getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	public int getRetCode() {
		return retCode;
	}
	
}
