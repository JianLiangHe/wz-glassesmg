package com.wz.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.wz.pojo.Customer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TokenUtil {

	public static <T> String encryptToken(T obj) throws Exception {
		String token = null;
		String objJsonStr = JSON.toJSONString(obj);
		System.out.println("json str: " + objJsonStr);
		byte[] cipher = RSAEncrypt.encrypt(objJsonStr.getBytes());
		token = new BASE64Encoder().encodeBuffer(cipher);
		return token;
	}
	
	public static Map<String, Object> decryptToken(String token) throws Exception {
		byte[] cipher = new BASE64Decoder().decodeBuffer(token);
		byte[] plainText = RSAEncrypt.decrypt(cipher);
		
		String jsonStr = new String(plainText);
		
		System.out.println("decrypt token: " + jsonStr);
		
		Map<String, Object> map = (Map<String, Object>) JSON.parse(jsonStr);
		
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		Customer customer = new Customer();
		customer.setAddress("hhh");
		customer.setId(1);
		customer.setMobile("13750098002");
		
		Map<String, Object> data =new HashMap<String, Object>();
		data.put("customer", customer);
		data.put("time", 123456);
		
		String token = encryptToken(data);
		
		Map<String, Object> map = decryptToken(token);
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String obj = it.next();
			System.out.println("k:" + obj + ", v:" + map.get(obj));
		}
	}
	
}
