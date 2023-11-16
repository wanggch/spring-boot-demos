package cn.ddcherry.springboot.demo.service.impl;

import cn.ddcherry.springboot.demo.service.FlowService;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowServiceImpl implements FlowService {

	private final RepositoryService repositoryService;

	@Override
	public boolean deploy(List<MultipartFile> files, String category) {
		files.forEach(file -> {
			try {
				String fileName = file.getOriginalFilename();
				InputStream fileInputStream = file.getInputStream();
				byte[] bytes = IoUtil.readBytes(fileInputStream);
				Deployment deployment = repositoryService.createDeployment().addBytes(fileName, bytes).deploy();
				deploy(deployment, category);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		});
		return true;
	}

	@Override
	public List<ProcessDefinition> getFlowDefinitionList() {
		return repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().list();
	}

	private void deploy(Deployment deployment, String category) {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
		StringBuilder logBuilder = new StringBuilder(500);
		List<Object> logArgs = new ArrayList<>();
		// 设置流程分类
		for (ProcessDefinition processDefinition : list) {
			if (StrUtil.isNotBlank(category)) {
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
			}
			logBuilder.append("部署成功，流程ID={} \n");
			logArgs.add(processDefinition.getId());
		}
		if (list.size() == 0) {
			log.error("部署失败，未找到流程！");
			throw new RuntimeException("部署失败，未找到流程！");
		} else {
			log.info(logBuilder.toString(), logArgs.toArray());
		}
	}
}
