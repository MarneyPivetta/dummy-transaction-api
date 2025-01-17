package com.fiserv.dummy_transaction_api.core.ports.transaction;

import com.fiserv.dummy_transaction_api.core.domain.TransactionDTO;

import com.fiserv.dummy_transaction_api.basic.sql.SqlQuery;
import java.util.List;

public interface ITransactionRepository {
    List<TransactionDTO> executeTransactionQuery(SqlQuery query);
}
