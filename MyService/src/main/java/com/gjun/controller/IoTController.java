package com.gjun.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.DHT;
import com.gjun.domain.DHTIoTData;
import com.gjun.domain.DhtData;
import com.gjun.domain.Message;
import com.google.gson.Gson;

//教室物聯網getway
@RestController
//註冊文件(帶入 各連結(post and get))
@PropertySource("classpath:appurl.properties") //Register Resource File
public class IoTController {
	
	//Property Injection(注入)
	@Value("${iot.posturl}")
	private String postURL;
	
	@RequestMapping(path="/iot/dht/add",method= {RequestMethod.POST}
	,consumes="application/json",produces="application/json")
	public String dhtSender(@RequestBody DhtData data) {
		//串接CHT IoT服務(HttpClient/Http Request/Header???/body JSON
		//url 是外部環境不可寫在程式裏面(防止改版)
		//                                        deviceID
		String urlString=String.format(postURL,"29010757883");
		//1.使用HttpClient相關工廠產生一個HttpClient物件
		//使用介面多型化 向前向後相容性考量
		HttpClient client=HttpClients.createDefault();
		HttpPost request=new HttpPost(); //Request物件
		try {
			//設定 url
			request.setURI(new URI(urlString));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3/25
		//Http Request Header(Content-Type:application/json and CK:xxxxxxx)
		//還傳型態
		request.setHeader("Content-Type","application/json");
		// Apikey=CK
		request.setHeader("CK","PKB23M3GAEFEYCHZPE");
		
		//整理 Body 部分
		//TODO 建構DHT  整理Http  Request Body HttpEntity(or Http Content)
		DHT dht=new DHT();
		dht.temper=data.getTemper();
		dht.humi=data.getHumi();
		dht.location=data.getLocation();
		//序列化成字串
		Gson gson=new Gson();
		String dhtString=gson.toJson(dht);
		//建構DHTIotData物件
		DHTIoTData dhtIot=new DHTIoTData();
		dhtIot.id="DHT22";
		dhtIot.save=true;
		
		
		dhtIot.value=new String[] {dhtString};
		ArrayList<DHTIoTData> list=new ArrayList<DHTIoTData>();
		list.add(dhtIot);
		String jsonData=gson.toJson(list);
		StringEntity entity;
		try {
			entity = new StringEntity(jsonData);
			request.setEntity(entity); //HttpEntity介面
			client.execute(request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonData;
		
	}

}
