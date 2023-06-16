package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.Rule;
import cn.ddcherry.springboot.demo.service.RuleService;
import cn.hutool.core.lang.Dict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/rule")
public class RuleController {

	@Resource
	private RuleService ruleService;

	@GetMapping("/hello")
	public Object hello() {
		ruleService.hello();
		return Dict.create().set("msg", "hello world");
	}

	@PostMapping("/save")
	public Object save(@RequestBody Rule rule) {
		rule.setCreateBy("wanggc");
		rule.setCreateTime(new Date());
		ruleService.save(rule);
		return Dict.create().set("msg", "添加成功。");
	}
}
