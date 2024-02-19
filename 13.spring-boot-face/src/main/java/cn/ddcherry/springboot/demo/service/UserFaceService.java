package cn.ddcherry.springboot.demo.service;

import cn.ddcherry.springboot.demo.entity.UserFace;
import cn.ddcherry.springboot.demo.param.UserFaceAddParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserFaceService extends IService<UserFace> {

	boolean save(UserFaceAddParam param);

	UserFace compare(String img);
}
