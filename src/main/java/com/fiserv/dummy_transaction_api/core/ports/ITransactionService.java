package com.fiserv.dummy_transaction_api.core.ports;

import com.fiserv.dummy_transaction_api.core.transaction.TransactionTO;

import java.util.List;

public interface ITransactionService {

    List<TransactionTO> getAllByDate(String date, String seClient);

    List<TransactionTO> getAllByStore(String store, String seClient);
}
