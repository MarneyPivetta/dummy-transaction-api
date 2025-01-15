package com.fiserv.dummy_transaction_api.outbound.database.user;

import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserSQL {

	public SqlQuery getUserByLogin(String login) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT login, se_cliente ");
		sb.append(" FROM gw_usuario u ");
		sb.append(" WHERE u.login = :login ");

		Map<String, Object> params = new HashMap<>();
		params.put("login", login);

		return new SqlQuery(sb.toString(), params);
	}

}
