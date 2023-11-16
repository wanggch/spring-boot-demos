package cn.ddcherry.springboot.demo.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
	private int code;
	private boolean success;
	private T data;
	private String msg;

	private R(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.success = code == 200;
	}

	public static <T> R<T> ok(T data) {
		return new R<>(200, data, null);
	}

	public static <T> R<T> error(String msg) {
		return new R<>(500, null, msg);
	}
}
