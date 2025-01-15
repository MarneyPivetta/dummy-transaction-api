package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterTO;

public class SQLBuilderTEF implements ISQLBuilder {

	@Override
	public String findAllByDate(TransactionFilterTO filter) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		select(query);
		query.append(" FROM logtef l ");
		query.append(" WHERE 1=1 ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l.data_trn = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.dthr_trn BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
			case DATA_EXPORTACAO -> {
				query.append(" AND l.data_exportacao = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l.dthr_exportacao BETWEEN :data_horainicial AND :data_horafinal ");
				}
			}
		}

		query.append(" 	AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario) ");

		if (filter.getPosNumber() != null) {
			query.append(" AND l.idt_terminal = :numpdv ");
		}

		query.append(" UNION ");

		query.append(" SELECT ");
		select(query);

		query.append(" FROM logtef l ");
		query.append(" LEFT JOIN logtef l1");
		query.append("  ON l.codlojasitef = l1.codlojasitef ");
		query.append("  AND l.cod_sit = l1.cod_sit");
		query.append("  AND l.idt_produto = l1.idt_produto");
		query.append("  AND l.documento = l1.documento ");
		query.append(" LEFT JOIN def_tipo_transacao a1 ");
		query.append("  ON l1.cod_trnweb = a1.cod_trnweb ");
		query.append("  AND a1.tipo_id = 14 ");

		query.append(" WHERE 1=1 ");

		// case when usado como GAMBIA para "enganar" o Oracle para nao executar
		// o cast em transacoes que nao possuem nsu_host valido
		// -------------------------------------
		query.append(" AND ");
		query.append(" CASE l.cod_trnweb ");
		query.append(" WHEN 10 THEN 0 ");
		query.append(" ELSE ");
		query.append(" CASE l.cod_sit ");
		// na banrisul/vero o nsu host vem concatenado com outro campo
		query.append(" WHEN 21 THEN CAST(SUBSTR(l.nsu_host, 5, 8 ) AS NUMBER) ");
		query.append(" ELSE CAST (l.nsu_host AS NUMBER) ");
		query.append(" END ");
		query.append(" END ");
		query.append(" = CAST (l1.nsucanchost AS NUMBER) ");
		// -------------------------------------

		query.append(" AND l1.estado_trn IN (4, 132) ");
		query.append(" AND l1.codigo_resp = '00' ");

		switch (filter.getDateType()) {
			case DATA_TRN -> {
				query.append(" AND l1.data_trn = :datatrn ");
				if (filter.getStartTime() != null && filter.getEndTime() != null) {
					query.append(" AND l1.dthr_trn BETWEEN :data_horainicial AND :data_horafinal ");
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
		query.append("  (l.estado_trn = 135 AND l.valor_trn = l1.valor_trn)");
		query.append("  OR (l.estado_trn = 133)");
		query.append(" )");

		if (filter.getPosNumber() != null) {
			query.append(" AND l1.idt_terminal = :numpdv");
		}

		query.append(" and l.codlojasitef in (select codlojasitef from loja_view where cod_usuario = :cod_usuario)");

		if (FilterDateType.DATA_TRN.equals(filter.getDateType())) {
			query.append(" UNION ALL ");

			query.append(" SELECT ");
			select(query);

			query.append(" FROM logtef l ");

			query.append(" WHERE ");

			// pendencia -> ultimos 4 dias sem a data_trn
			query.append("  l.data_trn BETWEEN :data_menos4 AND :data_menos1");
			query.append("  AND l.datapend = :datatrn");

			if (filter.getStartTime() != null && filter.getEndTime() != null) {
				query.append(" AND l.horapend BETWEEN :horainicial AND :horafinal");
			}

			if (filter.getPosNumber() != null) {
				query.append(" AND l.idt_terminal = :numpdv");
			}

			query.append("  AND l.codlojasitef IN (SELECT codlojasitef FROM loja_view WHERE cod_usuario = :cod_usuario)");
		}
		return query.toString();
	}

	private void select(StringBuilder query) {
		query.append("  l.cod_sit, ");
		query.append("  l.data_trn, ");
		query.append("  l.nsu_sitef, ");
		query.append("  l.codlojasitef, ");
		query.append("  l.codlojasitef_tronco, ");
		query.append("  l.hora_trn, ");
		query.append("  l.valor_trn, ");
		query.append("  l.codigo_resp, ");
		query.append("  l.cod_autoriz, ");
		query.append("  l.cuponfiscal, ");
		query.append("  (SELECT p.descr_produto FROM produtos p WHERE p.idt_produto = l.idt_produto AND rownum = 1) AS bandeira, "); //rownum = 1 pra realizar um stopkey
		query.append("  (SELECT t.descr_trn FROM transacoes t WHERE t.cod_trnweb = l.cod_trnweb AND rownum = 1) AS infotransacao, "); //rownum = 1 pra realizar um stopkey
		query.append("  l.documento AS documento, ");
		query.append("  l.num_parcelas AS num_parcelas, ");
		query.append("  l.idt_terminal AS num_pdv, ");
		query.append("  l.nsu_host AS nsuhost, ");
		query.append("  (SELECT m.descr_modoent FROM modoentrada m WHERE m.cdmodoentrada = l.cdmodoentrada AND rownum = 1) AS modoentrada, "); //rownum = 1 pra realizar um stopkey
		query.append("  l.codigo_estab AS codestabelecimento, ");
		query.append("  (SELECT lj.cnpj FROM loja lj WHERE l.codlojasitef = lj.codlojasitef) AS cnpj, ");
		//query.append("  '' AS cnpj, ");
		query.append("  'logtef' AS tabletef, ");
		query.append("  l.ipsitef, ");
		query.append("  l.datapend, ");
		query.append("  l.horapend, ");
		query.append("  l.estado_trn, ");
		query.append("  l.idt_terminal, ");
		query.append("  l.se_cliente, ");
		query.append("  l.quem_negou, ");
		query.append("  l.datafiscal, ");
		query.append("  l.horafiscal, ");
		query.append("  l.codcli, ");
		query.append("  NULL AS codoperadora, ");
		query.append("  NULL AS codfilial, ");
		query.append("  NULL AS valor_repasse, ");
		query.append("  NULL AS valor_avista, ");
		query.append("  NULL AS valor_cartao, ");
		query.append("  l.cod_rede_autoriz, ");
		query.append("  l.dthr_exportacao, ");
		query.append("  l.cod_autoriz_aux, ");
		query.append("  l.nsu_host_aux AS nsuhostaux, ");
		query.append("  l.nsu_host_original AS nsuhostoriginal, ");
		query.append("  NVL(l.forma_pagamento_cd, '00') AS forma_pagamento_cd, ");
		query.append("  NVL(l.subtipo_pagamento_cd, '00') AS subtipo_pagamento_cd, ");
		query.append("  l.bandeira_pagamento_cd AS bandeira_pagamento_cd, ");
		query.append("  l.psp, ");
		query.append("  l.tid, ");
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
		query.append("  l.nsucanchost ");
	}
}
