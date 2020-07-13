package com.colin.app.config.database;

import java.util.Objects;

public class JpaDataSourceContext {
	private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

	public static void setDataSourceType(Object dataSourceType) {
		if (Objects.isNull(dataSourceType)) {
			throw new NullPointerException();
		}
		
		contextHolder.set(dataSourceType);
	}

	public static Object getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDatabaseType() {
		contextHolder.remove();
	}
}
