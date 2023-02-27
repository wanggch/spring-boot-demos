package cn.ddcherry.springboot.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@AllArgsConstructor
@EnableSwagger2WebMvc
public class SwaggerConfig {

	private SwaggerProperties swaggerProperties;

	@Bean(value = "dockerBean")
	public Docket dockerBean() {
		//指定使用Swagger2规范
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			// 分组名称
			.groupName("1.0.0")
			.select()
			// 这里指定Controller扫描包路径
			.apis(RequestHandlerSelectors.basePackage("cn.ddcherry.springboot.demo.controller"))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title(swaggerProperties.getTitle())
			.description(swaggerProperties.getDescription())
			.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
			.contact(new Contact(swaggerProperties.getContract().getName(), swaggerProperties.getContract().getUrl(), swaggerProperties.getContract().getEmail()))
			.version(swaggerProperties.getVersion())
			.build();
	}
}
