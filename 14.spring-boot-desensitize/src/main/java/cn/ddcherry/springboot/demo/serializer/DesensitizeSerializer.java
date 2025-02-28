package cn.ddcherry.springboot.demo.serializer;

import cn.ddcherry.springboot.demo.annotation.Desensitize;
import cn.ddcherry.springboot.demo.enums.DesensitizeType;
import cn.ddcherry.springboot.demo.util.DesensitizeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * 自定义脱敏序列化器
 *
 * @author 汪小成
 */
public class DesensitizeSerializer extends JsonSerializer<String> implements ContextualSerializer {

	private DesensitizeType type;
	private String pattern;
	private String replacement;

	public DesensitizeSerializer() {
		this.type = null;
	}

	public DesensitizeSerializer(DesensitizeType type, String pattern, String replacement) {
		this.type = type;
		this.pattern = pattern;
		this.replacement = replacement;
	}

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (Objects.isNull(type)) {
			gen.writeString(value);
		} else if (value != null) {
			String desensitized = DesensitizeUtil.desensitize(value, type, pattern, replacement);
			gen.writeString(desensitized);
		} else {
			gen.writeNull();
		}
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
		Desensitize annotation = property.getAnnotation(Desensitize.class);
		if (annotation != null) {
			return new DesensitizeSerializer(
				annotation.type(),
				annotation.pattern(),
				annotation.replacement()
			);
		}
		return this;
	}
}
