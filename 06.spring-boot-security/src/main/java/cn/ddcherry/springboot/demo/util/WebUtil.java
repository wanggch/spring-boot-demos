package cn.ddcherry.springboot.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class WebUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);

	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return Objects.nonNull(requestAttributes) ? ((ServletRequestAttributes) requestAttributes).getRequest() : null;
	}
}
