package com.fiserv.dummy_transaction_api.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.TransactionStatusCode;
import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.TransactionType;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@ToString
@EqualsAndHashCode
public class TransactionDTO implements Serializable {

    private static final long serialVersionUID = 5698726765918161543L;

    // Chaves
    private final long codSit;
    private final String transactionDate;
    private final String nsuSitef;
    private final String sitefStoreCode;

    private final String trunkStoreCode;

    // Campos Visiveis
    private final String transactionTime;
    private final String transactionValue;
    private final String transactionType;
    private final String responseCode;
    private final TransactionStatusCode transactionStatusCode;
    private final String authorizationCode;
    private final String cupomFiscal; //
    private final String cardType;
    private final String transactionInfo;
    private final String document;
    private final String installmentsNumber;
    private final String posNumber;
    private final String nsuHost;
    private final String nsuHostOriginal;
    private final String inputMode;
    private final String establishmentCode;
    private final String cnpj;
    private final String networkDescription;
    private final String clientCode;
    private final String deniedBy;
    private final String fiscalDate;
    private final String fiscalTime;
    private final String codcli; //
    private final String mobileOperatorCode;
    private final String storeBranchCode;
    private final String transferValue;
    private final String amountPaidInFull;
    private final String amountPaidByCard;
    private final String networkAuthorizationCode;
    private final String exportDateTime;
    private final String authorizationCodeAux;
    private final String nsuHostAux;
    private final String paymentMethodCD;
    private final String subtypePaymentCD;
    private final String brandPaymentCD;
    private final String psp; //
    private final String tid; //
    private final String paymentValue1;
    private final String paymentMethod1;
    private final String paymentValue2;
    private final String paymentMethod2;
    private final String paymentValue3;
    private final String paymentMethod3;
    private final String paymentValue4;
    private final String paymentMethod4;
    private final String docCancel;

    @JsonIgnore
    private final String table;

    @JsonIgnore
    private final String ipSitef;

    @JsonIgnore
    private final String pendingDate;

    @JsonIgnore
    private final String pendingTime;

    @JsonIgnore
    private final String trnStatus;

    @JsonIgnore
    private final TransactionType trnType;

    @JsonIgnore
    private final String terminalID;

    public TransactionDTO() {
        this(-1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public TransactionDTO(long codSit, String transactionDate, String nsuSitef, String sitefStoreCode, String trunkStoreCode,
                          String transactionValue, String ipSitef, String responseCode, String pendingDate, String pendingTime,
                          String trnStatus, String terminalID, String authorizationCode, String transactionTime, String cupomFiscal,
                          String cardType, String transactionInfo, String table, String document, String installmentsNumber,
                          String posNumber, String nsuHost, String nsuHostOriginal, String inputMode, String establishmentCode,
                          String cnpj, String networkDescription, String seClient, String deniedBy, String fiscalDate,
                          String fiscalTime, String codcli, String mobileOperatorCode, String storeBranchCode, String transferValue,
                          String amountPaidInFull, String amountPaidByCard, String networkAuthorizationCode, String exportDateTime,
                          String authorizationCodeAux, String nsuHostAux, String paymentMethodCD, String subtypePaymentCD,
                          String brandPaymentCD, String psp, String tid, String paymentValue1, String paymentMethod1,
                          String paymentValue2, String paymentMethod2, String paymentValue3, String paymentMethod3,
                          String paymentValue4, String paymentMethod4, String docCancel) {

        this.transactionDate = transactionDate;
        this.codSit = codSit;
        this.nsuSitef = nsuSitef;
        this.sitefStoreCode = sitefStoreCode;
        this.trunkStoreCode = trunkStoreCode;

        this.transactionTime = transactionTime;
        this.table = table;
        this.ipSitef = ipSitef;
        this.pendingDate = pendingDate;
        this.pendingTime = pendingTime;
        this.trnStatus = trnStatus;
        this.terminalID = terminalID;
        this.authorizationCode = authorizationCode;
        this.cupomFiscal = cupomFiscal;
        this.trnType = TransactionType.fromTable(table);
        this.transactionInfo = transactionInfo;
        this.cardType = cardType;
        this.document = document;
        this.installmentsNumber = installmentsNumber;
        this.posNumber = posNumber;
        this.nsuHost = nsuHost;
        this.nsuHostOriginal = nsuHostOriginal;
        this.inputMode = inputMode;
        this.establishmentCode = establishmentCode;
        this.cnpj = cnpj;
        this.networkDescription = networkDescription;
        this.clientCode = seClient;
        this.deniedBy = deniedBy;
        this.fiscalDate = fiscalDate;
        this.fiscalTime = fiscalTime;
        this.codcli = codcli;
        this.mobileOperatorCode = mobileOperatorCode;
        this.storeBranchCode = storeBranchCode;
        this.transactionValue = transactionValue;
        this.responseCode = responseCode;
        this.transferValue = transferValue;
        this.amountPaidInFull = amountPaidInFull;
        this.amountPaidByCard = amountPaidByCard;
        this.networkAuthorizationCode = networkAuthorizationCode;
        this.exportDateTime = exportDateTime;
        this.authorizationCodeAux = authorizationCodeAux;
        this.nsuHostAux = nsuHostAux;
        this.paymentMethodCD = paymentMethodCD;
        this.subtypePaymentCD = subtypePaymentCD;
        this.brandPaymentCD = brandPaymentCD;
        this.psp = psp;
        this.tid = tid;
        this.paymentValue1 = paymentValue1;
        this.paymentMethod1 = paymentMethod1;
        this.paymentValue2 = paymentValue2;
        this.paymentMethod2 = paymentMethod2;
        this.paymentValue3 = paymentValue3;
        this.paymentMethod3 = paymentMethod3;
        this.paymentValue4 = paymentValue4;
        this.paymentMethod4 = paymentMethod4;
        this.docCancel = docCancel;

        this.transactionType = (trnType == null ? TransactionType.NAO_IDENTIFICADO.getLabel() : trnType.getLabel());
        TransactionStatusCode estadoTemp = TransactionStatusCode.DESCONHECIDO;

        try {
            estadoTemp = TransactionStatusCode.defineEstado(Integer.parseInt(trnStatus), responseCode);

        } catch (Exception e) {
        }

        this.transactionStatusCode = estadoTemp;
    }


}
