package cn.ddcherry.springboot.demo.aspect;

import cn.ddcherry.springboot.demo.util.HttpUtil;
import cn.ddcherry.springboot.demo.util.JsonUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 日志切面：用于记录请求头、请求参数、响应结果内容
 * @author wanggc
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

	// 切入点
	@Pointcut("(@within(org.springframework.stereotype.Controller) || " +
		"@within(org.springframework.web.bind.annotation.RestController))")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 开始处理时间
		long startTime = System.currentTimeMillis();
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = Objects.isNull(attributes) ? null : ((ServletRequestAttributes) attributes).getRequest();

		assert request != null;

		// 构建成一条长日志，避免并发下日志输出错乱
		StringBuilder webLog = new StringBuilder(300);
		// 日志参数
		List<Object> args = new ArrayList<>();

		webLog.append("\n================ Start  ================\n");
		// 打印路由
		webLog.append("===> {}: {}\n");
		String requestMethod = request.getMethod();
		args.add(requestMethod);
		args.add(request.getRequestURL().toString());
		// IP
		webLog.append("===> IP: {}\n");
		args.add(HttpUtil.getClientIp(request));
		// 打印调用 controller 的全路径以及执行方法
		webLog.append("===> Class Method: {}.{}\n");
		// 获取声明切点方法或构造函数的类型
		args.add(joinPoint.getSignature().getDeclaringTypeName());
		// 获取切点方法或构造函数的名称
		args.add(joinPoint.getSignature().getName());
		// 打印请求头
		logRequestHeaders(request, webLog, args);
		// 打印请求入参
		logRequestArgs(joinPoint, webLog, args);

		Object result = joinPoint.proceed();

		// 打印处理耗时
		webLog.append(StrUtil.format("处理耗时: {} ms \n", System.currentTimeMillis() - startTime));
		webLog.append("================ End  =================\n");
		// 打印
		log.info(webLog.toString(), args.toArray());

		return result;

//		try {
////			// 打印出参
////			webLog.append("Response: {}\n");
////			args.add(JsonUtil.toJson(result));
//
//			return result;
//		} finally {
//			// 打印处理耗时
//			webLog.append(StrUtil.format("处理耗时: {} ms \n", System.currentTimeMillis() - startTime));
//			webLog.append("================ End  =================\n");
//			// 打印
//			log.info(webLog.toString(), args.toArray());
//		}
	}

	private void logRequestHeaders(HttpServletRequest request, StringBuilder sb, List<Object> args) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			sb.append("=== Headers ===  {}: {}\n");
			String headerName = headerNames.nextElement();
			args.add(headerName);
			args.add(StrUtil.join("", request.getHeader(headerName)));
		}
	}

	/**
	 * 记录请求参数
	 * @param point       切点
	 * @param webLog      日志字符串
	 * @param requestArgs 日志参数
	 */
	private void logRequestArgs(ProceedingJoinPoint point, StringBuilder webLog, List<Object> requestArgs) {
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		Object[] args = point.getArgs();
		// 请求参数处理
		final Map<String, Object> paraMap = new HashMap<>(16);
		// 一次请求只能有一个 request body
		Object requestBodyValue = null;
		for (int i = 0; i < args.length; i++) {
			// 读取方法参数
			MethodParameter methodParam = new SynthesizingMethodParameter(method, i);
			methodParam.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
			// PathVariable 参数跳过
			PathVariable pathVariable = methodParam.getParameterAnnotation(PathVariable.class);
			if (pathVariable != null) {
				continue;
			}
			RequestBody requestBody = methodParam.getParameterAnnotation(RequestBody.class);
			String parameterName = methodParam.getParameterName();
			Object value = args[i];
			// 如果是body的json则是对象
			if (requestBody != null) {
				requestBodyValue = value;
				continue;
			}
			// 处理 参数
			if (value instanceof HttpServletRequest) {
				paraMap.putAll(((HttpServletRequest) value).getParameterMap());
				continue;
			} else if (value instanceof WebRequest) {
				paraMap.putAll(((WebRequest) value).getParameterMap());
				continue;
			} else if (value instanceof HttpServletResponse) {
				continue;
			} else if (value instanceof MultipartFile) {
				MultipartFile multipartFile = (MultipartFile) value;
				String name = multipartFile.getName();
				String fileName = multipartFile.getOriginalFilename();
				paraMap.put(name, fileName);
				continue;
			} else if (value instanceof MultipartFile[]) {
				MultipartFile[] arr = (MultipartFile[]) value;
				if (arr.length == 0) {
					continue;
				}
				String name = arr[0].getName();
				StringBuilder sb = new StringBuilder(arr.length);
				for (MultipartFile multipartFile : arr) {
					sb.append(multipartFile.getOriginalFilename());
					sb.append(StrUtil.COMMA);
				}
				paraMap.put(name, StrUtil.removeSuffix(sb.toString(), StrUtil.COMMA));
				continue;
			} else if (value instanceof List) {
				List<?> list = (List<?>) value;
				AtomicBoolean isSkip = new AtomicBoolean(false);
				for (Object o : list) {
					if ("StandardMultipartFile".equalsIgnoreCase(o.getClass().getSimpleName())) {
						isSkip.set(true);
						break;
					}
				}
				if (isSkip.get()) {
					paraMap.put(parameterName, "此参数不能序列化为json");
					continue;
				}
			}
			// 参数名
			RequestParam requestParam = methodParam.getParameterAnnotation(RequestParam.class);
			String paraName = parameterName;
			if (requestParam != null && StrUtil.isNotBlank(requestParam.value())) {
				paraName = requestParam.value();
			}
			if (value == null) {
				paraMap.put(paraName, null);
			} else if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
				paraMap.put(paraName, value);
			} else if (value instanceof InputStream) {
				paraMap.put(paraName, "InputStream");
			} else if (value instanceof InputStreamSource) {
				paraMap.put(paraName, "InputStreamSource");
			} else if (JsonUtil.canSerialize(value.getClass())) {
				// 判断模型能被 json 序列化，则添加
				paraMap.put(paraName, value);
			} else {
				paraMap.put(paraName, "此参数不能序列化为json");
			}
		}
		// 请求参数
		if (paraMap.isEmpty()) {
			webLog.append("\n");
		} else {
			webLog.append("arameters: {}\n");
			requestArgs.add(JsonUtil.toJson(paraMap));
		}
		if (requestBodyValue != null) {
			webLog.append("====Body=====  {}\n");
			requestArgs.add(JsonUtil.toJson(requestBodyValue));
		}
	}
}
