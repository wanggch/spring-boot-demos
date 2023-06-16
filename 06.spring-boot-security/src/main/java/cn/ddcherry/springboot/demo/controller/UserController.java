package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.entity.User;
import cn.ddcherry.springboot.demo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private UserService userService;

	@GetMapping("/list")
	public Result<List<User>> list() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		List<User> users = userService.list(queryWrapper);
		return Result.success(users);
	}
}
