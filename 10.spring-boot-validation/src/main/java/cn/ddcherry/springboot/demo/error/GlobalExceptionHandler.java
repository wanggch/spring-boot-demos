package cn.ddcherry.springboot.demo.error;

import cn.ddcherry.springboot.demo.util.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public R handleError(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		return R.error(bindingResult.getFieldError().getDefaultMessage());
	}
}
