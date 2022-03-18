package com.gjun.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//提供庫戶資料的View
@Controller
public class UiCustomersController {

	//提供客戶編號查詢的view Page
	//Http Get
	@GetMapping(path = "/customers/qryid")
	public String customersQryId() {
		return "custqryid";
	}
	
}
