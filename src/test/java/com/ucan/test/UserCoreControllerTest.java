package com.ucan.test;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.ucan.server.Application;
import com.ucan.server.model.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserCoreControllerTest {
	private Logger log = Logger.getLogger(UserCoreControllerTest.class);
	/*
	 * @Autowired private UserInfo userInfo;
	 */

	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/user/core";
	}

	@Test
	public void testUserLogin() throws Exception {

		// given().queryParam("account", "18701679240").queryParam("password",
		// "123")
		// .when().get("/userLogin")
	}

	@Test
	public void testUserRegister() throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount("123");
		File fi = new File("d:\\123\\eee.png");
		ObjectMapper oMapper = new ObjectMapper();
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("password", "222")
				.addHeader("account", "123").addHeader("fileName", "avatar.jpg")
				.addHeader("userInfo", URLEncoder.encode(oMapper.writeValueAsString(userInfo), "utf-8"))
				.addMultiPart("avatar", fi).build();
		given().spec(requestSpec).when().post("/userRegister");
	}
}
