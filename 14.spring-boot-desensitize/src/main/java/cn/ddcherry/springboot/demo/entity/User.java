package cn.ddcherry.springboot.demo.entity;

import cn.ddcherry.springboot.demo.annotation.Desensitize;
import cn.ddcherry.springboot.demo.enums.DesensitizeType;
import lombok.Data;

@Data
public class User {
	// 用户名
	private String username;
	// 密码
	private String password;
	// 姓名
	@Desensitize(type = DesensitizeType.CHINESE_NAME)
	private String name;
	// 手机号
	@Desensitize(type = DesensitizeType.PHONE)
	private String phone;
	// 邮箱
	@Desensitize(type = DesensitizeType.EMAIL)
	private String email;
	// 身份证号
	@Desensitize(type = DesensitizeType.ID_CARD)
	private String idCard;
	// 地址
	@Desensitize(type = DesensitizeType.CUSTOM, pattern = "(.{2,6}?(?:省|市)).*", replacement = "$1****")
	private String address;
}
