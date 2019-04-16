package xyz.cincommon.vo;

import java.util.HashMap;
import java.util.Map;

public class ReturnMapResult {
	private String message;
	private int retCode;
	private Map<String, Object> data;
	private ReturnMapResult(Map<String, Object> data) {
		this.retCode = 0;
		this.message = "success";
		this.data = data;
	}
	private ReturnMapResult() {
		this.retCode = 0;
		this.message = "success";
		this.data = new HashMap<>();
	}
	private ReturnMapResult(CodeMsg cm) {
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
	public static ReturnMapResult success(String key, Object value){
		ReturnMapResult result = new ReturnMapResult();
		result.put(key, value);
		return result;
	}
	public static ReturnMapResult success(Map<String, Object> map) {
		ReturnMapResult result = new ReturnMapResult();
		result.data = map;
		return result;
	}
	public ReturnMapResult put(String key, Object value) {
		data.put(key, value);
		return this;
	}
	/**
	 * 成功，不需要传入参数
	 * @return
	 */
	public static ReturnMapResult success(){
		return new ReturnMapResult();
	}
	/**
	 * 失败时候的调用
	 * @return
	 */
	public static ReturnMapResult error(CodeMsg cm){
		return new ReturnMapResult(cm);
	}
	/**
	 * 失败时候的调用,扩展消息参数
	 * @param cm
	 * @param msg
	 * @return
	 */
	public static ReturnMapResult error(CodeMsg cm,String msg){
		cm.setMessage(cm.getMessage()+"--"+msg);
		return new ReturnMapResult(cm);
	}
	public Map<String, Object> getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	public int getRetCode() {
		return retCode;
	}
	
}
