package cn.ddcherry.springboot.demo;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class FreemarkerApplication {
	public static void main(String[] args) throws Exception {
		// 获取本机IP地址
		String ip = InetAddress.getLocalHost().getHostAddress();
		ConfigurableApplicationContext applicationContext = SpringApplication.run(FreemarkerApplication.class, args);
		Environment env = applicationContext.getEnvironment();
		// 端口号
		String port = env.getProperty("server.port");
		log.info("地址：{}", StrUtil.format("http://{}:{}", ip, port));
	}
}
