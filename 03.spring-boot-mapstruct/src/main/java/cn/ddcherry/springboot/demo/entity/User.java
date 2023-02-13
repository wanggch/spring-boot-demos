package cn.ddcherry.springboot.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String id;
	private String usercode;
	private String name;
}
