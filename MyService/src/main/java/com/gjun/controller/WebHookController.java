package com.gjun.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.SendData;
import com.gjun.domain.SendMessage;
import com.gjun.domain.WebHookData;
import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//訂閱Line Messaging API WebHook for Line Bot
//註冊Resurce file
@Tag(name="接收 LineBot WebHook API" , description = "掛勾在特定LineBot WebHook->推送使用者聊天訊息->進行Workflow->回應訊息")
@PropertySource(value = { "classpath:appurl.properties" })
@RestController
public class WebHookController {
	//注入Resource value
	@Value(value = "${line.sendpushmessage}")
	private String sendPushURL;
	@Value(value = "${line.token}")
	private String token;
	//接受訂閱資料進來(加入好友 封鎖 或者是傳送訊息....)
	@Operation(summary = "訂閱接收Line Messaging API 推播內容")
	@RequestMapping(path= {"/webhook/receive/rawdata"}
	,method= {RequestMethod.POST},consumes="application/json")
	public String receiveWebHook(@RequestBody()WebHookData data) {
		String retmsg = null;
		System.out.println(sendPushURL);
		//判斷類型(發送訊息 or 加入好友 或者是封鎖)
		String type=data.events[0].type;
		//取出user id
		String userid=data.events[0].source.userId;
		switch(type) {
		case "message":
			//聊天
			//判斷是文字
			if(data.events[0].message.type.equals("text")) {
				//取出訊息
				String message=data.events[0].message.text;
				//TODO 配合NLP(自然語言解析AI推測)
				//...
				//回應內容
				String content=String.format("晨晨說:%s",message); //得到message : 這是什麼
				//TODO 送訊息到Line Bot 推送到使用者端(已讀已回) send push message api
				//HttpClient
				//這裡以上是服務讀取
				//這裡以下回傳給服務的整理設定
				org.apache.http.client.HttpClient client=HttpClients.createDefault();
				//設定前端請求方法
				HttpPost request=new HttpPost();
				//設定URL
				try {
					request.setURI(new URI(sendPushURL));
					//設定Request物件的Header
					request.setHeader("Content-Type","application/json");
					//設定Token
					request.setHeader("Authorization",token);
					//設定Http Body 做這些設定才能讀到你配置Body
					//建構物件(值得配置)
					SendMessage msg=new SendMessage();
					msg.type="text";  //還傳型態
					msg.text=content; //還傳內容配置 下方處理content 中的 Message
					//窗口物件(顯示於窗口)
					//寄給誰 跟 內容配置content 中的 Message
					SendData sendData=new SendData();
					sendData.to=userid; //使用者
					sendData.messages=new SendMessage[] {msg}; //還傳內容配置的值
					//讀取部分:
					/*"message": {
		                "type": "text",
		                "id": "14353798921116",
		                "text": "這是什麼"
		            }*/					
					//序列化成Json String ㄒㄖ  
					Gson gson=new Gson();
					String sendJson=gson.toJson(sendData);
					//封裝成Entity
					request.setEntity(new StringEntity(sendJson,Charset.forName("UTF-8")));
					//執行request
					client.execute(request);
				} catch (URISyntaxException e) {
					retmsg = e.getMessage();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					retmsg = e.getMessage();
				} catch (IOException e) {
					retmsg = e.getMessage();
				}
				
			}
			break;
		case "follow":
			//加入好友 或者解除封鎖
			break;
		case "unfollow":
			//封鎖
			break;
		} 
		return retmsg;
		//WebHook 訂閱的服務位址 往往無須回應值
		
	}

}