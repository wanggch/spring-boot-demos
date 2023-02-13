package cn.ddcherry.springboot.demo;

import cn.ddcherry.springboot.demo.dto.UserDto;
import cn.ddcherry.springboot.demo.entity.User;
import cn.ddcherry.springboot.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserMapperTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void testEntityToDto() {
		User user = new User("001", "user-001", "嗨皮汪小成");
		UserDto dto = userMapper.toDto(user);
		System.out.println(dto);
	}
}
