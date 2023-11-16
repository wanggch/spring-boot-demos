package cn.ddcherry.springboot.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

//	@Bean
//	public FilterRegistrationBean<LoggingFilter> loggingFilterRegistrationBean() {
//		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//		registrationBean.setFilter(new LoggingFilter());
//		registrationBean.addUrlPatterns("/*");
//		registrationBean.setName("LogTraceFilter");
//		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
//		return registrationBean;
//	}

//	@Bean
//	public FilterRegistrationBean<CommonsRequestLoggingFilter> commonsRequestLoggingFilterFilterRegistrationBean() {
//		FilterRegistrationBean<CommonsRequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//		registrationBean.setFilter(commonsRequestLoggingFilter());
//		registrationBean.addUrlPatterns("/*");
//		registrationBean.setName("CommonsRequestLoggingFilter");
//		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
//		return registrationBean;
//	}

//	@Bean
//	public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
//		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//		filter.setIncludeQueryString(true);
//		filter.setIncludePayload(true);
//		filter.setMaxPayloadLength(10000);
//		filter.setIncludeHeaders(false);
//		filter.setAfterMessagePrefix("REQUEST DATA : ");
//		return filter;
//	}

}
