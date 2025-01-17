package com.fiserv.dummy_transaction_api.adapters.outbound.database.transaction;

import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.domain.TransactionDTO;
import com.fiserv.dummy_transaction_api.basic.sql.SqlQuery;
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
	public List<TransactionDTO> executeTransactionQuery(SqlQuery query) {
		return namedParametersJdbcTemplate.query(query.sql, query.params, this::mapTransaction);
	}

	private TransactionDTO mapTransaction(ResultSet rs, int rowNum) throws SQLException {
		return new TransactionDTO( //
				rs.getLong("cod_sit"), //
				rs.getString("data_trn"), //
				rs.getString("nsu_sitef"), //
				rs.getString("codlojasitef"), //
				rs.getString("codlojasitef_tronco"), //
				rs.getString("valor_trn"), //
				rs.getString("ipsitef"), //
				rs.getString("codigo_resp"), //
				rs.getString("datapend"), //
				rs.getString("horapend"), //
				rs.getString("estado_trn"), //
				rs.getString("idt_terminal"), //
				rs.getString("cod_autoriz"), //
				rs.getString("hora_trn"), //
				rs.getString("cuponfiscal"), //
				rs.getString("bandeira"), //
				rs.getString("infotransacao"), //
				rs.getString("tabletef"), //
				rs.getString("documento"), //
				rs.getString("num_parcelas"), //
				rs.getString("num_pdv"), //
				rs.getString("nsuhost"), //
				rs.getString("nsuhostoriginal"), //
				rs.getString("modoentrada"), //
				rs.getString("codestabelecimento"), //
				getRawCNPJ(rs.getString("cnpj")), //
				//getDescricaoSit(rs.getLong("cod_sit"), rs.getString("tabletef")), //
				rs.getLong("cod_sit") + rs.getString("tabletef"), //
				rs.getString("se_cliente"), //
				rs.getString("quem_negou"), //
				rs.getString("datafiscal"), //
				rs.getString("horafiscal"), //
				rs.getString("codcli"), //
				rs.getString("codoperadora"), //
				rs.getString("codfilial"), //
				rs.getString("valor_repasse"), //
				rs.getString("valor_avista"), //
				rs.getString("valor_cartao"), //
				rs.getString("cod_rede_autoriz"), //
				rs.getString("dthr_exportacao"), //
				rs.getString("cod_autoriz_aux"), //
				rs.getString("nsuhostaux"), //
				rs.getString("forma_pagamento_cd"), //
				rs.getString("subtipo_pagamento_cd"), //
				rs.getString("bandeira_pagamento_cd"), //
				rs.getString("psp"), //
				rs.getString("tid"), //
				rs.getString("valor_pagamento_1"), //
				rs.getString("forma_pagamento_1"), //
				rs.getString("valor_pagamento_2"), //
				rs.getString("forma_pagamento_2"), //
				rs.getString("valor_pagamento_3"), //
				rs.getString("forma_pagamento_3"), //
				rs.getString("valor_pagamento_4"), //
				rs.getString("forma_pagamento_4"), //
				rs.getString("nsucanchost")//
		);
	}
	public String getRawCNPJ(String cnpj) {
		if (cnpj == null) return null;
		return cnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "");
	}
}
