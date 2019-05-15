package com.wz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class StringUtil extends org.apache.commons.lang.StringUtils {

	private final static Logger logger = Logger.getLogger(StringUtil.class);

	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 
	 * @Title: StringUtils @Description: 产生随机的六位数 @param @return string @throws
	 */
	public static String getSix() {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != 6) {
			return getSix();
		}
		return result;
	}

	/**
	 * 校验email
	 * @param email
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean isEmail(String email) {
		boolean flag = false;
		
		if (null == email || email.length() <= 0) {
			return flag;
		}
		
		String pat = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		
		try {
			Pattern regex = Pattern.compile(pat);
			
			Matcher matcher = regex.matcher(email);
			
			flag = matcher.matches();
		} catch (Exception e) {
			logger.error("校验email有误, email[" + email + "]");
			
			flag = false;
		} finally {
			return flag;
		}
	}
	
	/**
	 * 检验手机
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (mobile.length() != 11) {
            return false;
        } else {
            // 移动号段正则表达式
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
            // 联通号段正则表达式
            String pat2  = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            // 电信号段正则表达式
            String pat3  = "^((133)|(153)|(177)|(18[0,1,9])|(149)|(199))\\d{8}$";
            // 虚拟运营商正则表达式
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            Pattern pattern1 = Pattern.compile(pat1);
            Matcher match1 = pattern1.matcher(mobile);
            boolean isMatch1 = match1.matches();
            if (isMatch1) {
                return true;
            }
            Pattern pattern2 = Pattern.compile(pat2);
            Matcher match2 = pattern2.matcher(mobile);
            boolean isMatch2 = match2.matches();
            if (isMatch2) {
                return true;
            }
            Pattern pattern3 = Pattern.compile(pat3);
            Matcher match3 = pattern3.matcher(mobile);
            boolean isMatch3 = match3.matches();
            if (isMatch3) {
                return true;
            }
            Pattern pattern4 = Pattern.compile(pat4);
            Matcher match4 = pattern4.matcher(mobile);
            boolean isMatch4 = match4.matches();
            if (isMatch4) {
                return true;
            }
            return false; 
        }
	}

	public static String formatMobile(String mobile) {
		if (null == mobile || mobile.isEmpty()) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(mobile.substring(0, 3));
		buffer.append("****");
		buffer.append(mobile.substring(mobile.length() - 4, mobile.length()));
		return buffer.toString();
	}

	/**
	 * 
	 * @Title: StringUtils @Description: 产生随机的四位数 @param @return string @throws
	 */
	public static String getFour() {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != 4) {
			return getFour();
		}
		return result;
	}

	/**
	 * 
	 * @Title: StringUtils @Description: 将手机号中间4位替换为随机数 @param @return string @throws
	 */
	public static String replaceMobile(String mobile) {
		return mobile.replace(mobile.substring(4, 8),  getFour());
	}
	
	/**
	 * 
	 * @Title: StringUtils
	 * @Description: 创建8位数字和英文组成的邀请码（英文和数字必须同时出现，区分大小写）
	 * @param
	 * @return String
	 * @throws Exception
	 * @throws IOException
	 */
	public static String getInviteCode() throws Exception {
		StringBuffer sb = new StringBuffer();
		Integer length = 8;
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}
	
	public static String createCode(long number){
		return createCode((int)number);
	}
	
	/**
	 * 根据8位以内的数字参数，生成字母加数字的的8位组合字符串
	 * @param number 8位以内数字
	 * @return 字母+数字组合
	 */
	public static String createCode(int number) {
		String source_string = "0E5FCDG3HQA4B1NOPIJ2RSTUV67MWX89KLYZ";
		char code = 'a';
		String id = number + "";
		StringBuffer codes = new StringBuffer();
		while (number > 0) {
			int mod = number % 36;
			number = (number - mod) / 36;
			code = source_string.charAt(mod);
			codes.append(code);
		}

		if (codes.length() < 8) {
			codes.append(id.substring(id.length() - (8 - codes.length()), id.length()));
		}
		return codes.toString();
	}
	
	public static boolean isNullOrEmpty(String property) {
		return isBlank(property);
	}

	public static boolean isAllNumber(String str){     
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher m = pattern.matcher(str);
        return m.matches();     
    }
	
	/**
	 * 字符串切割成Long数组
	 * @param str 字符串
	 * @param regex 切割
	 * @return Long[]
	 */
	public static Long[] splicToLongArray(String str, String regex){
		String[] strs = str.split(regex);
		Long[] result = new Long[strs.length];
		for(int i=0; i<strs.length; i++){
			result[i] = Long.parseLong(strs[i]);
		}
		return result;
	}
	
	/**
	 * 字符串切割成Long类型的ArrayList
	 * @param str 要切割的字符串
	 * @param regex 切割格式
	 * @return List<Long>
	 */
	public static List<Long> splicToLongArrayList(String str, String regex){
		String[] strs = str.split(regex);
		List<Long> result = new ArrayList<Long>();
		for(int i=0; i<strs.length; i++){
			result.add(Long.parseLong(strs[i]));
		}
		return result;
	}
	
	/**
	 * 字符串切割成String类型的ArrayList
	 * @param str 	要切割的字符串
	 * @param regex	切割格式
	 * @return List<String>
	 */
	public static List<String> spliceToStringArrayList(String str, String regex){
		String[] strs = str.split(regex);
		List<String> result = new ArrayList<String>();
		for(String s : strs){
			result.add(s);
		}
		return result;
	}
	
	/**
	 * 判断字符串是否为null，返回字符串
	 * @param str
	 * @return
	 */
	public static String returnString(String str) {
		return str == null ? "" : str; 
	}
	
	public static void main(String[] args) {
		//System.out.println(isMobile("13750098002"));
		System.out.println(isEmail("844131681@163.com"));
	}

}
