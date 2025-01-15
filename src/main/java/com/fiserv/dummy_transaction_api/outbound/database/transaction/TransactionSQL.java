package com.fiserv.dummy_transaction_api.outbound.database.transaction;

import com.fiserv.dummy_transaction_api.core.domain.UserTO;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class TransactionSQL {

	public SqlQuery getAllByDate(String date, UserTO user) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l.nsu_sitef, l.data_trn, l.cod_sit, l.codlojasitef, l.se_cliente, l.valor_trn ");
		sb.append(" FROM logtef l ");
		sb.append(" WHERE l.data_trn = :date");
		sb.append(" AND l.se_cliente = :seClient");

		Map<String, Object> params = new HashMap<>();
		params.put("date", date);
		params.put("seClient", user.getSeClient());

		return new SqlQuery(sb.toString(), params);
	}

	public SqlQuery getAllByStore(String sitefStoreCode, UserTO user) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l.nsu_sitef, l.data_trn, l.cod_sit, l.codlojasitef, l.se_cliente, l.valor_trn ");
		sb.append(" FROM logtef l ");
		sb.append(" WHERE l.codlojasitef = :sitefStoreCode");
		sb.append(" AND l.se_cliente = :seClient");

		Map<String, Object> params = new HashMap<>();
		params.put("sitefStoreCode", sitefStoreCode);
		params.put("seClient", user.getSeClient());

		return new SqlQuery(sb.toString(), params);
	}

}
