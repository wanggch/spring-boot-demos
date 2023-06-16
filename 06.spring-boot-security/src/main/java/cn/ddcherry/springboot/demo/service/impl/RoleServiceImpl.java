package cn.ddcherry.springboot.demo.service.impl;

import cn.ddcherry.springboot.demo.dao.RoleDao;
import cn.ddcherry.springboot.demo.entity.Role;
import cn.ddcherry.springboot.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表 服务实现类
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

	@Override
	public List<Role> findRolesByUsername(String username) {
		return baseMapper.findRolesByUsername(username);
	}

	@Override
	public List<String> findRoleCodesByUsername(String username) {
		List<Role> roles = this.findRolesByUsername(username);
		return roles.stream().map(Role::getCode).collect(Collectors.toList());
	}
}
