package cn.ddcherry.springboot.demo.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {
	/**
	 * 用户ID
	 */
	private final String userId;
	/**
	 * 真实姓名
	 */
	private final String realName;
	/**
	 * 电话
	 */
	private final String phone;
	/**
	 * 头像
	 */
	private final String avatar;

	public AuthUser(String userId, String realName, String avatar, String phone, String username, String password,
					Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);
		this.userId = userId;
		this.realName = realName;
		this.avatar = avatar;
		this.phone = phone;
	}
}
