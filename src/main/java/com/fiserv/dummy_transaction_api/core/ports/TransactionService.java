package com.fiserv.dummy_transaction_api.core.ports;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;

import java.util.List;

public interface TransactionService {

    List<TransactionTO> getAllByDate(String date);

    List<TransactionTO> getAllByStore(String store);
}
