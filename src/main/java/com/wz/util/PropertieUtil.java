package com.wz.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertieUtil {
	
	private static final Logger LOGGER = Logger.getLogger(PropertieUtil.class);
	
	public static Properties prop = new Properties();
	
	static {
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			LOGGER.error("静态初始化PropertieUtil失败.");
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
	
}
