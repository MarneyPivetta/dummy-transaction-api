package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;

public class SQLBuilderCB implements ISQLBuilder {

	@Override
	public String findAllByDate(TransactionFilterDTO filter) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		select(query);

		query.append(" FROM logcb l");
		query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb ");

		query.append(" WHERE 1=1 ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l.data_sitef = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.hora_sitef BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
			case DATA_EXPORTACAO -> {
				query.append(" AND l.data_exportacao = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		if (filter.getPosNumber() != null) {
			query.append(" AND l.ident_pdv = :numpdv ");
		}

		query.append(" 	AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		query.append(" UNION ");

		query.append(" SELECT ");
		select(query);

		query.append(" FROM logcb l");
		query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb ");
		query.append(" LEFT JOIN logcb l1 ");
		query.append("  ON l.cod_trnweb = l1.cod_trnweb ");
		query.append("  AND l.codlojasitef = l1.codlojasitef ");
		query.append("  AND l.cod_sit = l1.cod_sit ");
		query.append("  AND l.nsu_sitef = l1.nsusiteforiginal");
		query.append(" LEFT JOIN def_tipo_transacao a1 ");
		query.append("  ON l1.cod_trnweb = a1.cod_trnweb ");

		query.append(" WHERE 1=1 ");

		query.append(" AND l.estado_trn = 135");
		query.append(" AND l1.estado_trn IN (4, 132)");
		query.append(" AND l1.codigo_resposta = '00'");
		query.append(" AND a1.tipo_id = 14");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l1.data_sitef = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.hora_sitef BETWEEN :horainicial AND :horafinal");
					query.append(" AND (");
					query.append("  (l.data_sitef || l.hora_sitef) <> (l1.datapend || l1.horapend)");
					query.append("  AND (l.data_sitef < :horainicial OR l.hora_sitef > :horafinal)");
					query.append(" ) ");
				} else {
					query.append(" AND l.data_sitef <> l1.datapend");
				}
			}
			case DATA_EXPORTACAO -> {
				query.append(" AND l1.data_exportacao = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		if (filter.getPosNumber() != null) {
			query.append(" AND l1.ident_pdv = :numpdv");
		}

		query.append(" 	AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (FilterDateType.DATA_TRN.equals(filter.getDateType())) {
			query.append(" UNION ");

			query.append(" SELECT ");
			select(query);

			query.append(" FROM logcb l");
			query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb ");

			query.append(" WHERE 1=1 ");

			// pendencia -> ultimos 4 dias sem a data_trn
			query.append("  AND l.datapend <> l.data_sitef ");
			query.append("  AND l.datapend = :datatrn ");

			if (filter.getStartTime() != null && filter.getEndTime() != null) {
				query.append(" AND l.horapend BETWEEN :horainicial AND :horafinal");
			}

			if (filter.getPosNumber() != null) {
				query.append(" AND l.ident_pdv = :numpdv");
			}

			query.append("  AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario)");
		}

		return query.toString();
	}

	private void select(StringBuilder query) {
		query.append("  l.cod_sit, ");
		query.append("  l.data_sitef AS data_trn, ");
		query.append("  l.nsu_sitef, ");
		query.append("  l.codlojasitef, ");
		query.append("  NULL AS codlojasitef_tronco, ");
		query.append("  l.hora_sitef AS hora_trn, ");
		query.append("  l.valortotalpago AS valor_trn, ");
		query.append("  l.codigo_resposta AS codigo_resp, ");
		query.append("  l.autorizacao AS cod_autoriz, ");
		query.append("  l.cuponfiscal, ");
		query.append("  NULL AS bandeira,  ");
		query.append("  t.descr_trn AS infotransacao, ");
		query.append("  l.codigobarras AS documento, ");
		query.append("  NULL AS num_parcelas, ");
		query.append("  l.ident_pdv AS num_pdv, ");
		query.append("  l.nsuhost, ");
		query.append("  (SELECT m.descr_modoent FROM modoentrada m WHERE m.cdmodoentrada = l.modoentrada_cb) as modoentrada,  ");
		query.append("  l.codigoestabelecimento AS codestabelecimento, ");
		query.append("  (SELECT lj.cnpj FROM loja lj WHERE l.codlojasitef = lj.codlojasitef) AS cnpj, ");
		//query.append("  '' AS cnpj, ");
		query.append("  'logcb' as tabletef, ");
		query.append("  l.ipsitef, ");
		query.append("  l.datapend, ");
		query.append("  l.horapend, ");
		query.append("  l.estado_trn, ");
		query.append("  l.ident_pdv AS idt_terminal, ");
		query.append("  l.se_cliente, ");
		query.append("  NULL AS quem_negou, ");
		query.append("  l.datafiscal, ");
		query.append("  l.horafiscal, ");
		query.append("  NULL AS codcli, ");
		query.append("  NULL AS codoperadora, ");
		query.append("  NULL AS codfilial, ");
		query.append("  NULL AS valor_repasse, ");
		query.append("  NULL AS valor_avista, ");
		query.append("  NULL AS valor_cartao, ");
		query.append("  NULL AS cod_rede_autoriz, ");
		query.append("  l.dthr_exportacao, ");
		query.append("  NULL AS cod_autoriz_aux, ");
		query.append("  NULL AS nsuhostaux, ");
		query.append("  NULL AS nsuhostoriginal, ");
		query.append("  NULL AS forma_pagamento_cd, ");
		query.append("  NULL AS subtipo_pagamento_cd, ");
		query.append("  NULL AS bandeira_pagamento_cd, ");
		query.append("  NULL AS psp, ");
		query.append("  NULL AS tid, ");
		query.append("  NULL AS descrRede, ");
		query.append("  NULL AS tipo, ");
		query.append("  TO_CHAR(l.valortotalpago) AS valor_pagamento_1, ");
		query.append("  TO_CHAR(l.formapagto) AS forma_pagamento_1, ");
		query.append("  NULL AS valor_pagamento_2, ");
		query.append("  NULL AS forma_pagamento_2, ");
		query.append("  NULL AS valor_pagamento_3, ");
		query.append("  NULL AS forma_pagamento_3, ");
		query.append("  NULL AS valor_pagamento_4, ");
		query.append("  NULL AS forma_pagamento_4, ");
		query.append("  NULL AS nsucanchost ");
	}
}
