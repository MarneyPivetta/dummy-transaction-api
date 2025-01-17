package com.fiserv.dummy_transaction_api.core.ports.transaction;

import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;
import com.fiserv.dummy_transaction_api.core.domain.TransactionDTO;

import com.fiserv.dummy_transaction_api.core.domain.UserDTO;
import java.util.List;

public interface ITransactionService {

    List<TransactionDTO> getAllByDate(TransactionFilterDTO filter, UserDTO user);

}
