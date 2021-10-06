package com.spring.test.kakao;

import java.util.HashMap;

public class Documents {
	private String address_name;
	private Double y;
	private Double x;
	private String address_type;
	private HashMap<String, Object> address;
	private HashMap<String, Object> road_address;

	public Documents() { }
	
	public Documents( String address_name, 
			Double y, Double x, String address_type, 
			HashMap<String, Object> address, HashMap<String, Object> road_address) {
		this.address_name = address_name;
		this.y = y;   
		this.x = x;
		this.address_type = address_type;
		this.address = address;
		this.road_address = road_address;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public String getAddress_type() {
		return address_type;
	}

	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}

	public HashMap<String, Object> getAddress() {
		return address;
	}

	public void setAddress(HashMap<String, Object> address) {
		this.address = address;
	}

	public HashMap<String, Object> getRoad_address() {
		return road_address;
	}

	public void setRoad_address(HashMap<String, Object> road_address) {
		this.road_address = road_address;
	}
	
	
}
