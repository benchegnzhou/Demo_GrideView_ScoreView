package com.example.demomore.utils;

import java.util.regex.Pattern;

public class CheckType {
	/**
	 * 判断该字符串是否为日期类型
	 * @param str
	 * @return
	 */
	public static boolean isDateType(String str) {
		Boolean b = false;
		String dateType1 = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d*";
		String dateType2 = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
		String dateType3 = "\\d{4}-\\d{2}-\\d{2}";
		if (Pattern.matches(dateType1, str) || Pattern.matches(dateType2, str) || Pattern.matches(dateType3, str)) {
			b = true;
		}
		return b;
	}

	/**
	 * 返回字符串所属日期格式
	 * @param str
	 * @return
	 */
	public static String getDateType(String str) {
		String dateType1 = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d*";
		String dateType2 = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
		String dateType3 = "\\d{4}-\\d{2}-\\d{2}";
		if (Pattern.matches(dateType1, str) || Pattern.matches(dateType2, str)) {
			return "yyyy-MM-dd HH:mm:ss";
		}
		if (Pattern.matches(dateType3, str)) {
			return "yyyy-MM-dd";
		}
		return null;
	}

	/**
	 * 判断该字符串是否为整型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		Boolean b = false;
		if (Pattern.matches("\\d+", str)) {
			b = true;
		}
		return b;
	}
}
