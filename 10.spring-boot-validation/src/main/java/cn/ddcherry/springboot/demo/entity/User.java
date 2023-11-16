package cn.ddcherry.springboot.demo.entity;

import cn.ddcherry.springboot.demo.validator.Phone;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class User {

	@NotBlank(message = "用户姓名不能为空")
	private String name;

	@NotBlank(message = "密码不能为空")
	@Size(min = 6, message = "密码长度不能少于6位")
	private String password;

	@Min(value = 0, message = "年龄不能小于0岁")
	@Max(value = 150, message = "年龄不应超过150岁")
	private Integer age;

//	@Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$", message = "手机号格式不正确")
	@Phone
	private String phone;

}
