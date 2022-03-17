package com.gjun.controller;


import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Customers;
import com.gjun.domain.Message;

//客戶服務控制器(RESTful)
@RestController
public class NorCustomersController {
	//屬性注入Property Injection JdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Action(Method) 調出客戶所有資料 Request Method-GET
	//Produces-Response Header Content-Type:application/json (MIME)
	@GetMapping(path="/nor/customers/all/rawdata",produces="application/json")
	public List<Customers> AllCustomers() {
		DataSource datasource=jdbcTemplate.getDataSource();
		System.out.println(datasource.toString());
		//下SQL語法
		String sql="select CustomerID,CompanyName,Address,Phone,Country from Customers";
			List<Customers> result=jdbcTemplate.query(sql,
					//匿名類別(callback 找到每一筆Fetching 逐筆整理成封裝JavaBean物件)
//					new RowMapper<Customers>() {
//						
//						//逐筆
//						@Override
//						public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
//							//不要針對ResultSet next
//							Customers customers=new Customers();
//							customers.setCustomerId(rs.getString("CustomerID"));
//							customers.setCompanyName(rs.getString("CompanyName"));
//							customers.setAddress(rs.getString("Address"));
//							customers.setPhone(rs.getString("Phone"));
//							customers.setCountry(rs.getString("Country"));
//							
//							return customers;
//						}
//				
//			}
					//Lambda
					(rs,num)->{
						//不要針對ResultSet next
						Customers customers=new Customers();
						customers.setCustomerId(rs.getString("CustomerID"));
						customers.setCompanyName(rs.getString("CompanyName"));
						customers.setAddress(rs.getString("Address"));
						customers.setPhone(rs.getString("Phone"));
						customers.setCountry(rs.getString("Country"));
						
						return customers;
					}
					); 
		return result;
	}
    public List<Customers> AllCustomers2(){
		String sql="select CustomerID,CompanyName,Address,Phone,Country from Customers";
		List<Customers> result =jdbcTemplate.query(sql, new BeanPropertyRowMapper<Customers>(Customers.class));
		return result;
	}
	@GetMapping(path ="/nor/customers/all3/rawdata/" , produces="application/json")
	public List<Customers> AllCustomers3(){
		String sql="select CustomerID,CompanyName,Address,Phone,Country from Customers ";
		ResultSetExtractor<List<Customers>> resultSetExtractor
		= JdbcTemplateMapperFactory
		  .newInstance()
		  .addKeys("CustomerID") //主key
		  .newResultSetExtractor(Customers.class);
		return jdbcTemplate.query(sql,resultSetExtractor);
	}

	//採用path傳遞客戶編號 進行單筆查詢
	@GetMapping(path="/nor/customers/id/{cid}/rawdata",produces="application/json")
	public ResponseEntity<Object> customersById(@PathVariable("cid")String customerId) {
		//使用注入JdbcTemplate進行查詢
		try {
		Customers result=jdbcTemplate.queryForObject("Select CustomerID,CompanyName,Address,Phone,"
				+ "Country From Customers Where CustomerID=?",
				//查詢結果callback function point傳遞
				(rs,num)->{
					Customers customers=new Customers();
					customers.setCustomerId(rs.getString("CustomerID"));
					customers.setCompanyName(rs.getString("CompanyName"));
					customers.setAddress(rs.getString("Address"));
					customers.setPhone(rs.getString("Phone"));
					customers.setCountry(rs.getString("Country"));
					
					return customers;
				},
				new Object[] {customerId} // = customerId
				);
		//建構ResponseEntity
		return new ResponseEntity<>(result,HttpStatus.OK);
			
		}catch(DataAccessException ex) {
			Message msg=new Message();
			msg.setCode(400); //客製化訊息清單代號
			msg.setMsg("查無客戶資料");
			return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
		}
	}
	//可指定錯誤內容方法(new BeanPropertyRowMapper方法)
	@GetMapping(path = "/nor/customers/id/{cid}/rawdata2" , produces="application/json" )
	public ResponseEntity<Object> get2( @PathVariable("cid") String cid){
		try {
			String sql="select CustomerID,CompanyName,Address,Phone,Country from Customers Where CustomerID=? ";
			Customers customers = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Customers>(Customers.class) , cid);
			return new ResponseEntity<>(customers, HttpStatus.OK);	
		} catch (Exception e) {
			Message message = new Message();
			message.setCode(400);
			message.setMsg("查無客戶資料");
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	//直接回傳物件(new BeanPropertyRowMapper方法)
	@GetMapping(path = "/nor/customers/id/{cid}/rawdata3" , produces="application/json" )
	public Customers get3(@PathVariable("cid") String cid) {
		String sql="select CustomerID,CompanyName,Address,Phone,Country from Customers Where CustomerID=? ";
		Customers customers = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Customers>(Customers.class) , cid);
		return customers;
	}

	    
	//客戶新增(Http Body-rawdata-JSON) /Http Request Method-POST
	//consumes Request Header Content-Type:application/json
	//                                      接收                         回傳
	//客戶新增(Http Body-rawdata-JSON) /Http Request Method-POST
	//consumes Request Header Content-Type:application/json
	// postMan 記得調 json
	@PostMapping(path="/nor/customers/add",consumes="application/json;charset=UTF-8",produces="application/json;charset=UTF-8")
	public Customers customersAdd(@RequestBody Customers customers) {
			return customers;
	}
}