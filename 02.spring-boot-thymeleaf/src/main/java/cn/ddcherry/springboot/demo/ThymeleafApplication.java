package cn.ddcherry.springboot.demo;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class ThymeleafApplication {

	public static void main(String[] args) throws Exception {
		// 获取本机IP地址
		String ip = InetAddress.getLocalHost().getHostAddress();
		SpringApplication.run(ThymeleafApplication.class, args);
		log.info("地址：{}", StrUtil.format("http://{}:8002", ip));
	}
}
