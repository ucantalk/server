package com.ucan.server.repository;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ucan.server.mapper.UserCoreMapper;
import com.ucan.server.model.UserCore;

import tk.mybatis.mapper.entity.Example;

/**
 * get:查询数据<br>
 * post:插入数据<br>
 * put:更新数据<br>
 * delete:删除数据
 * 
 * @author xuening
 * @since 2016-1-8
 */
@Repository("userCoreRepository")
public class UserCoreRepository {

	@Autowired
	private UserCoreMapper userCoreMapper;

	/**
	 * 查询数据
	 * 
	 * @param userCore
	 * @return List<UserCore>
	 */

	public List<UserCore> get(UserCore userCore) {
		if (userCore == null) {
			return null;
		}
		return userCoreMapper.selectAsCustom(userCore);
	}

	/**
	 * 
	 * 插入数据
	 * 
	 * @param userCore
	 * @return int
	 */

	public int post(UserCore userCore) {
		if (userCore == null) {
			return -1;
		}
		return userCoreMapper.insertSelective(userCore);
	}

	/**
	 * 根据主键来更新所有不为null的字段
	 * 
	 * @param userCore
	 * @return int
	 */

	public int put(UserCore userCore) {
		if (userCore == null || userCore.getUid() == null || "".equals(userCore.getUid())) {
			return -1;
		}
		return userCoreMapper.updateByPrimaryKeySelective(userCore);
	}

	/**
	 * 
	 * 根据UserCore删除数据，建议userCore里仅存放uid，以便根据uid来删除数据
	 * 
	 * @param userCore
	 * @return int
	 */

	public int delete(UserCore userCore) {
		if (userCore == null) {
			return -1;
		}

		return userCoreMapper.delete(userCore);
	}

}
