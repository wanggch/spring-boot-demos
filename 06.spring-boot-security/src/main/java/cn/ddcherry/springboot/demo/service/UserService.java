package cn.ddcherry.springboot.demo.service;

import cn.ddcherry.springboot.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户表 服务类
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
public interface UserService extends IService<User> {
	/**
	 * 根据登录账号获取用户信息
	 * @param username 登录账号
	 * @return /
	 */
	User findByUsername(String username);
}
