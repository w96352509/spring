package com.gjun.controller.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
//http://localhost:8081/swagger-ui/index.html
@Configuration
public class OpenApiConfig {

	//部屬open API 物件
	@Bean
	public OpenAPI initOpenAPI() {
		return new OpenAPI().info(
				new Info()
				    .title("Eric 雲端服務")
				    .description("金融產訓")
				    .version("v1.0")
				);
	}
}
