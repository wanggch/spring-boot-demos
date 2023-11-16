package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.util.R;
import cn.ddcherry.springboot.demo.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/save")
	public R save(@Validated @RequestBody User user) {
		return R.ok(user);
	}
}
