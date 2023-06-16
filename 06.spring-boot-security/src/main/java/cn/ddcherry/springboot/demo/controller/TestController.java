package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.entity.User;
import cn.ddcherry.springboot.demo.service.UserService;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class TestController {

	private PasswordEncoder passwordEncoder;
	private UserService userService;

	@GetMapping("/hello")
	public Result<String> hello() {
		return Result.success("Hello World.");
	}

	@GetMapping("/encode")
	public Result<String> encode(String text) {
		if (StrUtil.isEmpty(text)) {
			return Result.success();
		} else {
			return Result.success(passwordEncoder.encode(text));
		}
	}

	@PostMapping("/addUser")
	public Result<String> addUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode("123456"));
		userService.save(user);
		return Result.success();
	}
}
