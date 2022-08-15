package com.backend.app.business.coury.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.app.business.common.SuperService;
import com.backend.app.common.map.GoogleMap;
import com.backend.app.common.map.NCloudMap;
import com.backend.app.common.util.UtilMo;
import com.backend.app.model.Payload;
import com.backend.app.model.PayloadList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class CouryService extends SuperService{
    
	@Autowired
	GoogleMap googleMap;
	
	@Autowired
	NCloudMap ncloudMap;
	
	@Autowired
	UtilMo utilMo;
	
	public static String getRandomUpperString(int randomNumber) {
		Random random = new Random();
		String result = "";
		
		for (int i = 0; i < randomNumber; i++) {
			int choice = random.nextInt(2);
			
			// 대문자
			if (choice == 0) {
				char bch = (char) (random.nextInt(26) + 65);
				result += Character.toString(bch);
				
			}
			// 숫자
			else {
				result += random.nextInt(10);
			}
		}
		
		return result;
	}
	
	public Payload getCouryToMap(Payload request) {
	      Payload result = new Payload();
	      
	      try {
	         //1. 전체 배송 결과 
	         PayloadList<Payload> recv_addr_list = selectList("mybatis.coury.coury_mapper.selectCouryToMap", request);
	         result.set("recvAddrList", recv_addr_list);
	         
	         result.set("REPL_CD", SUCCESS_CD);
	         result.set("REPL_MSG", SUCCESS_MSG);
	         
	      } catch (Exception ex) {
	         result.set("REPL_CD", DEFAULT_ERROR_CD);
	         result.set("REPL_MSG", DEFAULT_ERROR_MSG);
	         ex.printStackTrace();
	      }
	      
	      return result;
	}	
	
	// 유저 배송 통계 데이터 가져오기
	public Payload getCouryResult(Payload request) {
	      Payload result = new Payload();
	      
	      try {
	         //1. 전체 배송 결과 
	         PayloadList<Payload> total = selectList("mybatis.coury.coury_mapper.selectCouryResult", request);
	         
	         // 2. 배송 완료 결과 
	         request.set("condition", "배송완료");
	         PayloadList<Payload> complete = selectList("mybatis.coury.coury_mapper.selectCouryResult",request);
	         
	         // 3. 분실/파손
	         request.set("condition", "분실/파손");
	         PayloadList<Payload> damage = selectList("mybatis.coury.coury_mapper.selectCouryResult", request);
	         
	         // 4. 오배송
	         request.set("condition", "오배송");
	         PayloadList<Payload> wrong = selectList("mybatis.coury.coury_mapper.selectCouryResult", request);
	         
	         result.set("total", total);
	         result.set("complete", complete);
	         result.set("damage", damage);
	         result.set("wrong", wrong);
	         
	         result.set("REPL_CD", SUCCESS_CD);
	         result.set("REPL_MSG", SUCCESS_MSG);
	         
	      } catch (Exception ex) {
	         result.set("REPL_CD", DEFAULT_ERROR_CD);
	         result.set("REPL_MSG", DEFAULT_ERROR_MSG);
	         ex.printStackTrace();
	      }
	      
	      return result;
	}	
	
	// 할당된 배송 목록 가져오기
	public Payload getAssignmentedCouryList(Payload request) {
		Payload result = new Payload();
		
		try {
			PayloadList<Payload> coury_list = selectList("mybatis.coury.coury_mapper.selectAssignmentedCouryList", request);
			request.set("couryList", coury_list);
			update("mybatis.coury.coury_mapper.updateCompletedCoury", request);
			
			result.set("REPL_CD", SUCCESS_CD);
			result.set("REPL_MSG", SUCCESS_MSG);
			
		} catch (Exception ex) {
			result.set("REPL_CD", DEFAULT_ERROR_CD);
			result.set("REPL_MSG", DEFAULT_ERROR_MSG);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	// 배송 완료 상태 변경(분실/파손, 오배송, 배송완료)
	public Payload updateCompletedCoury(Payload request) {
		Payload result = new Payload();
		
		try {
			PayloadList<Payload> coury_list = selectList("mybatis.coury.coury_mapper.selectAssignmentedCouryList", request);
			request.set("couryList", coury_list);
			update("mybatis.coury.coury_mapper.updateCompletedCoury", request);
			
			result.set("REPL_CD", SUCCESS_CD);
			result.set("REPL_MSG", SUCCESS_MSG);
			
		} catch (Exception ex) {
			result.set("REPL_CD", DEFAULT_ERROR_CD);
			result.set("REPL_MSG", DEFAULT_ERROR_MSG);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	// 확정
	public Payload confirmCouryList(Payload request) {
		Payload result = new Payload();
		
		try {
			PayloadList<Payload> coury_list = selectList("mybatis.coury.coury_mapper.selectAssignmentedCouryList", request);
			request.set("couryList", coury_list);
			update("mybatis.coury.coury_mapper.updateConfirmCouryList", request);
			
			result.set("REPL_CD", SUCCESS_CD);
			result.set("REPL_MSG", SUCCESS_MSG);
			
		} catch (Exception ex) {
			result.set("REPL_CD", DEFAULT_ERROR_CD);
			result.set("REPL_MSG", DEFAULT_ERROR_MSG);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	// 물량 자동 배정
	public Payload autoAssignmentCoury(Payload request) {
		Payload result = new Payload();
		
		try {
			
			int count = Integer.parseInt(request.getString("count"));
			request.set("count", count*3);
			
			System.out.println(request);
			PayloadList<Payload> coury_list = selectList("mybatis.coury.coury_mapper.autoAssignmentCoury", request);
			
			// 3개의 추천 리스트를 보여주기 위함
			// 무게 / 사이즈 /  교통 혼잡도 계산 등급 계산
			Payload first = new Payload();
			first.set("classSize", sum(coury_list.subList(0, count), "SIZE"));
			first.set("classWeight", sum(coury_list.subList(0, count), "WEIGHT"));
			getTraffic(request.getString("workTime"), first);
			
			Payload paramBox = new Payload();
			paramBox.set("couryList", coury_list.subList(0, count));
			String package_name = getRandomUpperString(10);
			paramBox.set("packageName", package_name);
			update("mybatis.coury.coury_mapper.updatePackageName", paramBox);
			first.set("packageName", package_name);
			
			Payload total = utilMo.getCharge(first, request.getString("workTime"));
			first.set("totalFee", total.getString("resultFee")); // 수수료 계산
			first.set("resultScore", total.getString("resultScore")); // 수수료 계산
			
			Payload mid = new Payload();
			mid.set("classSize", sum(coury_list.subList(count, count*2), "SIZE"));
			mid.set("classWeight", sum(coury_list.subList(count, count*2), "WEIGHT"));
			getTraffic(request.getString("workTime"), mid);
			
			paramBox.set("couryList", coury_list.subList(count, count*2));
			package_name = getRandomUpperString(10);
			paramBox.set("packageName", package_name);
			update("mybatis.coury.coury_mapper.updatePackageName", paramBox);
			mid.set("packageName", package_name);
			
			total = utilMo.getCharge(mid, request.getString("workTime"));
			mid.set("totalFee", total.getString("resultFee")); // 수수료 계산
			mid.set("resultScore", total.getString("resultScore")); // 수수료 계산
						
			Payload end = new Payload();
			end.set("classSize", sum(coury_list.subList(count*2, count*3), "SIZE"));
			end.set("classWeight", sum(coury_list.subList(count*2, count*3), "WEIGHT"));
			getTraffic(request.getString("workTime"), end);
			
			paramBox.set("couryList", coury_list.subList(count*2, count*3));
			package_name = getRandomUpperString(10);
			paramBox.set("packageName", package_name);
			update("mybatis.coury.coury_mapper.updatePackageName", paramBox);
			end.set("packageName", package_name);			

			total = utilMo.getCharge(end, request.getString("workTime"));
			end.set("totalFee", total.getString("resultFee")); // 수수료 계산
			end.set("resultScore", total.getString("resultScore")); // 수수료 계산
			
			result.set("first", first);
			result.set("mid", mid);
			result.set("end", end);
			
			result.set("REPL_CD", SUCCESS_CD);
			result.set("REPL_MSG", SUCCESS_MSG);
			
		} catch (Exception ex) {
			result.set("REPL_CD", DEFAULT_ERROR_CD);
			result.set("REPL_MSG", DEFAULT_ERROR_MSG);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	// 교통혼잡도 계산
	public void getTraffic(String work_time, Payload count) {
		// 출차시간 
		int pickup_time = 0;
		if(work_time.equals("주간")) {
			final int MIN = 7; 
			final int MAX = 20;
			pickup_time = (int) (Math.random() * (MAX - MIN)) + MIN;
		}
		else {
			final int MIN = 0; 
			final int MAX = 21;
			pickup_time = (int) (Math.random() * (MAX - MIN)) + MIN;
		}	
		count.set("pickupTime", pickup_time);
		
		// 교통혼잡도 등급화
		if(pickup_time == 8 || pickup_time == 18 || pickup_time == 17 || pickup_time == 16) {
			count.set("classTraffic", "D");
		}
		else if(pickup_time == 7 || pickup_time == 9 || pickup_time == 12 || pickup_time == 14) {
			count.set("classTraffic", "C");
		}
		else if(pickup_time == 10 || pickup_time == 11 || pickup_time == 15 || pickup_time == 19) {
			count.set("classTraffic", "B");
		}
		else {
			count.set("classTraffic", "A");
		}
	}
	
	// 무게, 사이즈 등급 측정
	public String sum(List<Payload> subList, String target) {
		int result = 0;
		
		for(Payload data : subList) {
			switch(data.getString(target)) {
			  case "A":
				 result += 4; 
			    break;
			  case "B":
				  result += 3;
			    break;
			  case "C":
				  result += 2;
			    break;
			  default: 
			  	result += 1;
			}
		}
		result = result / subList.size();
		
		switch(result) {
		  case 3:
			 return "A"; 
		  case 2:
			  return "B"; 
		  case 1:
			  return "C"; 
		  default: 
			  return "D"; 
		}
	}
	
	public Payload getDistance(Payload payload) {
		Payload result = new Payload();
		
		// 위치 좌표 구하기
		Payload start_location = getLocation(payload.getString("start"));
		Payload goal_location = getLocation(payload.getString("goal"));
	
		Payload param_box = new Payload();
		param_box.set("start", start_location);
		param_box.set("goal", goal_location);
		
		result = navigate(param_box);
		return result;
	}
	
	// 주소의 위도 경도 반환
	public Payload getLocation(String address) {
		Payload result = new Payload();
		
		String strResult = googleMap.getLocation(address);
		JsonObject jsonObj = new Gson().fromJson(strResult, new JsonObject().getClass());
		JsonArray jsonArr = jsonObj.get("results").getAsJsonArray();
		JsonObject jsonResult = jsonArr.get(0).getAsJsonObject();
		
		JsonObject geometry = jsonResult.get("geometry").getAsJsonObject();
		JsonObject location = geometry.get("location").getAsJsonObject();
		
		double latitude = location.get("lat").getAsDouble();
		double longitude = location.get("lng").getAsDouble();
	
		result.set("lat", latitude);
		result.set("lng", longitude);
		
		return result;
	}
	
	// 네비게이션
	public Payload navigate(Payload locations) {
		Payload result = new Payload();
		
		String strResult = ncloudMap.navigate(locations);
		
		JsonObject jsonObj = new Gson().fromJson(strResult, new JsonObject().getClass());
		JsonObject route = jsonObj.get("route").getAsJsonObject();
		JsonObject traoptimal = route.get("traoptimal").getAsJsonObject();
		JsonObject summary = traoptimal.get("summary").getAsJsonObject();
		
		// 총 이동 거리와 소요시간
		int distance = summary.get("distance").getAsInt();
		result.set("distance", distance);
		int duration = summary.get("duration").getAsInt();
		result.set("duration", duration);
		
		// 특정 거리마다 표시되는 위치좌표 리스트
		JsonObject path = jsonObj.get("path").getAsJsonObject();
		JsonArray pathArr = path.get("path").getAsJsonArray();
		
		// 거점은 전체 중에 5개만 가지고 옴
		int cnt = 5;
		int total_cnt = pathArr.size();
		int multi_idx = total_cnt/cnt;
		
		PayloadList<Payload> location_list = new PayloadList<Payload>();
		for(int i=0; i<cnt; i++) {
			Payload param = new Payload();
			
			JsonArray location = pathArr.get(i*multi_idx).getAsJsonArray();
			param.set("lat", location.get(0).getAsDouble());
			param.set("lng", location.get(1).getAsDouble());
			location_list.add(param);
		}
		
		result.set("locationList", location_list);	
		return result;
	}
	
}
