package com.gjun.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Account;

//個人帳戶資料維護與查詢
@CrossOrigin //(跨遇開發程式(系統))開放不同 port 使用
@RestController
public class AccountCintroller {

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
