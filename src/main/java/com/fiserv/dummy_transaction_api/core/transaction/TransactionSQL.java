package com.fiserv.dummy_transaction_api.core.transaction;

import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class TransactionSQL {

	public SqlQuery getAllByDate(String date, String seClient) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l.nsu_sitef, l.data_trn, l.cod_sit, l.codlojasitef, l.se_cliente, l.valor_trn ");
		sb.append(" FROM logtef l ");
		sb.append(" WHERE l.data_trn = :date");
		sb.append(" AND l.se_cliente = :seClient");

		Map<String, Object> params = new HashMap<>();
		params.put("date", date);
		params.put("seClient", seClient);

		return new SqlQuery(sb.toString(), params);
	}

	public SqlQuery getAllByStore(String sitefStoreCode, String seClient) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l.nsu_sitef, l.data_trn, l.cod_sit, l.codlojasitef, l.se_cliente, l.valor_trn ");
		sb.append(" FROM logtef l ");
		sb.append(" WHERE l.codlojasitef = :sitefStoreCode");
		sb.append(" AND l.se_cliente = :seClient");

		Map<String, Object> params = new HashMap<>();
		params.put("sitefStoreCode", sitefStoreCode);
		params.put("seClient", seClient);

		return new SqlQuery(sb.toString(), params);
	}

}
