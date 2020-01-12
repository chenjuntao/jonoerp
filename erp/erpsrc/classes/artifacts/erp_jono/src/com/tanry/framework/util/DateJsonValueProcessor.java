package com.tanry.framework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	public static final String Default_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	public DateJsonValueProcessor(String datePattern) {
		dateFormat = new SimpleDateFormat(datePattern);
	}

	public DateJsonValueProcessor() {
		dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
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
		} else {
			return dateFormat.format(((java.sql.Date) value).getTime());
		}
	}

}
