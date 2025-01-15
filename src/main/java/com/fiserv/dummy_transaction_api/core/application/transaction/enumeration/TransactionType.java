package com.fiserv.dummy_transaction_api.core.application.transaction.enumeration;

public enum TransactionType {

	LOGTEF("TEF", "logtef"),

	LOGPBM("PBM", "logpbm"),

	LOG_CB("Correspondente Bancario", "logcb"),

	LOG_RECARGA("Recarga Celular", "logrecargacel"),

	LOG_CARTAO_PRE("Gestao", "log_cartao_pre"),

	LOGCSF("CSF", "log_csf"),

	LOGSAV("SAV", "logsav"),

	LOGPROMOCAO("PROMOCAO", "logpromocao"),

	NAO_IDENTIFICADO("Nao Identificado", "");

	private String label;
	private String table;

	private TransactionType(String label, String table) {
		this.label = label;
		this.table = table;
	}

	public String getLabel() {
		return label;
	}

	public String getTable() {
		return table;
	}

	public static TransactionType fromTable(String table) {
		for (TransactionType type : values()) {
			if (type.table.equalsIgnoreCase(table)) {
				return type;
			}
		}

		return TransactionType.NAO_IDENTIFICADO;
	}

	@Override
	public String toString() {
		return getLabel();
	}

}

