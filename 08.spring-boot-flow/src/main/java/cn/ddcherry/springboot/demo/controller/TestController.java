package cn.ddcherry.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/hello")
	public Object hello(String name) {
		String str = String.format("Hello %s.", name);
		Map<String, Object> resultMap = new HashMap<>(16);
		resultMap.put("msg", str);
		return resultMap;
	}

	@PostMapping("/bye")
	public Object bye(String name) {
		String str = String.format("Bye Bye, %s.", name);
		Map<String, Object> resultMap = new HashMap<>(16);
		resultMap.put("msg", str);
		return resultMap;
	}
}
