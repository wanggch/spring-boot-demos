package cn.ddcherry.springboot.demo.publisher;

import cn.ddcherry.springboot.demo.event.CustomEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CustomEventPublisher {

	private ApplicationEventPublisher publisher;

	public void publish(String message) {
		CustomEvent customEvent = new CustomEvent(this, message);
		publisher.publishEvent(customEvent);
		log.info("事件发布成功 - 消息：{}", message);
	}
}
