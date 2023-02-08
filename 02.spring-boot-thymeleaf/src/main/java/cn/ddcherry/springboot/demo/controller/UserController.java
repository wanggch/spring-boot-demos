package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/info")
	public String info(Model model) {
		User user = new User("001", "wangxiaocheng", "汪小成");
		model.addAttribute("user", user);
		return "userInfo";
	}
}
