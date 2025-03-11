package com.javaweb.utils;

public class NumberUtils {

	public static Boolean isNumber(String data) {
		try {
			Long number = Long.parseLong(data);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
