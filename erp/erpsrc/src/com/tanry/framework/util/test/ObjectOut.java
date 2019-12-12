package com.tanry.framework.util.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ObjectOut {
	public static void out(String className) {
		Class<?> c = null;

		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Field[] fields = c.getDeclaredFields();
		Method[] methods = c.getMethods();

		System.out
				.println("------------------------------ properties -------------------------------");
		for (Field field : fields) {
			int mod = field.getModifiers();
			System.out.println(Modifier.toString(mod) + " "
					+ field.getType().getName() + " " + field.getName());
		}

		System.out
				.println("------------------------------ methods -------------------------------");
		for (Method method : methods) {
			StringBuffer sb = new StringBuffer();
			int mod = method.getModifiers();
			sb.append(Modifier.toString(mod)).append(" ")
					.append(method.getReturnType().getName()).append(" ")
					.append(method.getName()).append("(");

			boolean hasParameter = false;
			for (Class<?> ss : method.getParameterTypes()) {
				hasParameter = true;
				sb.append(ss.getName()).append(",");
			}

			if (hasParameter) {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}

			sb.append(")");
			System.out.println(sb.toString());
		}
	}
}
