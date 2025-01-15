package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterTO;

public interface ISQLBuilder {

	String findAllByDate(TransactionFilterTO filter);

}
