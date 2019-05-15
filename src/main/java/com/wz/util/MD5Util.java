package com.wz.util;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

public class MD5Util {

	private static Log logger = LogFactory.getLog(ContextLoader.class);

	// MD5加码。32位
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 加密后解密
	public static String convertMD5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}

	// 测试主函数
	public static void main(String args[]) {
		/*String s = new String("ff92a240d11b05ebd392348c35f781b2");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));  */
		String pwd = "hejianliang";
		pwd = string2MD5(pwd);
		System.out.println("md5: " + pwd);
		System.out.println("解密后：" + convertMD5(convertMD5("123456")));
	}
}
