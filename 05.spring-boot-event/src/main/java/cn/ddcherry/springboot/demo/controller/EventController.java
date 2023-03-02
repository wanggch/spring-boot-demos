package cn.ddcherry.springboot.demo.controller;

import cn.ddcherry.springboot.demo.publisher.CustomEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

	@Resource
	private CustomEventPublisher customEventPublisher;

	@GetMapping("/publish")
	public Map<String, Object> publish(@RequestParam("message") String message) {
		customEventPublisher.publish(message);

		Map<String, Object> resultMap = new HashMap<>(16);
		resultMap.put("data", "事件发布成功");
		resultMap.put("message", message);
		return resultMap;
	}
}
