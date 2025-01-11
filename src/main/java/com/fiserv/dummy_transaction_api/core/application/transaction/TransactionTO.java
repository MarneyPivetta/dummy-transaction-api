package com.fiserv.dummy_transaction_api.core.application.transaction;

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

    private String sitefStoreCode;
    private String date;
    private Integer codsit;
    private String nsuSitef;
    private String seClient;
    private Long value;

    public TransactionTO(String sitefStoreCode, String date, Integer codsit, String nsuSitef, String seClient, Long value) {
        this.sitefStoreCode = sitefStoreCode;
        this.date = date;
        this.codsit = codsit;
        this.nsuSitef = nsuSitef;
        this.seClient = seClient;
        this.value = value;
    }

    public String getSitefStoreCode() {
        return sitefStoreCode;
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

    public String getSeClient() {
        return seClient;
    }

    public Long getValue() {
        return value;
    }
}
