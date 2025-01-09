package com.fiserv.dummy_transaction_api.outbound.database;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import com.fiserv.dummy_transaction_api.core.ports.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionJpaRepository implements TransactionRepository {

    private final EntityManager entityManager;

    public TransactionJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TransactionTO> getAllByDate(String date) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT t ");
        sb.append(" FROM TransactionEntity t ");
        sb.append(" WHERE t.dataTrn = :date");

        TypedQuery<TransactionEntity> query = entityManager.createQuery(sb.toString(), TransactionEntity.class);
        query.setParameter("date", date);

        return query.getResultList().stream()
                .map(TransactionEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionTO> getAllByStore(String store) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT t ");
        sb.append(" FROM TransactionEntity t ");
        sb.append(" WHERE t.codlojasitef = :store");

        TypedQuery<TransactionEntity> query = entityManager.createQuery(sb.toString(), TransactionEntity.class);
        query.setParameter("store", store);

        return query.getResultList().stream()
                .map(TransactionEntity::toDomain)
                .collect(Collectors.toList());
    }

}
