package team.ybj.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtUtil {
    private static final String BASE64_SECRET = "7flxSYLeSerk8PMtrWlcmjxpJmZ4oSR2xrG7OB5kejc=";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(BASE64_SECRET));
    private static final long DEFAULT_TTL_MILLIS = 3600000; // 默认有效期设为1小时

    // 生成 JWT Token，仅使用 subject
    public static String generateToken(String subject) {
        return generateToken(subject, DEFAULT_TTL_MILLIS);
    }

    // 使用 subject 和自定义有效期生成 JWT Token
    public static String generateToken(String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SECRET_KEY)
                .setExpiration(new Date(nowMillis + ttlMillis))
                .compact();
    }

    // 解析 JWT，并返回包含 Claims 的 Jws 对象
    public static Jws<Claims> parseClaimsJws(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt);
    }

    // 从 JWT 中解析 subject
    public static String getSubjectFromToken(String token) {
        return parseClaimsJws(token).getBody().getSubject();
    }

    // 获取 Base64 编码的密钥字符串
    public static String getSecretKeyBase64() {
        return Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());
    }

}

