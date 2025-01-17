package com.fiserv.dummy_transaction_api.core.application.transaction.service;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.EnvProductType;
import com.fiserv.dummy_transaction_api.core.application.transaction.repository.TransactionSQL;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;
import com.fiserv.dummy_transaction_api.core.domain.TransactionDTO;
import com.fiserv.dummy_transaction_api.core.domain.UserDTO;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionService;
import com.fiserv.dummy_transaction_api.basic.sql.SqlQuery;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionDTO> getAllByDate(TransactionFilterDTO filter, UserDTO user) {

        List<EnvProductType> products = new ArrayList<>();
        products.add(EnvProductType.TEF);
        products.add(EnvProductType.RECARGA);
        products.add(EnvProductType.AMBEV);
        products.add(EnvProductType.CARTAO_PRE);
        products.add(EnvProductType.PBM);
        products.add(EnvProductType.SAV);
        products.add(EnvProductType.CSF);
        products.add(EnvProductType.CB);


        SqlQuery query = new TransactionSQL(products).findAllByDate(filter);

        return repository.executeTransactionQuery(query);
    }

}
