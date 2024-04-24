package team.ybj.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 如果需要的话
        config.addAllowedOrigin("https://localhost:3000"); // 前端服务器的 HTTPS 地址
        config.addAllowedHeader("*"); // 允许所有的头信息
        config.addAllowedMethod("*"); // 允许所有的方法（GET,POST等）
        source.registerCorsConfiguration("/**", config); // 对所有路径应用这个配置
        return new CorsFilter(source);
    }
}
