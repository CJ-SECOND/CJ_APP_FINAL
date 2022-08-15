package com.backend.app.common.util;

import org.springframework.stereotype.Component;

import com.backend.app.model.Payload;

@Component
public class UtilMo {

	// 수수료 계산
	public Payload getCharge(Payload payload, String workTime) {
		Payload result = new Payload();
		
		int basic_fee; // 최저 수수료
		int result_score; // 최종 수수료
		
		result_score = (getScoreByClass(payload.getString("classSize")) + getScoreByClass(payload.getString("classWeight"))
				+ getScoreByClass(payload.getString("classTraffic"))) / 3;
		
		// 주간일 때 수수료 계싼
		if(workTime.equals("주간")) 
			basic_fee = 800;			
		// 새벽/잔여물량일 때 수수료 계\
		else 
			basic_fee = 1000;
		
		int result_fee = basic_fee;
		
		// 최종 수수료 걔산
		switch (result_score) {
		case 3:
			break;
		case 2:
			result_fee = result_fee + 100;
			break;
		case 1:
			result_fee = result_fee + 200;
			break;
		default:
			result_fee = result_fee + 300;
			break;
		}
		result.set("resultFee", result_fee);
		result.set("resultScore", result_score);
		
		return result;
	}
	
	// 등급별 환산 점수 계산
	private int getScoreByClass(String class_) {
		if(class_.equals("A")) 
			return 4;
		else if(class_.equals("B"))
			return 3;
		else if(class_.equals("C"))
			return 2;
		else return 1;
	}
}
