package com.javaweb.utils;

public class StringUtils {
	
	public static Boolean checkString(String data) {
		if (data != null && !data.equals("")) {
			return true;
		}
		return false;
	}
	
	
}
