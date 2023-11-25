package cn.ddcherry.springboot.demo.service;

import cn.ddcherry.springboot.demo.entity.Rule;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RuleService extends IService<Rule> {
	/**
	 * 根据规则名称触发指定规则
	 * @param name 规则名称
	 */
	void fire(String name);
}
