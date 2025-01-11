package com.fiserv.dummy_transaction_api.core.ports.transaction;

import com.fiserv.dummy_transaction_api.core.application.transaction.TransactionTO;

import com.fiserv.dummy_transaction_api.core.application.user.UserTO;
import java.util.List;

public interface ITransactionRepository {
    List<TransactionTO> getAllByDate(String date, UserTO user);

    List<TransactionTO> getAllByStore(String store, UserTO user);
}
