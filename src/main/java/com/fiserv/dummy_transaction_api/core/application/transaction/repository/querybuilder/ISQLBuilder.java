package com.fiserv.dummy_transaction_api.core.application.transaction.repository.querybuilder;

import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;

public interface ISQLBuilder {

	String findAllByDate(TransactionFilterDTO filter);

}
