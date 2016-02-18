package com.ucan.server.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ucan.server.mapper.UserInfoMapper;
import com.ucan.server.model.UserInfo;

/**
 * get:查询数据<br>
 * post:插入数据<br>
 * put:更新数据<br>
 * delete:删除数据
 * 
 * @author xuening
 * @since 2016-1-9
 */
@Repository("userInfoRepository")
public class UserInfoRepository {

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * 查询数据
	 * 
	 * @param userInfo
	 * @return List<UserInfo>
	 */
	public List<UserInfo> get(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}
		return userInfoMapper.select(userInfo);
	}

	/**
	 * 插入数据，null值不会被插入
	 * 
	 * @param userInfo
	 * @return int
	 */
	public int post(UserInfo userInfo) {
		if (userInfo == null) {
			return -1;
		}
		return userInfoMapper.insertSelective(userInfo);
	}

	/**
	 * 
	 * 根据主键来更新所有不为null的字段
	 * 
	 * @param userInfo
	 * @return int
	 */

	public int put(UserInfo userInfo) {
		if (userInfo == null || userInfo.getUid() == null || "".equals(userInfo.getUid())) {
			return -1;
		}
		return userInfoMapper.updateByPrimaryKey(userInfo);
	}

	/**
	 * 根据UserInfo删除数据，建议userInfo里仅存放uid，以便根据uid来删除数据
	 * 
	 * @param userInfo
	 * @return int
	 */

	public int delete(UserInfo userInfo) {
		if (userInfo == null) {
			return -1;
		}

		return userInfoMapper.delete(userInfo);
	}

}
