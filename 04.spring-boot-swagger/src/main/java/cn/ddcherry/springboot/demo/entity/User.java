package cn.ddcherry.springboot.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("用户")
public class User {
	@ApiModelProperty("主键")
	private String id;
	@ApiModelProperty("用户编码")
	private String usercode;
	@ApiModelProperty("用户姓名")
	private String name;
}
