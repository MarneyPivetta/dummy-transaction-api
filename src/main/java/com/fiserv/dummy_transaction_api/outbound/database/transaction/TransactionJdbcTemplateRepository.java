package com.fiserv.dummy_transaction_api.outbound.database.transaction;

import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionSQL;
import com.fiserv.dummy_transaction_api.core.application.transaction.TransactionTO;
import com.fiserv.dummy_transaction_api.core.application.user.UserTO;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionJdbcTemplateRepository implements ITransactionRepository {

	private final NamedParameterJdbcTemplate namedParametersJdbcTemplate;
	private final ITransactionSQL transactionSQL;

	public TransactionJdbcTemplateRepository(NamedParameterJdbcTemplate namedParametersJdbcTemplate, ITransactionSQL transactionSQL) {
		this.namedParametersJdbcTemplate = namedParametersJdbcTemplate;
		this.transactionSQL = transactionSQL;
	}

	@Override
	public List<TransactionTO> getAllByDate(String date, UserTO user) {
		SqlQuery query = transactionSQL.getAllByDate(date, user);

		return namedParametersJdbcTemplate.query(query.sql, query.params, this::mapTransaction);
	}

	@Override
	public List<TransactionTO> getAllByStore(String store, UserTO user) {
		SqlQuery query = transactionSQL.getAllByStore(store, user);

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
