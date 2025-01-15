package com.fiserv.dummy_transaction_api.core.application.transaction.service;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.EnvProductType;
import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import com.fiserv.dummy_transaction_api.core.application.transaction.repository.TransactionSQL;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterTO;
import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import com.fiserv.dummy_transaction_api.core.domain.UserTO;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionRepository;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionService;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
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
    public List<TransactionTO> getAllByDate(String date, UserTO user) {

        List<EnvProductType> products = new ArrayList<>();
        products.add(EnvProductType.TEF);
        products.add(EnvProductType.RECARGA);
        products.add(EnvProductType.AMBEV);
        products.add(EnvProductType.CARTAO_PRE);
        products.add(EnvProductType.PBM);
        products.add(EnvProductType.SAV);
        products.add(EnvProductType.CSF);
        products.add(EnvProductType.CB);

        TransactionFilterTO filter = new TransactionFilterTO();
        filter.setDate(date);
        filter.setDateType(FilterDateType.DATA_TRN);
        filter.setSeClient(user.getSeClient());

        SqlQuery query = new TransactionSQL(products).findAllByDate(filter);

        return repository.executeTransactionQuery(query);
    }

}
