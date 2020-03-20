package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestBuilder {

	static RequestSpecification reqBuilder;
	ResponseSpecification resBuilder;
	
	

	public RequestSpecification setUpBuilder() throws IOException {

		if(reqBuilder==null) {
		PrintStream stream = new PrintStream(new FileOutputStream("loggin.txt"));
		reqBuilder = new RequestSpecBuilder().
				setBaseUri(setUpEnvironment("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
		return reqBuilder;
		}
		
		else return reqBuilder;

	}
	
	public void setUpCreateResponse() {
		
		resBuilder = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
		
	}
	
	public String setUpEnvironment(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("src/test/java/resources/environment.properties");
		prop.load(file);
		return prop.getProperty(key);
		
	}

}
