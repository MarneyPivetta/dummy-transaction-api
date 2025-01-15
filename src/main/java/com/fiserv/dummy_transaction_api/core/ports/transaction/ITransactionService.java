package com.fiserv.dummy_transaction_api.core.ports.transaction;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;

import com.fiserv.dummy_transaction_api.core.domain.UserTO;
import java.util.List;

public interface ITransactionService {

    List<TransactionTO> getAllByDate(String date, UserTO user);

}
