package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.api.Result;
import cn.ddcherry.springboot.demo.entity.UserFace;
import cn.ddcherry.springboot.demo.param.UserFaceAddParam;
import cn.ddcherry.springboot.demo.service.UserFaceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userFace")
public class UserFaceController {

	@Resource
	private UserFaceService userFaceService;

	@PostMapping("/save")
	public Result<?> save(@RequestBody UserFaceAddParam param) {
		userFaceService.save(param);
		return Result.success();
	}

	@PostMapping("/compare")
	public Result<?> compare(@RequestBody UserFaceAddParam param) {
		UserFace userFace = userFaceService.compare(param.getImg());
		return Result.success(userFace);
	}

}
