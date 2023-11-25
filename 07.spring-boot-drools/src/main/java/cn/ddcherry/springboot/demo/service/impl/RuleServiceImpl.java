package cn.ddcherry.springboot.demo.service.impl;

import cn.ddcherry.springboot.demo.dao.RuleDao;
import cn.ddcherry.springboot.demo.entity.Rule;
import cn.ddcherry.springboot.demo.service.RuleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RuleServiceImpl extends ServiceImpl<RuleDao, Rule> implements RuleService {

	@Override
	public void fire(String name) {
		// 根据规则名称获取规则实体
		Rule rule = getByName(name);
		// 初始化KieHelper，用于构建规则
		KieHelper kieHelper = new KieHelper();
		// 设置规则内容
		kieHelper.addContent(rule.getContent(), ResourceType.DRL);
		// 创建KieSession对象
		KieSession kieSession = kieHelper.build().newKieSession();
		// 触发规则
		kieSession.fireAllRules();
		// 释放KieSession资源
		kieSession.dispose();
	}

	/**
	 * 根据规则名称获取规则实体
	 * @param name 规则名称
	 * @return /
	 */
	private Rule getByName(String name) {
		return getOne(Wrappers.<Rule>lambdaQuery().eq(Rule::getName, name));
	}

}
