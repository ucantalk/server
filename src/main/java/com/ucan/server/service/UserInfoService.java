package com.ucan.server.service;

import java.util.HashMap;

import com.ucan.server.model.UserInfo;

public interface UserInfoService {
	
	 boolean isUserExist(String account) throws Exception;
	 
	 UserInfo UserLogin(String account,String password ) throws Exception;
	 
	 HashMap<String, String> UserRegister(UserInfo userInfo,String password)throws Exception;
	 

}
