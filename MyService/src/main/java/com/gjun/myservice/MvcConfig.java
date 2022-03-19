/*
package com.gjun.myservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	public MvcConfig() {
		//System.out.println("static files定義");
	}
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
	    {
	        "classpath:/resources/images/",
			"classpath:/resources/",
	        "classpath:/resources/static/", 
			"classpath:/resources/templates/"
	    };
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/**")
          .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        //System.out.println("static file path定義");
       
    }
}
*/