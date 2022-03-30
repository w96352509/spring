package com.gjun.middleware;

import java.io.IOException;

import java.util.Base64;
import java.util.Base64.Decoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//過濾Basic類型的Authorization(授權) 進行攔截
@Component
@Order(2)
public class TokenFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("2. TokenFilter 授權起來");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		//參照Header 的 Authorization
		String token = request.getHeader("Authorization"); //Basic Token 是 base64
		if (token!=null) {
			//切割字串 basic xxxxxxxx
			String[] items=token.split(" ");
			//Base64 獲取一個decode物件
			Base64.Decoder decorder=Base64.getDecoder();
			//Basic    ZXJpYzoxMjM0NQ==
			//items[0] items[1]
			byte[] decodeByte=decorder.decode(items[1].getBytes("UTF-8"));
			//得到內容EX: userpassword
			var txtString=new String(decodeByte,"UTF-8");
			System.out.println(txtString);			
			//切割取出使用者帳號與密碼
			//將設定字串用 : 隔開
			String[] data=txtString.split(":");
			//user :
			String userid=data[0];
			//password
			String password=data[1];
			//進行合法驗證 Database authorization 
			chain.doFilter(request, response);
		}
	}
	//設定 url 過濾
	@Bean
	public FilterRegistrationBean<TokenFilter> loggingFilter(){
		FilterRegistrationBean<TokenFilter> register = 
				new FilterRegistrationBean<>();
		TokenFilter tokenFilter = new TokenFilter();
		//設定filter
		register.setFilter(tokenFilter);
		//設定url
		register.addUrlPatterns("/iot/*");
		//register.addUrlPatterns("/customer/*");
		register.setOrder(2);
		return register;
	}
}
