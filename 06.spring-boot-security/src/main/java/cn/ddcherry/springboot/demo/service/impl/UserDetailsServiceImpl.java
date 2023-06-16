package cn.ddcherry.springboot.demo.service.impl;

import cn.ddcherry.springboot.demo.entity.User;
import cn.ddcherry.springboot.demo.service.RoleService;
import cn.ddcherry.springboot.demo.service.UserService;
import cn.ddcherry.springboot.demo.security.model.AuthUser;
import cn.ddcherry.springboot.demo.util.WebUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = WebUtil.getRequest();
		// 从request中获取其它必要参数
		User user = userService.findByUsername(username);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("用户名或密码错误！");
		}
		List<String> roleCodeList = roleService.findRoleCodesByUsername(username);
		List<GrantedAuthority> authorities = roleCodeList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return new AuthUser(user.getId(), user.getRealName(), user.getAvatar(), user.getPhone(), user.getUsername(), user.getPassword(), authorities);
	}
}
