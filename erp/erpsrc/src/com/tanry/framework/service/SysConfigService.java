package com.tanry.framework.service;

import java.io.File;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.AbstractFileConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

@SuppressWarnings("rawtypes")
public class SysConfigService {

	private AbstractFileConfiguration config = null;

	public static Map<String, SysConfigService> configMap = Collections
			.synchronizedMap(new HashMap<String, SysConfigService>());

	/**
	 * 得到一个绑定了properties文件的实例,每个配置文件只打开一次，然后放入对应得SysConfigService实例中，此实例放入一个缓存中
	 *
	 * @param filePath properties文件的全路径
	 * @return 绑定了properties文件的解析实例
	 */
	public static SysConfigService getInstance(String filePath) {
		if (filePath == null || "".equals(filePath))
			throw new RuntimeException("The filePath can't be null");

		if (configMap.containsKey(filePath)) {
			return configMap.get(filePath);
		} else {
			File file = new File(filePath);
			SysConfigService SysConfigService = null;
			try {
				if (file.exists()) {
					SysConfigService = new SysConfigService();
					SysConfigService.config = new PropertiesConfiguration(filePath);
					configMap.put(filePath, SysConfigService);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return SysConfigService;
		}

	}

	public boolean isEmpty() {
		return config.isEmpty();
	}

	public boolean containsKey(String key) {
		return config.containsKey(key);
	}

	public void addProperty(String key, Object value) throws Exception {
		config.addProperty(key, value);
	}

	public void setProperty(String key, Object value) {
		config.setProperty(key, value);
	}

	public void clearProperty(String key) {
		config.clearProperty(key);
	}

	public Object getProperty(String key) {
		return config.getProperties(key);
	}

	
	public Iterator getKeys(String key) {
		return config.getKeys(key);
	}

	public Iterator getKeys() {
		return config.getKeys();
	}

	public Properties getProperties(String key) {
		return config.getProperties(key);
	}

	public boolean getBoolean(String key) {
		return config.getBoolean(key);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}

	public byte getByte(String key) {
		return config.getByte(key);
	}

	public byte getByte(String key, byte defaultValue) {
		return config.getByte(key, defaultValue);
	}

	public Byte getByte(String key, Byte defaultValue) {
		return config.getByte(key, defaultValue);
	}

	public double getDouble(String key) {
		return config.getDouble(key);
	}

	public double getDouble(String key, double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	public Double getDouble(String key, Double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	public float getFloat(String key) {
		return config.getFloat(key);
	}

	public float getFloat(String key, float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	public Float getFloat(String key, Float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	public int getInt(String key) {
		return config.getInt(key);
	}

	public int getInt(String key, int defaultValue) {
		return config.getInt(key, defaultValue);
	}

	public Integer getInteger(String key, Integer defaultValue) {
		return config.getInteger(key, defaultValue);
	}

	public long getLong(String key) {
		return config.getLong(key);
	}

	public long getLong(String key, long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	public Long getLong(String key, Long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	public short getShort(String key) {
		return config.getShort(key);
	}

	public short getShort(String key, short defaultValue) {
		return config.getShort(key, defaultValue);
	}

	public Short getShort(String key, Short defaultValue) {
		return config.getShort(key, defaultValue);
	}

	public String getString(String key) {
		return config.getString(key);
	}

	public String getString(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	public Configuration subset(String prefix) {
		return config.subset(prefix);
	}

	public AbstractFileConfiguration getConfig() {
		return config;
	}

	public void setConfig(AbstractFileConfiguration config) {
		this.config = config;
	}
}
