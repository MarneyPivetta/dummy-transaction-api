package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterTO;

public class SQLBuilderPBM implements ISQLBuilder {

	@Override
	public String findAllByDate(TransactionFilterTO filter) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		select(query);

		query.append(" FROM logpbm l ");
		query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb ");

		query.append(" WHERE 1=1 ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l.data_trn = :datatrn");
				if (filter.getStartTime() != null && filter.getEndTime() != null)
					query.append(" AND l.data_trn || l.hora_trn BETWEEN :data_horainicial AND :data_horafinal");
			}

			case DATA_EXPORTACAO -> {
				query.append(" AND l.data_exportacao = :datatrn");
				if (filter.getStartTime() != null && filter.getEndTime() != null)
					query.append(" AND l.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal");
			}
		}

		if (filter.getPosNumber() != null) {
			query.append(" AND l.ident_pdv = :numpdv");
		}

		query.append(" AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		query.append(" UNION ");

		query.append(" SELECT ");
		select(query);

		query.append(" FROM logpbm l ");
		query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb ");
		query.append(" LEFT JOIN logpbm l1 ");
		query.append("   ON l.codlojasitef = l1.codlojasitef ");
		query.append("   AND l.cod_sit = l1.cod_sit ");
		query.append("   AND l.documento = l1.documento ");
		query.append(" LEFT JOIN def_tipo_transacao a1 ON l1.cod_trnweb = a1.cod_trnweb ");

		query.append(" WHERE 1=1");

		// case when usado como GAMBIA para "enganar" o Oracle para nao executar
		// o cast em transacoes que nao possuem nsu_host valido
		query.append("   AND CASE l.cod_trnweb WHEN 10 THEN 0 ELSE CAST (l.nsu_host AS number) END = CAST (l1.doc_cancel AS number)");

		query.append("   AND l1.estado_trn in (4, 132)");
		query.append("   AND l1.codigo_resposta = '00'");
		query.append("   AND a1.tipo_id = 14");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l1.data_trn = :datatrn");
				if (filter.getStartTime() != null && filter.getEndTime() != null)
					query.append(" AND l1.data_trn || l1.hora_trn BETWEEN :data_horainicial AND :data_horafinal");
			}
			case DATA_EXPORTACAO -> {
				query.append(" AND l1.data_exportacao = :datatrn");
				if (filter.getStartTime() != null && filter.getEndTime() != null)
					query.append(" AND l1.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal");
			}
		}

		// estornada -> ultimos 14 dias (por performance)
		query.append(" AND l.data_trn BETWEEN :data_menos14 AND :datatrn");

		// Estorno: estado 135 e valor da trn de estorno igual a valor da trn
		// estornada
		// Estorno Parcial: estado 133 e valor da trn de estorno diferente do
		// valor da trn estornada
		query.append(" AND (");
		query.append("  (l.estado_trn = 135 AND l.valor = l1.valor)");
		query.append("  OR (l.estado_trn = 133)");
		query.append(" )");

		if(filter.getPosNumber() != null) {
			query.append(" AND l1.ident_pdv = :numpdv");
		}

		query.append(" AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (FilterDateType.DATA_TRN.equals(filter.getDateType())) {
			query.append(" UNION ALL ");

			query.append(" SELECT ");
			select(query);

			query.append(" FROM logpbm l ");
			query.append(" LEFT JOIN transacoes t ON l.cod_trnweb = t.cod_trnweb ");

			query.append(" WHERE 1=1 ");

			// pendencia -> ultimos 4 dias sem a data_trn
			query.append(" AND l.data_trn BETWEEN :data_menos4 AND :data_menos1");
			query.append(" AND l.datapend = :datatrn");

			if (filter.getStartTime() != null && filter.getEndTime() != null) {
				query.append(" AND l.horapend BETWEEN :horainicial AND :horafinal ");
			}

			if (filter.getPosNumber() != null) {
				query.append(" AND l.ident_pdv = :numpdv ");
			}

			query.append(" AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");
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
		query.append("  l.valor AS valor_trn, ");
		query.append("  l.codigo_resposta AS codigo_resp, ");
		query.append("  l.codigo_autoriz AS cod_autoriz, ");
		query.append("  l.cupom_fiscal AS cuponfiscal, ");
		query.append("  NULL AS bandeira, ");
		query.append("  t.descr_trn AS infotransacao, ");
		query.append("  l.documento, ");
		query.append("  l.num_parcelas, ");
		query.append("  l.ident_pdv AS num_pdv, ");
		query.append("  l.nsu_host AS nsuhost, ");
		query.append("  NULL AS modoentrada, ");
		query.append("  l.codigo_estab AS codestabelecimento, ");
		query.append("  (SELECT lj.cnpj FROM loja lj WHERE l.codlojasitef = lj.codlojasitef) AS cnpj, ");
		//query.append("  '' AS cnpj, ");
		query.append("  'logpbm' as tabletef, ");
		query.append("  l.ipsitef, ");
		query.append("  l.datapend, ");
		query.append("  l.horapend, ");
		query.append("  l.estado_trn, ");
		query.append("  l.ident_pdv AS idt_terminal, ");
		query.append("  l.se_cliente, ");
		query.append("  NULL AS quem_negou, ");
		query.append("  NULL AS datafiscal, ");
		query.append("  NULL AS horafiscal, ");
		query.append("  l.codcli, ");
		query.append("  NULL AS codoperadora, ");
		query.append("  NULL AS codfilial, ");
		query.append("  l.valor_repasse, ");
		query.append("  l.valor_avista, ");
		query.append("  l.valor_cartao, ");
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
		query.append("  NULL AS valor_pagamento_1, ");
		query.append("  NULL AS forma_pagamento_1, ");
		query.append("  NULL AS valor_pagamento_2, ");
		query.append("  NULL AS forma_pagamento_2, ");
		query.append("  NULL AS valor_pagamento_3, ");
		query.append("  NULL AS forma_pagamento_3, ");
		query.append("  NULL AS valor_pagamento_4, ");
		query.append("  NULL AS forma_pagamento_4, ");
		query.append("  NULL AS nsucanchost ");
	}
}
