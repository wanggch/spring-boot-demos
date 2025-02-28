package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping("/info")
	public User getUserInfo() {
		User user = new User();
		user.setUsername("ddcherry");
		user.setPassword("ddcherry.cn");
		user.setPhone("13812345678");
		user.setIdCard("11010119900307211X");
		user.setName("汪小成");
		user.setEmail("wangxiaocheng@ddcherry.cn");
		user.setAddress("山东省济宁市任城区太白西路123号");
		return user;
	}
}
