package cn.ddcherry.springboot.demo.annotation;

import cn.ddcherry.springboot.demo.enums.DesensitizeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义脱敏注解
 *
 * @author 汪小成
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitize {
	/**
	 * 指定脱敏策略类型
	 * <p>必填参数，选择预定义的脱敏处理方式</p>
	 */
	DesensitizeType type();
	/**
	 * 自定义正则匹配模式
	 * <p>当type为CUSTOM时生效，需配合replacement使用</p>
	 */
	String pattern() default "";
	/**
	 * 替换字符
	 * <p>默认使用星号进行替换，可配置其他字符</p>
	 */
	String replacement() default "*";
}
