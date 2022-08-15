package com.backend.app.business.common;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.backend.app.model.Payload;
import com.backend.app.model.PayloadList;

public class SuperService {
	
	// response code/msg
	protected String SUCCESS_CD = "success";
	protected String SUCCESS_MSG = "성공";
	
	protected String DEFAULT_ERROR_CD = "fail";
	protected String DEFAULT_ERROR_MSG = "실패";
	
	// db connection
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public Payload select(String key, Payload payload) {
		return sqlSessionTemplate.selectOne(key, payload);
	}
	
	public PayloadList<Payload> selectList(String key, Payload payload) {
		List<Object> list = sqlSessionTemplate.selectList(key, payload);
		return new PayloadList<Payload>(list);
	}
	
	public int insert(String key, Payload payload) {
		return sqlSessionTemplate.insert(key, payload);
	}

	public int update(String key, Payload payload) {
		return sqlSessionTemplate.update(key, payload);
	}

	public int delete(String key, Payload payload) {
		return sqlSessionTemplate.delete(key, payload);
	}
}
