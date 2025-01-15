package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterTO;

public class SQLBuilderAMBEV implements ISQLBuilder {

	@Override
	public String findAllByDate(TransactionFilterTO filter) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		select(query);

		query.append(" FROM log_promocao_torcedor l ");
		query.append(" LEFT JOIN estado_trn_ambev e ON l.cod_resp = e.codigo ");

		query.append("WHERE 1=1");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l.data_trn = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.data_trn || l.hora_trn BETWEEN :data_horainicial AND :data_horafinal ");
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
			query.append(" AND l.idt_terminal = :numpdv");
		}

		query.append(" UNION ");

		query.append(" SELECT ");
		select(query);

		query.append(" FROM log_promocao_torcedor l ");
		query.append(" LEFT JOIN estado_trn_ambev e ON l.cod_resp = e.codigo ");
		query.append(" LEFT JOIN log_promocao_torcedor l1 ");
		query.append("  ON l.codlojasitef = l1.codlojasitef ");
		query.append("  AND l.cod_sit = l1.cod_sit ");
		query.append("  AND l.cpf_cnpj = l1.cpf_cnpj ");

		query.append(" WHERE 1=1 ");
		query.append("  AND l1.estado_trn in (4, 132) ");
		query.append("  AND l1.cod_resp = '00' ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l1.data_trn = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.data_trn || l1.hora_trn BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}

			case DATA_EXPORTACAO -> {
				query.append(" AND l1.data_exportacao = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		// estornada -> ultimos 14 dias (por performance)
		query.append(" AND l.data_trn BETWEEN :data_menos14 AND :datatrn");

		// Estorno: estado 135 e valor da trn de estorno igual a valor da trn
		// estornada
		// Estorno Parcial: estado 133 e valor da trn de estorno diferente do
		// valor da trn estornada
		query.append(" AND (");
		query.append("  (l.estado_trn = 135 AND l.desconto_total = l1.desconto_total)");
		query.append("  OR (l.estado_trn = 133)");
		query.append(" )");

		if (filter.getPosNumber() != null) {
			query.append(" AND l1.idt_terminal = :numpdv");
		}

		query.append(" AND l.codlojasitef IN (select codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (FilterDateType.DATA_TRN.equals(filter.getDateType())) {
			query.append(" UNION ALL ");

			query.append(" SELECT ");
			select(query);

			query.append(" FROM log_promocao_torcedor l ");
			query.append(" LEFT JOIN estado_trn_ambev e ON l.cod_resp = e.codigo ");

			query.append(" WHERE 1=1 ");

			// pendencia -> ultimos 4 dias sem a data_trn
			query.append(" AND l.data_trn BETWEEN :data_menos4 AND :data_menos1");

			if (filter.getStartTime() != null && filter.getEndTime() != null) {
				query.append(" AND l.hora_trn BETWEEN :horainicial AND :horafinal ");
			}

			if (filter.getPosNumber() != null) {
				query.append(" AND l1.idt_terminal = :numpdv");
			}

			query.append(" AND l.codlojasitef IN (select codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		}

		return query.toString();
	}

	private void select(StringBuilder query) {
		query.append("  l.cod_sit, ");
		query.append("  l.data_trn, ");
		query.append("  TO_CHAR(l.nsu_sitef), ");
		query.append("  l.codlojasitef, ");
		query.append("  NULL AS codlojasitef_tronco, ");
		query.append("  l.hora_trn, ");
		query.append("  TO_CHAR(l.desconto_total) AS valor_trn, ");
		query.append("  l.cod_resp AS codigo_resp, ");
		query.append("  null AS cod_autoriz, ");
		query.append("  l.cupom_fiscal AS cuponfiscal, ");
		query.append("  NULL AS bandeira, ");
		query.append("  DECODE(l.codigo_trn, '17', 'Consulta CPF', '18', 'Registro Desconto', '19', 'Consulta de Produtos', '20', 'Consulta Lista de Produtos') AS infotransacao, ");
		query.append("  l.cpf_cnpj AS documento, ");
		query.append("  NULL AS num_parcelas, ");
		query.append("  l.idt_terminal AS num_pdv, ");
		query.append("  NULL AS nsuhost, ");
		query.append("  NULL AS modoentrada, ");
		query.append("  NULL AS codestabelecimento, ");
		query.append("  (SELECT lj.cnpj FROM loja lj WHERE l.codlojasitef = lj.codlojasitef) AS cnpj, ");
		//query.append("  '' AS cnpj, ");
		query.append("  'logpromocao' AS tabletef, ");
		query.append("  l.ipsitef, ");
		query.append("  NULL AS datapend, ");
		query.append("  NULL AS horapend, ");
		query.append("  l.estado_trn, ");
		query.append("  l.idt_terminal, ");
		query.append("  l.se_cliente, ");
		query.append("  NULL AS quem_negou, ");
		query.append("  NULL AS datafiscal, ");
		query.append("  NULL AS horafiscal, ");
		query.append("  NULL AS codcli, ");
		query.append("  NVL(e.descricao, 'Estado não previsto') AS codoperadora, ");
		query.append("  NULL as codfilial, ");
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
