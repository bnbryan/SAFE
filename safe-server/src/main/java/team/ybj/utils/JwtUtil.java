package team.ybj.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    public static String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

    }

    public static String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public static boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    // 解析 JWT，并返回包含 Claims 的 Jws 对象
    public static Jws<Claims> extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt);
    }

    public static <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt).getBody();
        return claimsResolver.apply(claims);
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    // 从 JWT 中解析 subject
    public static String getSubjectFromToken(String token) {
        return extractAllClaims(token).getBody().getSubject();
    }

    // 获取 Base64 编码的密钥字符串
    public static String getSecretKeyBase64() {
        return Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());
    }

}

