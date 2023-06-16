package cn.ddcherry.springboot.demo.config;

import cn.ddcherry.springboot.demo.security.crypto.Sm4PasswordEncoder;
import cn.ddcherry.springboot.demo.security.filter.TokenFilter;
import cn.ddcherry.springboot.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Resource
	private UserDetailsServiceImpl userDetailsService;

	@Resource
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	/**
	 * 密码加密方式配置
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Sm4PasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 启用跨域资源共享（CORS）支持
		http.cors()
			.and()
			// 禁用跨站请求伪造（CSRF）保护
			.csrf().disable()
			// 配置异常处理和身份验证入口点
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			.and()
			// 配置会话管理和会话创建策略：不使用会话
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			// 配置请求授权规则
			.authorizeRequests().antMatchers("/api/test/**").permitAll()
			.antMatchers("/api/auth/**").permitAll()
			// 所有其他请求需要进行身份验证
			.anyRequest().authenticated();

		// 配置用户身份验证逻辑
		http.authenticationProvider(authenticationProvider());

		// 在UsernamePasswordAuthenticationFilter过滤器之前添加TokenFilter
		http.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
