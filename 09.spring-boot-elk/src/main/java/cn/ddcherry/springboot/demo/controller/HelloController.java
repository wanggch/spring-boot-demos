package cn.ddcherry.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String index() {
		log.info("Hello ELK.");
		return "Hello World";
	}
}
