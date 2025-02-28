package cn.ddcherry.springboot.demo.enums;

import lombok.AllArgsConstructor;

/**
 * 脱敏类型
 *
 * @author 汪小成
 */
@AllArgsConstructor
public enum DesensitizeType {
	/**
	 * 身份证脱敏
	 */
	ID_CARD,

	/**
	 * 手机号脱敏
	 */
	PHONE,
	/**
	 * 中文名
	 */
	CHINESE_NAME,

	/**
	 * 邮箱
	 */
	EMAIL,
	/**
	 * 自定义
	 */
	CUSTOM
	;
}
