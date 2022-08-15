package com.backend.app.common.map;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.backend.app.model.Payload;

@Component
public class GoogleMap {

	final String API_KEY = "AIzaSyCRC_SErOTnA3MSlM_hIjXMLujSq3tI_co"; // 구글 맵 api 인증키 
	final String GOOGLE_REQUEST_URL = "https://maps.googleapis.com/maps/api/geocode/json";

	// 위치 좌표 구하기 
	public String getLocation(String address) {
		String result = "";
		CloseableHttpClient httpClient = null;
		
		try {
			
			// HTTP 통신 객체 생성 및 헤더 설정
			httpClient= HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(GOOGLE_REQUEST_URL);
			
			// 주소값 UTF-8 인코딩
			byte pAddress[] = address.getBytes();
			String encoding_address = new String(pAddress, "UTF-8");
			
			// URL build
			URI uri = new URIBuilder(request.getURI())
						.setParameter("address", encoding_address)
						.setParameter("key", API_KEY)
						.build();
			request.setURI(uri);

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
