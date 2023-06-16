package cn.ddcherry.springboot.demo.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

	/**
	 * 默认签名密钥
	 */
	private static final String DEFAULT_SECRET_KEY = "thisisasimpledemoicomefromshandongprovincejiningcityrenchengcountryanjuzhenhelloworld";

	public static String getBase64SecurityKey() {
		return Base64.getEncoder().encodeToString(DEFAULT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 生成 Token
	 */
	public static String createToken(String username, Map<String, Object> claims) {
		byte[] keyBytes = Decoders.BASE64.decode(getBase64SecurityKey());
		Key key = Keys.hmacShaKeyFor(keyBytes);
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.signWith(key, SignatureAlgorithm.HS256)
			.setClaims(claims)
			.compact();
	}

	/**
	 * 从 Token 中获取 username
	 */
	public static String getUsernameFromToken(String token) {
		if (StrUtil.isEmpty(token)) {
			return null;
		}
		try {
			byte[] keyBytes = Decoders.BASE64.decode(getBase64SecurityKey());
			Key key = Keys.hmacShaKeyFor(keyBytes);
			final Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
			if (Objects.isNull(claims) || StrUtil.isEmpty(claims.getSubject())) {
				return null;
			}
			return claims.getSubject();
		} catch (Exception e) {
			LOGGER.error("获取用户名称失败，异常信息：", e);
			return null;
		}
	}

}
