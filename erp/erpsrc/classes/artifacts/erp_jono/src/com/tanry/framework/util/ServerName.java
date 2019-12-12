package com.tanry.framework.util;

public class ServerName {
	public static String ServerNameToBranchId(String str) {
		switch (str) {
		case "SERVERHDQ":
			return "01";
		case "DTSERVER":
			return "02";
		case "HJTSERVER":
			return "03";
		case "SERVERDJY":
			return "04";
		case "SERVER-7":
			return "05";
		case "YJSEVER":
			return "08";
		case "JUNSERVER":
			return "06";
		case "OKSSERVER":
			return "11";
		case "XYMSERVER":
			return "12";
		case "THINKSERVER":
			return "13";
		case "YHTSERVER":
			return "16";
		case "SERVERZB":
			return "00";
		default:
			return "-1";
		}
	}

	public static String BranchIdToServerName(String str) {
		switch (str) {
		case "01":
			return "SERVERHDQ";
		case "02":
			return "DTSERVER";
		case "03":
			return "HJTSERVER";
		case "04":
			return "SERVERDJY";
		case "05":
			return "SERVER-7";
		case "08":
			return "YJSERVER";
		case "06":
			return "JUNSERVER";
		case "11":
			return "OKSSERVER";
		case "12":
			return "XYMSERVER";
		case "13":
			return "THINKSERVER";
		case "16":
			return "YHTSERVER";
		case "00":
			return "SERVERZB";
		default:
			return "-1";
		}
	}
}
