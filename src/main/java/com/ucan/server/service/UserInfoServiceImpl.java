package com.ucan.server.service;

import java.security.Key;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucan.server.model.UserInfo;
import com.ucan.server.model.UserCore;
import com.ucan.server.repository.UserInfoRepository;
import com.ucan.server.utils.TokenUtil;
import com.ucan.server.utils.DigestUtils;

import ch.qos.logback.core.subst.Token;

@Service("userInfoService")
public class UserInfoServiceImpl {
	@Autowired
	private UserInfoRepository userInfoRepository;
 

}
