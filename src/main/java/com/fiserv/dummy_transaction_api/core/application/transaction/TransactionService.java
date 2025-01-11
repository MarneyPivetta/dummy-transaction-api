package com.fiserv.dummy_transaction_api.core.application.transaction;

import com.fiserv.dummy_transaction_api.core.application.user.UserTO;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionTO> getAllByDate(String date, UserTO user) {

        // logicas de negocio...

        return repository.getAllByDate(date, user);
    }

    @Override
    public List<TransactionTO> getAllByStore(String store, UserTO user) {

        // logicas de negocio...

        return repository.getAllByStore(store, user);
    }
}
