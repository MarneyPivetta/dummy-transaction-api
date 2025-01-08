package com.fiserv.dummy_transaction_api.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
public class TransactionTO {

    private String codlojasitef;
    private String date;
    private int codsit;
    private String seCliente;
    private BigDecimal value;

}
