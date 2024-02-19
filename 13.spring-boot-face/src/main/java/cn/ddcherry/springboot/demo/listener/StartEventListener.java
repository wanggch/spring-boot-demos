package cn.ddcherry.springboot.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;

import java.net.InetAddress;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class StartEventListener {

	@Async
	@Order
	@EventListener(WebServerInitializedEvent.class)
	public void afterStart(WebServerInitializedEvent event) throws Exception {
		// 获取本机IP地址
		String ip = InetAddress.getLocalHost().getHostAddress();
		Environment environment = event.getApplicationContext().getEnvironment();
		String appName = environment.getProperty("spring.application.name");
		int localPort = event.getWebServer().getPort();
		log.info("---[{}]---项目启动完成，访问地址: http://{}:{}", appName, ip, localPort);
	}

}
