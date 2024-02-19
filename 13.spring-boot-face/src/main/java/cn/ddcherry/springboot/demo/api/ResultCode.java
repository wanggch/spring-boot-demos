package cn.ddcherry.springboot.demo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

@Getter
@AllArgsConstructor
public enum ResultCode {

	/**
	 * 操作成功
	 */
	SUCCESS(HttpServletResponse.SC_OK, "操作成功"),

	/**
	 * 业务异常
	 */
	FAILURE(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "业务异常"),

	/**
	 * 请求未授权
	 */
	UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请求未授权"),

	/**
	 * 404 没找到请求
	 */
	NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 没找到请求"),

	/**
	 * 不支持当前请求方法
	 */
	METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "不支持当前请求方法"),

	/**
	 * 不支持当前媒体类型
	 */
	UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型"),

	/**
	 * 请求被拒绝
	 */
	REQUEST_FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "请求被拒绝"),

	/**
	 * 服务器异常
	 */
	INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常"),

	/**
	 * 缺少必要的请求参数
	 */
	PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "缺少必要的请求参数"),

	/**
	 * 请求参数类型错误
	 */
	PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数类型错误"),

	/**
	 * 请求参数绑定错误
	 */
	PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数绑定错误"),

	/**
	 * 参数校验失败
	 */
	PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数校验失败"),
	;

	/**
	 * 编码
	 */
	final int code;

	/**
	 * 信息描述
	 */
	final String msg;
}
