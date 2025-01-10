package com.fiserv.dummy_transaction_api.core.transaction;

import com.fiserv.dummy_transaction_api.core.ports.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.ports.ITransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionTO> getAllByDate(String date, String seClient) {

        // logicas de negocio...

        return repository.getAllByDate(date, seClient);
    }

    @Override
    public List<TransactionTO> getAllByStore(String store, String seClient) {

        // logicas de negocio...

        return repository.getAllByStore(store, seClient);
    }
}
