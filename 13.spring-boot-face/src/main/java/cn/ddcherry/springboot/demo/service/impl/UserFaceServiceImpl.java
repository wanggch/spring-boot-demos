package cn.ddcherry.springboot.demo.service.impl;

import cn.ddcherry.springboot.demo.dao.UserFaceDao;
import cn.ddcherry.springboot.demo.entity.UserFace;
import cn.ddcherry.springboot.demo.param.UserFaceAddParam;
import cn.ddcherry.springboot.demo.service.UserFaceService;
import cn.hutool.core.codec.Base64;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserFaceServiceImpl extends ServiceImpl<UserFaceDao, UserFace> implements UserFaceService {

	@Resource
	private FaceEngine faceEngine;

	@Override
	public boolean save(UserFaceAddParam param) {
		List<FaceInfo> faceInfoList = new ArrayList<>();
		// 将base64字符串转化为二进制数组
		byte [] bytes = Base64.decode(param.getImg());
		// 使用二进制数组创建虹软人脸识别图片信息类
		ImageInfo imageInfo = ImageFactory.getRGBData(bytes);
		// 提取人脸特征
		faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(),
			imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

		// 特征提取
		FaceFeature faceFeature = new FaceFeature();
		faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);

		UserFace userFace = new UserFace();
		userFace.setName(param.getName());
		userFace.setFeature(faceFeature.getFeatureData());
		return this.save(userFace);
	}

	@Override
	public UserFace compare(String img) {
		List<FaceInfo> faceInfoList = new ArrayList<>();
		// 将base64字符串转化为二进制数组
		byte [] bytes = Base64.decode(img);
		// 使用二进制数组创建虹软人脸识别图片信息类
		ImageInfo imageInfo = ImageFactory.getRGBData(bytes);
		// 提取人脸特征
		faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(),
			imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

		// 特征提取
		FaceFeature targetFaceFeature = new FaceFeature();
		faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), targetFaceFeature);
		// 获取所有的人脸特征
		List<UserFace> userFaceList = this.list();
		Optional<UserFace> optional = userFaceList.stream().filter(item -> {
			FaceFeature sourceFaceFeature = new FaceFeature();
			sourceFaceFeature.setFeatureData(item.getFeature());
			FaceSimilar faceSimilar = new FaceSimilar();
			faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
			// 获取相似值
			Integer similarValue = new BigDecimal(faceSimilar.getScore()).multiply(new BigDecimal(100)).intValue();
			return similarValue > 80;
		}).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
}
