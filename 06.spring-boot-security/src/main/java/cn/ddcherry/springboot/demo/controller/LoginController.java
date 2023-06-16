package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.security.model.AuthUser;
import cn.ddcherry.springboot.demo.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

	private final AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Result<Map<String, Object>> login(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = JwtUtil.createToken(username, new HashMap<>());
		AuthUser authUser = (AuthUser) authentication.getPrincipal();

		Map<String, Object> resultMap = new HashMap<>(16);
		resultMap.put("token", token);
		resultMap.put("user", authUser);

		return Result.success(resultMap);
	}
}
