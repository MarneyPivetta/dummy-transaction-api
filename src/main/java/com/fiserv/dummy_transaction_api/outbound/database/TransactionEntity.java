package com.fiserv.dummy_transaction_api.outbound.database;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class TransactionEntity {

    private String codlojasitef;
    private String dateTrn;
    private int codsit;
    private String seCliente;
    private BigDecimal valorVenda;

    public TransactionTO toDomain() {
        return TransactionTO.builder()
                .codlojasitef(codlojasitef)
                .codsit(codsit)
                .date(dateTrn)
                .seCliente(seCliente)
                .value(valorVenda)
                .build();
    }
}
