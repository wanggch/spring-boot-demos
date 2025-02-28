package cn.ddcherry.springboot.demo.util;

import cn.ddcherry.springboot.demo.enums.DesensitizeType;
import cn.hutool.core.util.DesensitizedUtil;

/**
 * 脱敏工具类，提供根据不同类型对字符串进行脱敏处理的方法。
 */
public class DesensitizeUtil {

	/**
	 * 根据指定的脱敏类型、正则表达式模式和替换字符串对输入值进行脱敏处理。
	 *
	 * @param value       要脱敏的字符串值。如果为 null 或空字符串，则直接返回该值。
	 * @param type        脱敏类型，由 DesensitizeType 枚举定义。
	 * @param pattern     自定义脱敏时使用的正则表达式模式。
	 * @param replacement 匹配到 pattern 后用于替换的字符串。
	 * @return 脱敏后的字符串。
	 */
	public static String desensitize(String value, DesensitizeType type,
									 String pattern, String replacement) {
		// 如果输入值为 null 或者为空字符串，直接返回该值
		if (value == null || value.isEmpty()) {
			return value;
		}
		// 根据不同的脱敏类型进行处理
		switch (type) {
			case PHONE:
				return DesensitizedUtil.mobilePhone(value);
			case ID_CARD:
				return DesensitizedUtil.idCardNum(value, 3, 4);
			case CHINESE_NAME:
				return DesensitizedUtil.chineseName(value);
			case EMAIL:
				return DesensitizedUtil.email(value);
			case CUSTOM:
				return desensitizeCustom(value, pattern, replacement);
			default:
				return value;
		}
	}

	/**
	 * 使用自定义的正则表达式模式和替换字符串对输入值进行脱敏处理。
	 *
	 * @param value       要脱敏的字符串值。
	 * @param pattern     自定义的正则表达式模式。
	 * @param replacement 匹配到 pattern 后用于替换的字符串。
	 * @return 脱敏后的字符串。
	 */
	private static String desensitizeCustom(String value, String pattern,
											String replacement) {
		if (pattern.isEmpty()) return value;
		return value.replaceAll(pattern, replacement);
	}
}
