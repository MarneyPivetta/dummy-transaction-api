package com.fiserv.dummy_transaction_api.core.ports.transaction;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;

import com.fiserv.dummy_transaction_api.core.domain.UserTO;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import java.util.List;

public interface ITransactionRepository {
    List<TransactionTO> executeTransactionQuery(SqlQuery query);
}
