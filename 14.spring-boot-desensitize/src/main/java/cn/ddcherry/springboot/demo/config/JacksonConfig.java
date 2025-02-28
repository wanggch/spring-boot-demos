package cn.ddcherry.springboot.demo.config;

import cn.ddcherry.springboot.demo.serializer.DesensitizeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson配置类
 *
 * @author 汪小成
 */
@Configuration
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> {
			SimpleModule simpleModule = new SimpleModule();
			simpleModule.addSerializer(String.class, new DesensitizeSerializer());
			builder.modules(simpleModule);
		};
	}
}
