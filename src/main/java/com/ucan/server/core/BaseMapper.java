package com.ucan.server.core;

import java.util.List;

import com.ucan.server.model.UserCore;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
	List<T> selectAsCustom(T t);

	int insertAsCustom(T t);

	int updateAsCustom(T t);

	int deleteAsCustom(T t);
}
