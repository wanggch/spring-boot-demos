package cn.ddcherry.springboot.demo.error;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 处理所有不可知的异常
	 */
	@ExceptionHandler(Throwable.class)
	public Result<?> handleException(Throwable e) {
		// 打印堆栈信息
		log.error("系统异常", e);
		return Result.fail(e.getMessage());
	}

	/**
	 * 处理所有接口数据验证异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("参数验证异常", e);
		FieldError error = e.getBindingResult().getFieldError();
		if (Objects.isNull(error)) {
			return Result.fail(ResultCode.PARAM_BIND_ERROR);
		} else {
			String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
			return Result.fail(ResultCode.PARAM_BIND_ERROR, message);
		}
	}

}
