package com.fiserv.dummy_transaction_api.util.sql;

import java.util.Map;

public class SqlQuery {

	public String sql;
	public Map<String, Object> params;

	public SqlQuery(String sql, Map<String, Object> params) {
		this.sql = sql;
		this.params = params;
	}
}
