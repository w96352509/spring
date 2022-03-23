package com.gjun.myservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
//在外設定 Origins 位置
public class WebCorsConfig implements WebMvcConfigurer {
    @Override
	public void addCorsMappings(CorsRegistry registry) {
    	//映射路徑
    	registry.addMapping("/**") // /**(全部)
    	//允許跨網域請求的來源
		.allowedOrigins("http://localhost:8080")
		.allowedHeaders("HeaderDemocontroller");
	}
}
