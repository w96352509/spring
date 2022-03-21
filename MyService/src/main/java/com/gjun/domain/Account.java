package com.gjun.domain;

import java.io.Serializable;

//帳戶資料
@SuppressWarnings("serial")
public class Account implements Serializable  {

	//Account
	private int balance;
	private String bank;
	private String name;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", bank=" + bank + ", name=" + name + ", id=" + id + "]";
	}
	
}
