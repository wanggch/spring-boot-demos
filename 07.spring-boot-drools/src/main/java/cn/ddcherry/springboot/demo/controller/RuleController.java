package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.entity.Rule;
import cn.ddcherry.springboot.demo.service.RuleService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/rule")
public class RuleController {

	@Resource
	private RuleService ruleService;

	@PostMapping("/save")
	public Object save(HttpServletRequest request) {
		String name = request.getParameter("name");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");

		Rule rule = new Rule();
		rule.setName(name);
		rule.setContent(getContent(multipartFile));
		rule.setCreateBy("wanggc");
		rule.setCreateTime(new Date());

		ruleService.save(rule);

		return Dict.create().set("msg", "添加成功。");
	}

	private String getContent(MultipartFile multipartFile) {
		String content = null;
		try (InputStream inputStream = multipartFile.getInputStream()) {
			content = IoUtil.read(inputStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error("读取文件内容失败，异常信息：", e);
		}
		return content;
	}

}
