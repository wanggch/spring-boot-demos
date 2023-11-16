package cn.ddcherry.springboot.demo.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {
	private String title;
	private String description;
	private String termsOfServiceUrl;
	private String license;
	private String licenseUrl;
	private String version;
	private Contract contract = new Contract();

	@Data
	@NoArgsConstructor
	public static class Contract {
		private String name;
		private String url;
		private String email;
	}
}
