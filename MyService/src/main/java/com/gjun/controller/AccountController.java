package com.gjun.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Account;

//個人帳戶資料維護與查詢
@CrossOrigin(origins = {"http://localhost:80"}  ) 
//(跨遇開發程式(系統))
@RestController
public class AccountController {

	//查詢特定帳戶
	@GetMapping(path = "/account/id/{accid}/rawdata" , produces = "application/json")
	public Account getAccount(@PathVariable("accid") String id) {
		Account account = new Account();
		account.setId(id);
		account.setBalance(10000);
		account.setBank("台灣銀行");
		account.setName("VIC");
		return account;
	}
	
}
