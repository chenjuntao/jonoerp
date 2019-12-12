package com.tanry.framework.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DoubleJsonValueProcessor implements JsonValueProcessor {

	public DoubleJsonValueProcessor() {
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		return process(value);
	}

	private Object process(Object value) {
		if (value == null) {
			return "";
		}

		return value;
	}

}
