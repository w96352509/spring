package com.gjun.middleware;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TransactionFilter implements Filter {

	public TransactionFilter() {
		System.out.println("Transaction Filter 部署啟動");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("1. Transaction Filter 攔截器來");
		//轉換 Http 請求與接收
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//操作Cookie 操作Session 操作Header 必須到Http
		//判斷是否傳遞一把apikey
		String apikey = req.getHeader("apikey");
		System.out.println(req.getRequestURI()); ///iot/dht/add
        System.out.println(apikey); //1234456
        //往下
        chain.doFilter(request, response);
	}
}
