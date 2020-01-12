package com.tanry.framework.util;

import java.security.MessageDigest;
import java.util.UUID;

public class TextUtil {
	public static boolean isEmpty(String str) {
		if (str != null) {
			if (!str.trim().equals("")) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断待测试字符串是否在给出字符串数组中
	 * 
	 * @return
	 */
	public static boolean containStr(String[] arr, String testStr) {
		if (testStr == null) {
			return false;
		}

		for (int i = 0; i < arr.length; i++) {
			if (testStr.equals(arr[i])) {
				return true;
			}
		}

		return false;
	}

	public static String MD5(String input) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = input.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String uuid() {
		String id = UUID.randomUUID().toString();
		return id.replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(TextUtil.MD5("tanry147"));
		System.out.println(TextUtil.MD5("pw"));
		System.out.println(TextUtil.MD5("1234"));
		System.out.println(TextUtil.MD5("3456"));
	}
}
