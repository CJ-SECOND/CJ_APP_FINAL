package com.backend.app.business.user.service;

import org.springframework.stereotype.Service;

import com.backend.app.business.common.SuperService;
import com.backend.app.model.Payload;

@Service
public class UserService extends SuperService {

	public Payload login(Payload request) {
	      Payload result = new Payload();
	      
	      try {
	         Payload user = select("mybatis.user.user_mapper.login", request);
	         
	         result.set("userSeq", user.get("USER_SEQUENCE").toString());
	         result.set("REPL_CD", SUCCESS_CD);
	         result.set("REPL_MSG", SUCCESS_MSG);
	         
	      } catch (Exception ex) {
	         result.set("REPL_CD", DEFAULT_ERROR_CD);
	         result.set("REPL_MSG", DEFAULT_ERROR_MSG);
	         ex.printStackTrace();
	      }
	      
	      return result;
	   }
	
	public Payload registerUser(Payload request) {
		Payload result = new Payload();
		
		try {
			
			insert("mybatis.user.user_mapper.registerUser", request);
			
			result.set("REPL_CD", SUCCESS_CD);
			result.set("REPL_MSG", SUCCESS_MSG);
			
		} catch (Exception ex) {
			result.set("REPL_CD", DEFAULT_ERROR_CD);
			result.set("REPL_MSG", DEFAULT_ERROR_MSG);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public Payload selectUserName(Payload request) {
		Payload result = new Payload();
		
		try {
			Payload userName = select("mybatis.user.user_mapper.selectUserName", request);
			
			result.set("username", userName);
			
			result.set("REPL_CD", SUCCESS_CD);
			result.set("REPL_MSG", SUCCESS_MSG);
			
		} catch (Exception ex) {
			result.set("REPL_CD", DEFAULT_ERROR_CD);
			result.set("REPL_MSG", DEFAULT_ERROR_MSG);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	
}
