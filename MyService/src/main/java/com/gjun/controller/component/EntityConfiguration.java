package com.gjun.controller.component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gjun.domain.AppInformation;

//佈署可以重複使用的spring Bean
@Configuration
public class EntityConfiguration {
	public EntityConfiguration() {
		System.out.println("Entity物件建構");
	}
	//生命週期??? Scope(範圍)
	@Bean("information")
	@Scope("singleton") //預設 singleton 選擇@Bean 物件產生類型
	public AppInformation getInformation() {
		//建構物件(Instance)
		AppInformation infor = new AppInformation();
		infor.setAddress("財務系統");
		infor.setAppName("版權所有");
		infor.setCopyLight("台北公園路");
		return infor;
	}
}
