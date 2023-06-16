package cn.ddcherry.springboot.demo.security.filter;

import cn.ddcherry.springboot.demo.constant.AuthConstant;
import cn.ddcherry.springboot.demo.util.JwtUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证token有效性
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

	@Resource
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = getToken(request);
		if (StrUtil.isNotEmpty(token)) {
			// 从Token中获取username
			String username = JwtUtil.getUsernameFromToken(token);
			// 根据username获取用户信息
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			// 创建身份验证对象
			Authentication authentication
				= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			// 设置身份验证对象
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		// 过滤器链
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StrUtil.isNotEmpty(bearerToken) && bearerToken.startsWith(AuthConstant.AUTHORIZATION_BEARER)) {
			// 去掉令牌前缀
			return bearerToken.replace(AuthConstant.AUTHORIZATION_BEARER, StrUtil.EMPTY);
		}
		return null;
	}
}
