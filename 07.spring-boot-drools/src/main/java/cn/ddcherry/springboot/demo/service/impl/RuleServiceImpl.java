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
		Rule rule = getByName(name);

		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(rule.getContent(), ResourceType.DRL);
		KieSession kieSession = kieHelper.build().newKieSession();
		kieSession.fireAllRules();
		kieSession.dispose();
	}

	private Rule getByName(String name) {
		return getOne(Wrappers.<Rule>lambdaQuery().eq(Rule::getName, name));
	}

}
