package com.fiserv.dummy_transaction_api.outbound.database;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "logtef")
public class TransactionEntity {

    @Id
    private String codlojasitef;
    private String dataTrn;
    private Integer codSit;
    private String nsuSitef;
    private String seCliente;
    private Long valorTrn;

    public TransactionTO toDomain() {
        return new TransactionTO(codlojasitef, dataTrn, codSit, nsuSitef, seCliente, valorTrn);
    }
}
