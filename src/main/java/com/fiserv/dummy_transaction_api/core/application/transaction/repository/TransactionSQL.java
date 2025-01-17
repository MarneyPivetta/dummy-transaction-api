package com.fiserv.dummy_transaction_api.core.application.transaction.repository;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.EnvProductType;
import com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder.*;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;
import com.fiserv.dummy_transaction_api.basic.util.DateUtil;
import com.fiserv.dummy_transaction_api.basic.sql.SqlQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TransactionSQL {

	private final List<EnvProductType> enabledProducts;

	private Map<EnvProductType, ISQLBuilder> queryMap;

	public TransactionSQL(List<EnvProductType> enabledProducts) {
		this.enabledProducts = enabledProducts;
		this.setupBuilders();
	}

	public SqlQuery findAllByDate(TransactionFilterDTO filter) {
		return new SqlQuery(
				buildQuery(builder -> builder.findAllByDate(filter)),
				buildParams(filter)
		);
	}

	private Map<String, Object> buildParams(TransactionFilterDTO filter) {
		Map<String, Object> params = new HashMap<>();

		params.put("cod_usuario", "supervisor");

		if (filter.getDate() != null) {
			params.put("datatrn", filter.getDate());
			params.put("data_menos14", DateUtil.addDay(filter.getDate(), -14));
			params.put("data_menos4", DateUtil.addDay(filter.getDate(), -4));
			params.put("data_menos1", DateUtil.addDay(filter.getDate(), -1));

			if (filter.getStartTime() != null && filter.getEndTime() != null) {
				params.put("data_horainicial", filter.getDate().concat(filter.getStartTime()));
				params.put("data_horafinal", filter.getDate().concat(filter.getEndTime()));
			}
		}

		if (filter.getStartTime() != null && filter.getEndTime() != null) {
			params.put("horainicial", filter.getStartTime());
			params.put("horafinal", filter.getEndTime());
		}

		return params;
	}

	private String buildQuery(Function<ISQLBuilder, String> builder) {
		List<String> queries = new ArrayList<>();

		for (EnvProductType product : enabledProducts) {
			if (queryMap.get(product) != null) {
				queries.add(builder.apply(queryMap.get(product)));
			}
		}

		return String.join(" UNION ALL ", queries);
	}

	private void setupBuilders() {
		this.queryMap = new HashMap<>();
		this.queryMap.put(EnvProductType.TEF, new SQLBuilderTEF());
		this.queryMap.put(EnvProductType.RECARGA, new SQLBuilderRecarga());
		this.queryMap.put(EnvProductType.AMBEV, new SQLBuilderAMBEV());
		this.queryMap.put(EnvProductType.CARTAO_PRE, new SQLBuilderCartaoPre());
		this.queryMap.put(EnvProductType.PBM, new SQLBuilderPBM());
		this.queryMap.put(EnvProductType.SAV, new SQLBuilderSAV());
		this.queryMap.put(EnvProductType.CSF, new SQLBuilderCSF());
		this.queryMap.put(EnvProductType.CB, new SQLBuilderCB());
	}
}
