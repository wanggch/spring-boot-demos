package cn.ddcherry.springboot.demo.security.endpoint;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.api.ResultCode;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理未经身份验证或者身份验证失败的用户访问受保护资源时的行为
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
		response.setStatus(200);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONUtil.toJsonStr(Result.fail(ResultCode.UNAUTHORIZED, msg)));
	}
}
