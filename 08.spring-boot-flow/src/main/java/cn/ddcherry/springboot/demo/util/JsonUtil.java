package cn.ddcherry.springboot.demo.util;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class JsonUtil {

	/**
	 * 将对象序列化成json字符串
	 * @param value javaBean
	 * @return jsonString json字符串
	 */
	public static <T> String toJson(T value) {
		try {
			return getInstance().writeValueAsString(value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将json反序列化成对象
	 * @param content content
	 * @param clazz   class
	 * @param <T>     T 泛型标记
	 * @return Bean
	 */
	public static <T> T parse(String content, Class<T> clazz) {
		try {
			return getInstance().readValue(content, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将json反序列化成对象
	 * @param content       content
	 * @param typeReference 泛型类型
	 * @param <T>           T 泛型标记
	 * @return Bean
	 */
	public static <T> T parse(String content, TypeReference<T> typeReference) {
		try {
			return getInstance().readValue(content, typeReference);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将json反序列化成对象
	 * @param in    InputStream
	 * @param clazz class
	 * @param <T>   T 泛型标记
	 * @return Bean
	 */
	public static <T> T parse(InputStream in, Class<T> clazz) {
		try {
			return getInstance().readValue(in, clazz);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将json反序列化成对象
	 * @param in            InputStream
	 * @param typeReference 泛型类型
	 * @param <T>           T 泛型标记
	 * @return Bean
	 */
	public static <T> T parse(InputStream in, TypeReference<T> typeReference) {
		try {
			return getInstance().readValue(in, typeReference);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 判断是否可以序列化
	 * @param value 对象
	 * @return 是否可以序列化
	 */
	public static boolean canSerialize(@Nullable Object value) {
		if (value == null) {
			return true;
		}
		return getInstance().canSerialize(value.getClass());
	}

	public static ObjectMapper getInstance() {
		return ObjectMapperHolder.INSTANCE;
	}

	private static class ObjectMapperHolder {
		private static final ObjectMapper INSTANCE = new CustomObjectMapper();
	}

	private static class CustomObjectMapper extends ObjectMapper {

		public CustomObjectMapper() {
			super();
			// 设置地点为中国
			super.setLocale(Locale.CHINA);
			// 去掉默认的时间戳格式
			super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			// 设置为中国上海时区
			super.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
			// 序列化时，日期的统一格式
			super.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN, Locale.CHINA));
			// 单引号
			super.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			// 允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
			super.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
			super.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
			super.findAndRegisterModules();
			// 失败处理
			super.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// 单引号处理
			super.configure(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature(), true);
			// 排除空值字段
			super.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			super.registerModule(javaTimeModule());
			super.findAndRegisterModules();
		}

		public CustomObjectMapper(ObjectMapper objectMapper) {
			super(objectMapper);
		}

		@Override
		public ObjectMapper copy() {
			return new CustomObjectMapper(this);
		}
	}

	private static JavaTimeModule javaTimeModule() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DatePattern.NORM_TIME_FORMATTER));
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DatePattern.NORM_TIME_FORMATTER));
		return javaTimeModule;
	}
}
