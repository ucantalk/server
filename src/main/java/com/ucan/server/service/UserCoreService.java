package com.ucan.server.service;

import com.ucan.server.model.UserCore;

public interface UserCoreService {
	String post(UserCore userCore) throws RuntimeException;
}
