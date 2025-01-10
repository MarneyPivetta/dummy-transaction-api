package com.fiserv.dummy_transaction_api.outbound.database.transaction;

import com.fiserv.dummy_transaction_api.core.ports.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.transaction.TransactionSQL;
import com.fiserv.dummy_transaction_api.core.transaction.TransactionTO;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionJdbcTemplateRepository implements ITransactionRepository {

	private final NamedParameterJdbcTemplate namedParametersJdbcTemplate;
	private final TransactionSQL transactionSQL;

	public TransactionJdbcTemplateRepository(NamedParameterJdbcTemplate namedParametersJdbcTemplate, TransactionSQL transactionSQL) {
		this.namedParametersJdbcTemplate = namedParametersJdbcTemplate;
		this.transactionSQL = transactionSQL;
	}

	@Override
	public List<TransactionTO> getAllByDate(String date, String seClient) {
		SqlQuery query = transactionSQL.getAllByDate(date, seClient);

		return namedParametersJdbcTemplate.query(query.sql, query.params, this::mapTransaction);
	}

	@Override
	public List<TransactionTO> getAllByStore(String store, String seClient) {
		SqlQuery query = transactionSQL.getAllByStore(store, seClient);

		return namedParametersJdbcTemplate.query(query.sql, query.params, this::mapTransaction);
	}


	private TransactionTO mapTransaction(ResultSet rs, int rowNum) throws SQLException {
		String sitefStoreCode = rs.getString("codlojasitef");
		String transactionDate = rs.getString("data_trn");
		Integer codsit = rs.getInt("cod_sit");
		String nsu = rs.getString("nsu_sitef");
		String transactionSeClient = rs.getString("se_cliente");
		Long value = rs.getLong("valor_trn");

		return new TransactionTO(sitefStoreCode, transactionDate, codsit, nsu, transactionSeClient, value);
	}
}
