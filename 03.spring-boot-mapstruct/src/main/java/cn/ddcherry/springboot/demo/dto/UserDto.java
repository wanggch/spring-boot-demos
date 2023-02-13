package cn.ddcherry.springboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
	private String id;
	private String usercode;
	private String name;
}
