package cn.ddcherry.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表实体类
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
@Data
@TableName("sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键id
	*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	* 登录账号
	*/
	private String username;
	/**
	* 真实姓名
	*/
	private String realName;
	/**
	* 密码
	*/
	private String password;
	/**
	* md5密码盐
	*/
	private String salt;
	/**
	* 头像
	*/
	private String avatar;
	/**
	* 生日
	*/
	private String birthday;
	/**
	* 性别(0-默认未知,1-男,2-女)
	*/
	private Integer sex;
	/**
	* 电子邮件
	*/
	private String email;
	/**
	* 电话
	*/
	private String phone;
	/**
	* 性别(1-正常,2-冻结)
	*/
	private Integer status;
	/**
	* 删除状态(0-正常,1-已删除)
	*/
	private Integer delFlag;
	/**
	* 创建人
	*/
	private String createBy;
	/**
	* 创建时间
	*/
	private LocalDateTime createTime;
	/**
	* 更新人
	*/
	private String updateBy;
	/**
	* 更新时间
	*/
	private LocalDateTime updateTime;

}
