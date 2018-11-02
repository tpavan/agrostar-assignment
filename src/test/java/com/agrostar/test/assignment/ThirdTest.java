package com.agrostar.test.assignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.mapper.resolver.ObjectMapperResolver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class ThirdTest {

	static final String  APP_URL = "https://jsonplaceholder.typicode.com";
	static final String  BASE_PATH = "/posts";
	static final String  END_POINT ="/posts";
	static final int OK_STATUS_CODE = 200;
	static final int CREATED_STATUS_CODE = 201;
	
	static final int BAD_REQUEST_STATUS_CODE = 400;
	public Integer idContext = 0;

	static{	
		RestAssured.baseURI = APP_URL;
		RestAssured.basePath = BASE_PATH;
	}
	
	/**
	 * Validate HTTP status code on given API request	
	 */
	@Test(description = "Validate Http response code", alwaysRun=true)
	public void validateRequesHttpResponseCode(){
		givenGetRequest()
		.and()
		.assertThat()
		.statusCode(200);
	}
	
	
	/**
	 * Validate post request with HTTP status code
	 */
	@Test(description="Validate post request with HTTP status code", alwaysRun=true)
	public void validateResponseForPostRequest(){		
		 idContext = Integer.valueOf(RandomStringUtils.randomNumeric(4));
		 givenPostRequest(END_POINT, postRequestDataBody(idContext))
		.when()
		.contentType("application/json")
		.post()
		.then()
		.assertThat()
		.statusCode(ThirdTest.CREATED_STATUS_CODE);
	}
	
	
	/**
	 * Validate delete request
	 */
	@Test(description="Validate delete request", dependsOnMethods="validateResponseForPostRequest", alwaysRun=true)
	public void deleteRequest(){
		RestAssured.given()
		.contentType("application/json")
		.delete("/"+idContext+"");		
	}
	
	//@Test
	public void validateResponseOfGet(){
		
		Response mapJsonData = RestAssured.get(END_POINT);
		mapJsonData(mapJsonData.asString(),ResponseData.class);		
		JsonPath jsonEvaludator = RestAssured.get(END_POINT).jsonPath();
		
		List<ResponseData> reponse = getResponseContent(jsonEvaludator, "$.");
		
		for(ResponseData resp: reponse){
			if(true){
				
			}
		}
	}
	
	public <T> void mapJsonData(String reponse, Class<T> T){
		Gson gson = new Gson();
		gson.toJson(reponse, T);
		
	}
	
	public List<ResponseData> getResponseContent(JsonPath jsonEvaludator, String jsonPathExpression){
		return 	jsonEvaludator.getList(jsonPathExpression, ResponseData.class);
	}
	
	public  RequestSpecification givenPostRequest(String endpoint, JSONObject body){				
		return RestAssured.given()
				.contentType("application/json")
				.body(body.toMap());			
	}
	
	public  ValidatableResponse givenGetRequest(){		
		return RestAssured.get().then().log().everything();	
	}
	
	/**
	 * Post request body
 	 * @param id random integer value
	 * @return return JSONObject
	 */
	public JSONObject postRequestDataBody(int id){
		JSONObject jsonObj = new  JSONObject();
		jsonObj.put("userId", 1);
		jsonObj.put("id", id);
		jsonObj.put("title", "provident occaecati excepturi optio reprehenderit");
		jsonObj.put("body", "quia et suscipit\nsuscipit recusandae consequuntur");
		return jsonObj;
	}
}
