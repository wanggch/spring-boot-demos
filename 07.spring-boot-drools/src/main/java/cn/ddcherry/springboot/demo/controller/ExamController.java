package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.Rule;
import cn.ddcherry.springboot.demo.fact.ExamData;
import cn.ddcherry.springboot.demo.service.RuleService;
import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/exam")
public class ExamController {

	@Resource
	private RuleService ruleService;

	@PostMapping("/getConclusion")
	public Object save(@RequestBody ExamData examData) {
		// 根据规则名称获取规则实体
		Rule rule = ruleService.getByName(examData.getRuleName());
		try {
			// 初始化KieHelper，用于构建规则
			KieHelper kieHelper = new KieHelper();
			// 设置规则内容
			kieHelper.addContent(rule.getContent(), ResourceType.DRL);
			// 创建KieSession对象
			KieSession kieSession = kieHelper.build().newKieSession();
			// 插入事实对象
			kieSession.insert(examData);
			// 触发规则
			kieSession.fireAllRules();
			// 释放KieSession资源
			kieSession.dispose();
		} catch (Exception e) {
			log.error("生成结论时发生异常，异常信息：", e);
		}
		Dict result = Dict.create();
		return Objects.isNull(examData.getConclusion()) ? result.set("msg", "生成结论失败！") : result.set("result", examData.getConclusion());
	}
}
