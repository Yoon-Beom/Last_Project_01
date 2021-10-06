package com.spring.test.kakao;

import com.sun.tools.javac.util.List;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class KakaoAddr {
	private static String USER_INFO_KEY="c6177645afbb0dd936cf1039b0755eed";


	public Map<String, Object> getAddrPoint(Map<String, Object> map) throws Exception {
		System.out.println("KakaoAddr : getAddrPoint start");
		System.out.println("data : " + map.get("data"));

		Map<String, Object> resultMap = new HashMap();

		try {

			String url = (String) map.get("apiURL");	// url
			String parameter = "";						// param

			Map<String, String> dataMap = (Map<String, String>) map.get("data");

			//param셋팅
			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
				parameter += entry.getKey() + "=" + entry.getValue() + "&"; 
			}

			parameter = parameter.substring(0, parameter.length()-1);

			//HTTP RESPONSE 
			HttpResponse<JsonNode> response = Unirest.get(url + parameter)
					.header("Authorization", "KakaoAK " + USER_INFO_KEY) // 카카오 추가
					.asJson();

			resultMap.put("response", response.getBody().toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좌표 변환 실패");
		}

		System.out.println("KakaoAddr : getAddrPoint end");
		return resultMap;
	}

	public KakaoGeoRes getPoint(String address) throws Exception {
		System.out.println("KakaoAddr : getPoint start");
		Map<String, Object> paramMap = new HashMap(); //url 등등 넘길값
		Map dataMap = new HashMap();

		dataMap.put("query", address); // url ? 뒤에값

		paramMap.put("apiURL", "https://dapi.kakao.com/v2/local/search/address.json?");
		paramMap.put("data" , dataMap);

		Map returnMap = getAddrPoint(paramMap);
		
		String response = (String) returnMap.get("response");

		//변환 Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		// KakaoGeoRes
		KakaoGeoRes kakaoGeoRes = objectMapper.readValue(response, KakaoGeoRes.class);

		//DOCUMENTS 로 파싱? 안에 VO 값 추출!
		System.out.println(kakaoGeoRes.getDocuments().get(0).getY());
		System.out.println(kakaoGeoRes.getDocuments().get(0).getX());
		System.out.println("KakaoAddr : getPoint end");
		return kakaoGeoRes;
	}
}
