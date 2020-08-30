package com.xn.common.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5 {
	public final static String parseMD5(String s) {
		if(s==null){
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
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
	
	public static void main(String[] args) throws Exception {
		//Date date =DateUtils.StringToDate(new Date().toLocaleString(), DateUtils.formatStr_yyyyMMddHHmmss);
		Date date =new Date("yyyy,mth,dd,hh,mm,ss");
		System.out.println("~~~~~~~~~~~~~~"+date);
	}
}