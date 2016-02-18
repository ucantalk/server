package com.ucan.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucan.server.model.UserCore;
import com.ucan.server.repository.UserCoreRepository;
import com.ucan.server.utils.DateUtil;
import com.ucan.server.utils.DigestUtils;
import com.ucan.server.utils.TokenUtil;

@Service("userCoreService")
public class UserCoreServiceImpl implements UserCoreService {
	@Autowired
	private UserCoreRepository userCoreRepository;

	@Override
	public String post(UserCore userCore) throws RuntimeException {
		if (userCore.getAccount() == null || userCore.getAccount() == "") {
			throw new RuntimeException("在保存UserCore时发现错误：数据缺失account is null");
		}
		if (userCore.getPassword() == null || userCore.getAccount() == "") {
			throw new RuntimeException("在保存UserCore时发现错误：数据缺失password is null");
		}
		String uid = DigestUtils.composeUID();
		userCore.setUid(uid);
		userCore.setRole(1);
		userCore.setKey(TokenUtil.gInstance().getKey());
		userCore.setTimeStamp(DateUtil.getTimeStamp());
		try {
			userCore.setToken(TokenUtil.gInstance().composeToken(userCore));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userCoreRepository.post(userCore);
		return uid;
	}

}
