package cn.ddcherry.springboot.demo.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneValidator.class);
	private static final String REGEX = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$";

	@Override
	public boolean isValid(String s, ConstraintValidatorContext context) {
		boolean result = false;
		try {
			result = Pattern.matches(REGEX, s);
		} catch (Exception e) {
			LOGGER.error("验证手机号格式时发生异常，异常信息：", e);
		}
		return result;
	}
}
