package com.xyj.ems.utils;

import java.util.UUID;

public class TokenUUIDUtil {

	
	/**
	 * 获取UUID作为token
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	
}