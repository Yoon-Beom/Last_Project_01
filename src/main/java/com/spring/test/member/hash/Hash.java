package com.spring.test.member.hash;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class Hash {
	private static final int SALT_SIZE = 16;
	
	// SALT 값 생성
	public String getSALT() throws Exception {
		SecureRandom rand = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rand.nextBytes(temp);
		return Byte_to_String(temp);
	}
	/* 
	 	임의의 문자열을 생성해주는 솔트 생성 역할을 한다. 
	 	일반적인 Random 클래스와 사용방법은 크게 다르지 않다. 
	 	임의의 바이트 배열을 생성한 뒤, 
	 	nextBytes() 에 해당 배열을 넣어, 
	 	바이트 배열이 임의의 값들로 채워지도록 하는 것이다. 
	 	그렇게 채워진 배열이 바로 솔트가 되는 것이고, 
	 	해당 배열을 반환해준다.
	 */
	
	// 바이트값을 16진수로 변경
	private String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		
		return sb.toString();
	}
	// byte 배열을 String 으로 변환해주는 역할
	
	// pwd Hashing
	public String Hashing(byte[] pwd, String salt) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		// key-stretching
		for (int i = 0; i < 10000; i++ ) {
			String temp = Byte_to_String(pwd) + salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성
			md.update(temp.getBytes());					// temp 의 문자열을 해싱하여 md 에 저장해둔다
			pwd = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다
		}
		
		return Byte_to_String(pwd);
	}
	/*
		입력받은 비밀번호와 Salt 를 합쳐서 여러번의 해싱을 거쳐 최종 다이제스트를 보내는 역할을 한다. 
		좀 더 구체적으로 말하면 import 해서 MessageDigest 클래스를 사용한다. 
		SHA-256 알고리즘을 사용하기 위해 위와 같이 선언하면 된다. ( MessageDigest md = MessageDigest.getInstance("SHA-256"); )

		new 생성자를 따로 쓸 필요 없이 위와 같이 하면 자동으로 새로 생성이된다. 그 외에도, MD5, SHA-1 도 지원한다.

		패스워드와 솔트를 합쳐서 Hashing하고, 얻어진 다이제스트를 다시 솔트와 합쳐 해싱하고.. 이렇게 반복을 10000번 한다. 
		참고로 md.update() 가 바로 입력한 문자열을 해싱하는 함수다. 
		이 때 문자열은 byte배열이어야 한다.
		그렇기 때문에 해당 문자열을 getBytes() 메소드를 통해 바이트배열로 반환시켜 넣어야 하며,
		다이제스트를 반환하는 md.digest() 또한 기본적으로 바이트배열로 반환하기 때문에 이후에 설명 할 Byte_to_String 메소드를 통해 다시 문자열로 변환해주는 메소드를 필요로 한다.

		해쉬함수의 단점 보완하기에서 키 스트레칭이 바로 이 과정에 해당된다.
		즉, 다이제스트를 복호화하기 어렵게 만들면서도 의도적으로 시간을 지연시키는 방법인 것이다.
		이렇게 반복한 뒤 얻어진 다이제스트를 문자열로 변환시켜 반환해준다.
	 */
}
