package com.gjun.controller;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GjunCustomersController {

	//傳遞庫次編號 進行單一客戶資料查詢作業 參數傳遞採用QueryString
	@GetMapping(path = {"/customers/qry" , "/customers/qryid"} )
	private String customersQryById(String customerId) {
		//?customerId 完全相同 會自動接收
		//確認是否傳遞合法編號
		//透過注入的Data Soure(or DAO物件模組)進行查詢
		//將循結果序列化為json
		//找不道是否回應不同的json文件格式(不同HTTP - 400)
		return customerId;
	}
	
	@GetMapping(path = {"/customers/qry2" , "/customers/qryid2"} )
	private String customersQryById2(@RequestParam("cid")String customerId) {
		//?customerId 完全相同 會自動接收
		//確認是否傳遞合法編號
		//透過注入的Data Soure(or DAO物件模組)進行查詢
		//將循結果序列化為json
		//找不道是否回應不同的json文件格式(不同HTTP - 400)
		return customerId;
	}
	
	@GetMapping(path = {"/customers/customerid/country"} )
	private String customersQryById2(@RequestParam("cid")String customerId,
			@RequestParam(name ="country" , required = false)String country ) {
		//required 可以是空值
		//http://localhost:8080/customers/customerid/country?cid=2&country=1 
		//?customerId 完全相同 會自動接收
		//確認是否傳遞合法編號
		//透過注入的Data Soure(or DAO物件模組)進行查詢
		//將循結果序列化為json
		//找不道是否回應不同的json文件格式(不同HTTP - 400)
		return customerId+" "+country;
	}
	//傳遞參數 可以依照國家別 沒有傳遞預設為USA
	@GetMapping(path = {"/customers/country"} )
	private String customersQryByCountry(@RequestParam(name ="country" ,defaultValue = "USA" ,required = false)String country ) {
		//required 可以是空值
		//http://localhost:8080/customers/customerid/country?cid=2&country=1 
		//?customerId 完全相同 會自動接收
		//確認是否傳遞合法編號
		//透過注入的Data Soure(or DAO物件模組)進行查詢
		//將循結果序列化為json
		//找不道是否回應不同的json文件格式(不同HTTP - 400)
		return country;
	}
}
