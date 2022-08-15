package com.backend.app.common.talk;

import java.io.IOException;
import java.sql.Timestamp;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.backend.app.model.Payload;

@Component
public class NCloudKakaoTalk {

	final String serviceID = "";
	final String alimtalkSignRequstUri = "https://sens.apigw.ntruss.com/alimtalk/v2/services/"+ serviceID +"/messages"; // 카카오 알림톡 request url
	final String alimtalkRequstUri = "/alimtalk/v2/services/"+ serviceID +"/messages"; // 카카오 알림톡 request url
	final String alimtalkAccessKey = ""; 
	final String alimtalkSecretKey = ""; 
	final String alimtalkPlusFriendId = "";
	
	// 카카오 알림톡 요청
	public String sendSensNcloudAlimTalk(Payload param) {

		String result = "";
		CloseableHttpClient httpClient = null;
		try {

			// [1] N클라우드에서 제공하는 각종 KEY값 설정함.
			String url = alimtalkRequstUri;
			String accessKey = alimtalkAccessKey;
			String secretKey = alimtalkSecretKey;
			String signRequestUri = alimtalkSignRequstUri;

			// [2] REST API 요청시 필요한 Sign값 생성
			String signArray[] = makeSignature(accessKey, secretKey, signRequestUri);

			// [3] HTTP 통신 객체 생성 및 헤더 설정
			httpClient = HttpClients.createDefault(); // http client 생성
			HttpPost httpPost = new HttpPost(url); // post 메서드와 URL 설정
			httpPost.setHeader("accept", "application/json; charset=UTF-8");
			httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
			httpPost.setHeader("x-ncp-iam-access-key", accessKey);
			httpPost.setHeader("x-ncp-apigw-timestamp", signArray[0]);
			httpPost.setHeader("x-ncp-apigw-signature-v2", signArray[1]);

			// [4] REST API 전송 값 설정
			JSONObject messages = new JSONObject();
			messages.put("countryCode", param.getString("countryCode"));
			messages.put("to", param.getString("to"));
			messages.put("content", param.getString("templateMsg"));
			
			JSONObject msgObj = new JSONObject();
			msgObj.put("templateCode", param.getString("templateCode"));
			msgObj.put("plusFriendId", alimtalkPlusFriendId);
			
			JSONArray messageArray = new JSONArray();
			messageArray.put(messages);
			msgObj.put("messages", messageArray);

			// [5] REST API 전송 값 HTTP 객체에 탑재함.
			httpPost.setEntity(new StringEntity(msgObj.toString(), "UTF-8"));

			// [6] REST API 호출
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

			// [7] 응답 결과 저장
			String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			
			result = json;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public String[] makeSignature(String accessKey, String secretKey, String url) {
		String[] result = new String[2];
		try {
			String stamp = new Timestamp(System.currentTimeMillis()).getTime() + "";
			String space = " "; // one space
			String newLine = "\n"; // new line
			String method = "POST"; // method
			String timestamp = stamp; // current timestamp (epoch)

			String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp).append(newLine)
					.append(accessKey).toString();

			SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			String encodeBase64String = Base64.encodeBase64String(rawHmac);

			result[0] = stamp;
			result[1] = encodeBase64String;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	// 인증키 생성하기 
	public String[] makeGetSignature(String accessKey, String secretKey, String url) {
		String[] result = new String[2];
		try {
			String stamp = new Timestamp(System.currentTimeMillis()).getTime() + "";
			String space = " "; // one space
			String newLine = "\n"; // new line
			String method = "GET"; // method
			String timestamp = stamp; // current timestamp (epoch)

			String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp).append(newLine)
					.append(accessKey).toString();

			SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			String encodeBase64String = Base64.encodeBase64String(rawHmac);

			result[0] = stamp;
			result[1] = encodeBase64String;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
