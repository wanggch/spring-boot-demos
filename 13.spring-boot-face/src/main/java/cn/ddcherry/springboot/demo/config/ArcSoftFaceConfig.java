package cn.ddcherry.springboot.demo.config;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ArcSoftFaceConfig {

	@Value("${arcsoft.libPath}")
	private String libPath;
	@Value("${arcsoft.appId}")
	private String appId;
	@Value("${arcsoft.sdkKey}")
	private String sdkKey;

	@Bean
	public FaceEngine faceEngine() {
		// 创建引擎对象
		FaceEngine faceEngine = new FaceEngine(libPath);
		// 激活引擎
		int activeResultCode = faceEngine.activeOnline(appId, sdkKey);
		if (activeResultCode != ErrorInfo.MOK.getValue() && activeResultCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
			log.error("虹软人脸识别 | 激活失败！激活结果编码：{}", activeResultCode);
		}

		// 功能配置
		FunctionConfiguration functionConfiguration = FunctionConfiguration.builder().build();
		functionConfiguration.setSupportAge(true);
		functionConfiguration.setSupportFace3dAngle(true);
		functionConfiguration.setSupportFaceDetect(true);
		functionConfiguration.setSupportFaceRecognition(true);
		functionConfiguration.setSupportGender(true);
		functionConfiguration.setSupportLiveness(true);
		functionConfiguration.setSupportIRLiveness(true);

		// 引擎配置
		EngineConfiguration engineConfiguration = EngineConfiguration.builder().build();
		engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
		engineConfiguration.setFunctionConfiguration(functionConfiguration);

		faceEngine.init(engineConfiguration);

		return faceEngine;
	}
}
