package cn.ddcherry.springboot.demo.service;

import cn.ddcherry.springboot.demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表 服务类
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
public interface RoleService extends IService<Role> {

	/**
	 * 根据登录账号获取角色列表
	 * @param username 登录账号
	 * @return /
	 */
	List<Role> findRolesByUsername(String username);

	/**
	 * 根据登录账号获取角色编码列表
	 * @param username 登录账号
	 * @return /
	 */
	List<String> findRoleCodesByUsername(String username);

}
