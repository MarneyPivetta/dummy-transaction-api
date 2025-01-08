package com.fiserv.dummy_transaction_api.core.service;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import com.fiserv.dummy_transaction_api.core.ports.TransactionRepository;
import com.fiserv.dummy_transaction_api.core.ports.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionTO> getAllByDate(String date) {

        // logicas de negocio...

        return repository.getAllByDate(date);
    }

    @Override
    public List<TransactionTO> getAllByStore(String store) {

        // logicas de negocio...

        return repository.getAllByStore(store);
    }
}
