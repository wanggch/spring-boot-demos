package cn.ddcherry.springboot.demo.dao;

import cn.ddcherry.springboot.demo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色表 Mapper 接口
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
public interface RoleDao extends BaseMapper<Role> {

	/**
	 * 根据登录账号获取角色名称列表
	 * @param username 登录账号
	 * @return /
	 */
	List<Role> findRolesByUsername(String username);
}
