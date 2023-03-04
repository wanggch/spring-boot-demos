package cn.ddcherry.springboot.demo.listener;

import cn.ddcherry.springboot.demo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Async
@Component
public class CustomAsyncEventListener implements ApplicationListener<CustomEvent> {
	@Override
	public void onApplicationEvent(CustomEvent event) {
		log.info("事件监听器（异步） - 收到消息：{}，开始处理。", event.getMessage());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("事件监听器（异步） - 处理完成！");
	}
}
