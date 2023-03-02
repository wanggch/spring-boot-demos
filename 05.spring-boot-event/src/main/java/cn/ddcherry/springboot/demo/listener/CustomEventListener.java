package cn.ddcherry.springboot.demo.listener;

import cn.ddcherry.springboot.demo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {
	@Override
	public void onApplicationEvent(CustomEvent event) {
		log.info("事件监听器 - 收到消息：{}", event.getMessage());
	}
}
