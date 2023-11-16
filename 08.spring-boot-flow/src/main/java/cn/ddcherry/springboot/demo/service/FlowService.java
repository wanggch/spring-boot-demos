package cn.ddcherry.springboot.demo.service;

import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlowService {

	boolean deploy(List<MultipartFile> files, String category);

	/**
	 * 获取流程定义列表
	 * @return /
	 */
	List<ProcessDefinition> getFlowDefinitionList();
}
