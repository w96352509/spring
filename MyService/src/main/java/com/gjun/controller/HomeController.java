package com.gjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//MVC控制器(經由端點,調用樣板下的.HTML)
@Controller
public class HomeController {

	@GetMapping(path = "/")
	public String Home() {
		return "index";
	}
	
}
