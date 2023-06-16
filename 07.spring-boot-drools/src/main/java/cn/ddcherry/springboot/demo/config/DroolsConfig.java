package cn.ddcherry.springboot.demo.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

	private final KieServices kieServices = KieServices.Factory.get();

	@Bean
	public KieContainer kieContainer() {
		return kieServices.getKieClasspathContainer();
	}
}
