package com.fiserv.dummy_transaction_api.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionTO implements Serializable {

    private static final long serialVersionUID = 5698726765918161543L;

    private String codlojasitef;
    private String date;
    private Integer codsit;
    private String nsuSitef;
    private String seCliente;
    private Long value;

    public TransactionTO(String codlojasitef, String date, Integer codsit, String nsuSitef, String seCliente, Long value) {
        this.codlojasitef = codlojasitef;
        this.date = date;
        this.codsit = codsit;
        this.nsuSitef = nsuSitef;
        this.seCliente = seCliente;
        this.value = value;
    }

    public String getCodlojasitef() {
        return codlojasitef;
    }

    public String getDate() {
        return date;
    }

    public Integer getCodsit() {
        return codsit;
    }

    public String getNsuSitef() {
        return nsuSitef;
    }

    public String getSeCliente() {
        return seCliente;
    }

    public Long getValue() {
        return value;
    }
}
