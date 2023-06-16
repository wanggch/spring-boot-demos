package cn.ddcherry.springboot.demo.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;

	private boolean success;

	private T data;

	private String msg;

	private Result(ResultCode resultCode) {
		this(resultCode.code, null, resultCode.msg);
	}

	private Result(ResultCode resultCode, String msg) {
		this(resultCode.code, null, msg);
	}

	private Result(ResultCode resultCode, T data) {
		this(resultCode.code, data, resultCode.msg);
	}

	private Result(ResultCode success, T data, String msg) {
		this(success.code, data, msg);
	}

	private Result(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.success = ResultCode.SUCCESS.code == code;
	}

	public static <T> Result<T> success() {
		return new Result<>(ResultCode.SUCCESS);
	}

	public static <T> Result<T> success(String msg) {
		return new Result<>(ResultCode.SUCCESS, msg);
	}

	public static <T> Result<T> success(T data) {
		return new Result<>(ResultCode.SUCCESS, data);
	}

	public static <T> Result<T> success(T data, String msg) {
		return new Result<>(ResultCode.SUCCESS, data, msg);
	}

	public static <T> Result<T> fail(String msg) {
		return new Result<>(ResultCode.FAILURE, msg);
	}

	public static <T> Result<T> fail(ResultCode resultCode) {
		return new Result<>(resultCode.code, null, resultCode.msg);
	}

	public static <T> Result<T> fail(ResultCode resultCode, String msg) {
		return new Result<>(resultCode.code, null, msg);
	}

}
