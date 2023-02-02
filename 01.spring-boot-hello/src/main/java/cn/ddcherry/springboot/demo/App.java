package cn.ddcherry.springboot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class App {
	public static void main(String[] args) throws Exception {
		// 获取本机IP地址
		String ip = InetAddress.getLocalHost().getHostAddress();
		log.info("本机ip: {}", ip);

		ApplicationContext context = SpringApplication.run(App.class);
		System.out.println("Bean个数: " + context.getBeanDefinitionCount());
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		System.out.println("########## Bean列表 ##########");
		Arrays.asList(beanNames).forEach(System.out::println);
	}
}
