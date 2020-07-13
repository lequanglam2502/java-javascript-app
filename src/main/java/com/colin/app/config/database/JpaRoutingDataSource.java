package com.colin.app.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class JpaRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return JpaDataSourceContext.getDataSourceType();
	}
}
