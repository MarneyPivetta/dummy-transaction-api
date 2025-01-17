package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;

public class SQLBuilderRecarga implements ISQLBuilder {

	@Override
	public String findAllByDate( TransactionFilterDTO filter) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		select(query);
		query.append(" FROM logrecargacel l ");
		query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb");

		query.append(" WHERE 1=1 ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l.data_trn = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.hora_trn BETWEEN :horainicial AND :horafinal ");
				}
			}
			case DATA_EXPORTACAO -> {
				query.append(" AND l.data_exportacao = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		query.append(" AND l.codlojasitef IN (select codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (filter.getPosNumber() != null) {
			query.append(" AND l.numero_logico_pdv = :numpdv ");
		}

		return query.toString();
	}

	private void select(StringBuilder query) {
		query.append("  l.cod_sit, ");
		query.append("  l.data_trn, ");
		query.append("  l.nsu_sitef, ");
		query.append("  l.codlojasitef, ");
		query.append("  NULL AS codlojasitef_tronco, ");
		query.append("  l.hora_trn, ");
		query.append("  l.valor_trn, ");
		query.append("  l.codigo_resp, ");
		query.append("  l.cod_autoriz, ");
		query.append("  l.cuponfiscal, ");
		query.append("  NULL AS bandeira, ");
		query.append("  t.descr_trn AS infotransacao, ");
		query.append("  l.area || l.telefone AS documento, ");
		query.append("  NULL AS num_parcelas, ");
		query.append("  l.numero_logico_pdv AS num_pdv, ");
		query.append("  l.nsu_host AS nsuhost, ");
		query.append("  NULL AS modoentrada, ");
		query.append("  l.codigoestabelecimento AS codestabelecimento, ");
		query.append("  (SELECT lj.cnpj FROM loja lj WHERE l.codlojasitef = lj.codlojasitef) AS cnpj, ");
		//query.append("  '' AS cnpj, ");
		query.append("  'logrecargacel' AS tabletef, ");
		query.append("  l.ipsitef, ");
		query.append("  l.datapend, ");
		query.append("  l.horapend, ");
		query.append("  l.estado_trn, ");
		query.append("  l.idt_terminal, ");
		query.append("  l.se_cliente, ");
		query.append("  NULL AS quem_negou, ");
		query.append("  l.datafiscal, ");
		query.append("  l.horafiscal, ");
		query.append("  l.codcli, ");
		query.append("  l.codoperadora, ");
		query.append("  l.codfilial, ");
		query.append("  NULL AS valor_repasse, ");
		query.append("  NULL AS valor_avista, ");
		query.append("  NULL AS valor_cartao, ");
		query.append("  NULL AS cod_rede_autoriz, ");
		query.append("  l.dthr_exportacao, ");
		query.append("  NULL AS cod_autoriz_aux, ");
		query.append("  l.nsu_host AS nsuhostaux, ");
		query.append("  NULL AS nsuhostoriginal, ");
		query.append("  NULL AS forma_pagamento_cd, ");
		query.append("  NULL AS subtipo_pagamento_cd, ");
		query.append("  NULL AS bandeira_pagamento_cd, ");
		query.append("  NULL AS psp, ");
		query.append("  NULL AS tid, ");
		query.append("  NULL AS descrRede, ");
		query.append("  NULL AS tipo, ");
		query.append("  l.valor_pagamento_1, ");
		query.append("  l.forma_pagamento_1, ");
		query.append("  l.valor_pagamento_2, ");
		query.append("  l.forma_pagamento_2, ");
		query.append("  l.valor_pagamento_3, ");
		query.append("  l.forma_pagamento_3, ");
		query.append("  l.valor_pagamento_4, ");
		query.append("  l.forma_pagamento_4, ");
		query.append("  NULL AS nsucanchost ");
	}
}
