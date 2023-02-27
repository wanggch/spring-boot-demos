package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {

	@ApiOperation(value = "所有用户列表")
	@GetMapping("/all")
	public List<User> all() {
		return Arrays.asList(new User("001", "wxc", "嗨皮汪小成"), new User("002", "dd", "丁丁"));
	}
}
