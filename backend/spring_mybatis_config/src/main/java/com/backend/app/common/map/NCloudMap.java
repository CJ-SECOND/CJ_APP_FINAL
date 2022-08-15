package com.backend.app.common.map;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.backend.app.model.Payload;

@Component
public class NCloudMap {
	
	final String DRIVIE_REQUEST_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving"; // ncloud 네비게이션 사용을 위한 요청 URL
	final String API_KEY_ID = "hwdocwymq1"; // ncloud api 인증키
	final String API_KEY = "JtRWnSdOgAMQJKHsPpNsEzGleRQdfuWyNJosdQqa"; // ncloud api 비밀키
	
	// NCldoud Diriving 
	public String navigate(Payload payload) {
		String result = "";
		CloseableHttpClient httpClient = null;
		
		try {
			// HTTP 통신 객체 생성 및 헤더 설정
			httpClient= HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(DRIVIE_REQUEST_URL);
			
			request.addHeader("X-NCP-APIGW-API-KEY-ID", API_KEY_ID);
			request.addHeader("X-NCP-APIGW-API-KEY", API_KEY);
			
			// 출발지, 목적지 세팅
			Payload start = (Payload) payload.get("start");
			Payload goal = (Payload) payload.get("goal");
			
			// 주소값 UTF-8 인코딩
			String comma = URLEncoder.encode(",", "UTF-8");
			
			String start_param = start.getString("lat") + comma + start.getString("lng");
			String goal_param = goal.getString("lat") + comma + goal.getString("lng");
			
			// URL build
			URI uri = new URIBuilder(request.getURI())
						.setParameter("start", start_param)
						.setParameter("goal", goal_param)
						.build();
			request.setURI(uri);
			
			System.out.println(start_param);
			System.out.println(goal_param);
			System.out.println(uri);
			
			CloseableHttpResponse httpResponse = httpClient.execute(request);
			
			result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
