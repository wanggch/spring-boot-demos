package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.service.FlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "流程")
@RestController
@RequestMapping("/flow")
public class FlowController {

	@Resource
	private FlowService flowService;

	@PostMapping("/deploy")
	@ApiOperation(value = "上传部署流程文件", notes = "传入文件")
	public Result<?> deploy(@RequestParam List<MultipartFile> files,
							@RequestParam String category) {
		return Result.status(flowService.deploy(files, category));
	}

	@GetMapping("/getFlowDefinitionList")
	@ApiOperation(value = "查询流程定义列表", notes = "传入文件")
	public Result<?> getFlowDefinitionList() {
		List<ProcessDefinition> definitionList = flowService.getFlowDefinitionList();
//		return Result.success(definitionList.stream().map(ProcessDefinition::getName).collect(Collectors.toList()));
		return Result.success(definitionList);
	}

}
