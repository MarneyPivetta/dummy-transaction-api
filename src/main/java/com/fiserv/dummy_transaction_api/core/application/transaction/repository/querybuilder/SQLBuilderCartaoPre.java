package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterTO;

public class SQLBuilderCartaoPre implements ISQLBuilder {

	@Override
	public String findAllByDate(TransactionFilterTO filter) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		select(query);

		query.append(" FROM log_cartao_pre l");
		query.append(" LEFT JOIN trn_cartao_pre t ON l.cod_trn = t.cod_trn ");
		query.append(" LEFT JOIN produto_cartao_pre p ON l.cod_produto = p.cod_produto ");

		query.append(" WHERE 1=1 ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l.data_sitef = :datatrn");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.hora BETWEEN :horainicial AND :horafinal ");
				}
			}
			case DATA_EXPORTACAO -> {
				query.append(" AND l.data_exportacao = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		query.append(" AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (filter.getPosNumber() != null) {
			query.append(" AND l.ident_pdv = :numpdv ");
		}

		query.append(" UNION ");

		query.append(" SELECT ");
		select(query);

		query.append(" FROM log_cartao_pre l");
		query.append(" LEFT JOIN trn_cartao_pre t ON l.cod_trn = t.cod_trn ");
		query.append(" LEFT JOIN produto_cartao_pre p ON l.cod_produto = p.cod_produto ");
		query.append(" LEFT JOIN log_cartao_pre l1 ");
		query.append("   ON l.codlojasitef = l1.codlojasitef ");
		query.append("   AND l.cod_sit = l1.cod_sit ");
		query.append("   AND l.data_sitef = l1.data_cancel ");
		query.append("   AND l.nsu_sitef = l1.nsu_sitef_cancel ");

		query.append(" WHERE 1=1 ");
		query.append("   AND l.estado_trn = 135 ");
		query.append("   AND l1.estado_trn IN (4, 132) ");
		query.append("   AND l1.cod_resposta = '00' ");
		query.append("   AND l1.cod_trn = (SELECT cod_trn FROM trn_cartao_pre WHERE cod_trn LIKE 'Cancelamento%') ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l1.data_sitef = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.hora BETWEEN :horainicial and :horafinal ");
					query.append(" AND ( ");
					query.append("  (l.data_sitef || l.hora) <> (l1.datapend || l1.horapend) ");
					query.append("  AND (l.data_sitef < :horainicial OR l.hora > :horafinal) ");
					query.append(" ) ");

				} else {
					query.append(" and l.data_sitef <> l1.datapend ");
				}
			}

			case DATA_EXPORTACAO -> {
				query.append(" AND l1.data_exportacao = :datatrn" );
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		if (filter.getPosNumber() != null) {
			query.append(" AND l1.ident_pdv = :numpdv ");
		}

		query.append(" AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (FilterDateType.DATA_TRN.equals(filter.getDateType())) {
			query.append(" UNION ");

			query.append(" SELECT ");
			select(query);

			query.append(" FROM log_cartao_pre l ");
			query.append(" LEFT JOIN trn_cartao_pre t ON l.cod_trn = t.cod_trn ");
			query.append(" LEFT JOIN produto_cartao_pre p ON l.cod_produto = p.cod_produto ");

			query.append(" WHERE 1=1 ");
			query.append("   AND l.datapend <> l.data_sitef ");
			query.append("   AND l.datapend = :datatrn ");

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
		query.append("  l.data_sitef AS data_trn, ");
		query.append("  l.nsu_sitef, ");
		query.append("  l.codlojasitef, ");
		query.append("  NULL AS codlojasitef_tronco, ");
		query.append("  l.hora AS hora_trn, ");
		query.append("  l.valor AS valor_trn, ");
		query.append("  l.cod_resposta AS codigo_resp, ");
		query.append("  l.cod_autorizacao AS cod_autoriz, ");
		query.append("  l.cupom_fiscal AS cuponfiscal, ");
		query.append("  p.desc_produto AS bandeira, ");
		query.append("  t.desc_trn AS infotransacao, ");
		query.append("  l.num_cartao AS documento, ");
		query.append("  NULL AS num_parcelas, ");
		query.append("  l.ident_pdv as num_pdv, ");
		query.append("  l.nsu_host AS nsuhost, ");
		query.append("  NULL AS modoentrada, ");
		query.append("  l.cod_estabelecimento as codestabelecimento, ");
		query.append("  (SELECT lj.cnpj FROM loja lj WHERE l.codlojasitef = lj.codlojasitef) AS cnpj, ");
		//query.append("  '' AS cnpj, ");
		query.append("  'log_cartao_pre' as tabletef, ");
		query.append("  l.ipsitef, ");
		query.append("  l.datapend, ");
		query.append("  l.horapend, ");
		query.append("  l.estado_trn, ");
		query.append("  l.ident_pdv as idt_terminal, ");
		query.append("  l.se_cliente, ");
		query.append("  NULL AS quem_negou, ");
		query.append("  l.data_fiscal as datafiscal, ");
		query.append("  l.hora_fiscal as horafiscal, ");
		query.append("  NULL AS codcli, ");
		query.append("  NULL as codoperadora, ");
		query.append("  NULL as codfilial, ");
		query.append("  NULL AS valor_repasse, ");
		query.append("  NULL AS valor_avista, ");
		query.append("  NULL AS valor_cartao, ");
		query.append("  NULL AS cod_rede_autoriz, ");
		query.append("  l.dthr_exportacao, ");
		query.append("  NULL as cod_autoriz_aux, ");
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
