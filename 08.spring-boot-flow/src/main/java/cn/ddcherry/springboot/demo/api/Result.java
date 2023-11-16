package cn.ddcherry.springboot.demo.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "返回信息")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "状态码")
	private int code;

	@ApiModelProperty(value = "是否成功")
	private boolean success;

	@ApiModelProperty(value = "承载数据")
	private T data;

	@ApiModelProperty(value = "返回消息")
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

	public static <T> Result<T> success(ResultCode resultCode) {
		return new Result<>(resultCode.code, null, resultCode.msg);
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

	public static <T> Result<T> status(boolean flag) {
		return flag ? success(ResultCode.SUCCESS) : fail(ResultCode.FAILURE);
	}

}
