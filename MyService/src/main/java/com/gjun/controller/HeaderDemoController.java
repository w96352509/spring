package com.gjun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderDemoController {
	//透過參數注入Servlet api-HttpServletRequest 
	@GetMapping(path="/passing/header",produces="text/plain")
	public String getHeaders(HttpServletRequest request) {
		//Parames
		String who=request.getParameter("who");
		//Header
		String key=request.getHeader("apikey");
		return who+" "+key;
	}

}
