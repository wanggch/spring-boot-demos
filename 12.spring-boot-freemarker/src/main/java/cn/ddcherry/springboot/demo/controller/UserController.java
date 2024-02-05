package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.BloodPressure;
import cn.ddcherry.springboot.demo.entity.User;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@RequestMapping("/info")
	public Object info() throws Exception {
		User user = new User("001", "wangxiaocheng", "汪小成");
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("user.ftl");
		// 渲染结果
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, user);
		return content;
	}


	@RequestMapping("/bloodPressure")
	public void bloodPressure(HttpServletResponse response) throws Exception {
		BloodPressure bloodPressure = new BloodPressure(1, 50);
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("app.ftl");
		// 渲染结果
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, bloodPressure);
		log.info("渲染结果：{}", content);

		response.setContentType("text/javascript");
		response.getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
		response.flushBuffer();
	}
}
