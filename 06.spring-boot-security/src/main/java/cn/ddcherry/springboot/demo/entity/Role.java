package cn.ddcherry.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表实体类
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* ID
	*/
	@TableId
	private String id;
	/**
	 * 编码
	 */
	private String code;
	/**
	* 名称
	*/
	private String name;
	/**
	* 描述
	*/
	private String description;
	/**
	* 创建者
	*/
	private String createBy;
	/**
	* 更新者
	*/
	private String updateBy;
	/**
	* 创建日期
	*/
	private LocalDateTime createTime;
	/**
	* 更新时间
	*/
	private LocalDateTime updateTime;
}
