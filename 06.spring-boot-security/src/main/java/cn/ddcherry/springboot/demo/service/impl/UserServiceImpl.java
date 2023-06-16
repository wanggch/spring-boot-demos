package cn.ddcherry.springboot.demo.service.impl;

import cn.ddcherry.springboot.demo.constant.UserConstant;
import cn.ddcherry.springboot.demo.dao.UserDao;
import cn.ddcherry.springboot.demo.entity.User;
import cn.ddcherry.springboot.demo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

	@Override
	@Cacheable(value = UserConstant.USER_CACHE, key = "#username")
	public User findByUsername(String username) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(User::getUsername, username);
		return baseMapper.selectOne(queryWrapper);
	}
}
