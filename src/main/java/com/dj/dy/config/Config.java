package com.dj.dy.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加载配置文件，默认加载
 * @author ledong
 *
 */
public class Config {
	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	private final static Properties properties = new Properties();

	static {
		loadProperties("dy.properties");
	}
	private static Config instance = new Config();

	private Config() {
	}
//
//	private void init() {
//		loadProperties("dingwa.properties");
//	}

	/**
	 * 加载配置文件
	 * 
	 * @param filename
	 *            配置文件名。如果不再Classpath目录内，请输入全路径名
	 * 
	 */
	public static boolean loadProperties(final String filename) {
		InputStream is = Config.class.getClassLoader().getResourceAsStream(filename);
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));

		try {
			properties.load(bf);
			logger.info(">>>>>Load Properties File : " + filename + " SUCCESS!");
			return true;
		} catch (IOException e) {
			logger.error("Load Properties File : " + filename
					+ " Error!\n Error details : \n" + e.getMessage());
			return false;
		} catch (Exception e1) {
			logger.error("Load Properties File : " + filename
					+ " Error!\n Error details : \n" + e1.getMessage());
			return false;
		}
	}

	public static String getProperties(String key) {
		return getProperties(key, "");
	}

	public static String getProperties(String key, String defaultValue) {
		String value = (String) properties.get(key);
		if (value == null)
			return defaultValue;
		return value;
	}

	public static Integer getPropertiesAsIntValue(String key) {
		return getPropertiesAsIntValue(key, Integer.MIN_VALUE);
	}

	public static Integer getPropertiesAsIntValue(String key, int defaultValue) {
		String value = (String) properties.get(key);
		if (value == null)
			return defaultValue;
		else
			return Integer.valueOf(value);
	}

	public static Double getPropertiesAsDoubleValue(String key) {
		return getPropertiesAsDoubleValue(key, Double.MIN_VALUE);
	}

	public static Double getPropertiesAsDoubleValue(String key, double defaultValue) {
		String value = (String) properties.get(key);
		if (value == null)
			return defaultValue;
		else
			return Double.valueOf(value);
	}

	public static Boolean getPropertiesAsBooleanValue(String key) {
		return getPropertiesAsBooleanValue(key, Boolean.FALSE);
	}

	public static Boolean getPropertiesAsBooleanValue(String key, Boolean defaultValue) {
		String value = (String) properties.get(key);
		if (value == null)
			return defaultValue;
		else
			return Boolean.valueOf(value);
	}

	public static synchronized Config getInstance() {
		if (instance == null)
			instance = new Config();
		return instance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<Object> keySet = properties.keySet();
		for (Object key : keySet) {
			sb.append(key);
			sb.append(":");
			sb.append(properties.get(key));
			sb.append(",");
		}
		return sb.toString();
	}

	public static void main(String args[]) throws FileNotFoundException,
			IOException {
//		 Config config = new Config("schoolFellow.properties");
		Config.loadProperties("database1.properties");
		System.out.println(Config.getInstance().toString());
		System.out.println(Config.getPropertiesAsBooleanValue("compressImg.proportion"));
		

	}
}
