package com.one.vo;

import java.io.Serializable;

/**
 * Result vo
 * 
 * @author jay
 * @since 2016-6-30
 */
public class ResultVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * code  0: ok
	 */
	private Integer code = 0;

	/**
	 * message
	 */
	private String msg = "ok";

	/**
	 * return data
	 */
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResultVo() {
		super();
	}

	public ResultVo(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultVo [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
