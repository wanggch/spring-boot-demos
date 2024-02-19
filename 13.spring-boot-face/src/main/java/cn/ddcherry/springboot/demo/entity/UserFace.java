package cn.ddcherry.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户人脸
 *
 * @author wanggc
 * @since 2023-04-08 01:19:01
 */
@Data
@TableName("sys_user_face")
public class UserFace implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键id
	*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	* 姓名
	*/
	private String name;
	/**
	 * 特征值
	 */
	@JsonIgnore
	private byte[] feature;
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
