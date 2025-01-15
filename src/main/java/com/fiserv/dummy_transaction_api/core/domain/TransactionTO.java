package com.fiserv.dummy_transaction_api.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.TransactionStatusCode;
import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.TransactionType;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionTO implements Serializable {

    private static final long serialVersionUID = 5698726765918161543L;

    // Chaves
    private final long codSit;
    private final String dataTrn;
    private final String nsuSitef;
    private final String codLojaSitef;

    private final String codLojaTronco;

    // Campos Visiveis
    private final String horaTrn;
    private final String valor;
    private final String tipo;
    private final String codResp;
    private final TransactionStatusCode estado;
    private final String codAutorizacao;
    private final String cupomFiscal;
    private final String produto;
    private final String infoTransacao;
    private final String documento;
    private final String numParcelas;
    private final String numPdv;
    private final String nsuHost;
    private final String nsuHostOriginal;
    private final String modoEntrada;
    private final String codEstabelecimento;
    private final String cnpj;
    private final String descrRede;
    private final String codigoCliente;
    private final String quemNegou;
    private final String dataFiscal;
    private final String horaFiscal;
    private final String codcli;
    private final String codoperadora;
    private final String codfilial;
    private final String valorRepasse;
    private final String valorAVista;
    private final String valorCartao;
    private final String codRedeAutoriz;
    private final String dthrExportacao;
    private final String codAutorizacaoAux;
    private final String nsuHostAux;
    private final String formaPagamentoCD;
    private final String subtipoPagamentoCD;
    private final String bandeiraPagamentoCD;
    private final String psp;
    private final String tid;
    private final String valorPagamento1;
    private final String formaPagamento1;
    private final String valorPagamento2;
    private final String formaPagamento2;
    private final String valorPagamento3;
    private final String formaPagamento3;
    private final String valorPagamento4;
    private final String formaPagamento4;
    private final String docCancel;

    // Campos NAO Visiveis
    private final String tabela;
    private final String ipSitef;
    private final String dataPend;
    private final String horaPend;
    private final String estadoTrn;
    private final TransactionType tipoTRN;
    private final String idtTerminal;

    public TransactionTO() {
        this(-1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public TransactionTO(long codSit, String data, String nsuSitef, String loja, String codLojaTronco, String valor,
                     String ipSitef, String codResp, String dataPend, String horaPend, String estadoTRN, String idtTerminal,
                     String codAutorizacao, String horaTrn, String cupomFiscal, String produto, String infoTransacao,
                     String tabela, String documento, String numParcelas, String numPdv, String nsuHost, String nsuHostOriginal,
                     String modoEntrada, String codEstabelecimento, String cnpj, String descrRede, String seCliente,
                     String quemNegou, String dataFiscal, String horaFiscal, String codcli, String codoperadora,
                     String codfilial, String valorRepasse, String valorAVista, String valorCartao, String codRedeAutoriz,
                     String dthrExportacao, String codAutorizacaoAux, String nsuHostAux, String formaPagamentoCD,
                     String subtipoPagamentoCD, String bandeiraPagamentoCD, String psp, String tid, String valorPagamento1,
                     String formaPagamento1, String valorPagamento2, String formaPagamento2, String valorPagamento3,
                     String formaPagamento3, String valorPagamento4, String formaPagamento4, String docCancel) {
        this.dataTrn = data;
        this.codSit = codSit;
        this.nsuSitef = nsuSitef;
        this.codLojaSitef = loja;
        this.codLojaTronco = codLojaTronco;

        this.horaTrn = horaTrn;
        this.tabela = tabela;
        this.ipSitef = ipSitef;
        this.dataPend = dataPend;
        this.horaPend = horaPend;
        this.estadoTrn = estadoTRN;
        this.idtTerminal = idtTerminal;
        this.codAutorizacao = codAutorizacao;
        this.cupomFiscal = cupomFiscal;
        this.tipoTRN = TransactionType.fromTable(tabela);
        this.infoTransacao = infoTransacao;
        this.produto = produto;
        this.documento = documento;
        this.numParcelas = numParcelas;
        this.numPdv = numPdv;
        this.nsuHost = nsuHost;
        this.nsuHostOriginal = nsuHostOriginal;
        this.modoEntrada = modoEntrada;
        this.codEstabelecimento = codEstabelecimento;
        this.cnpj = cnpj;
        this.descrRede = descrRede;
        this.codigoCliente = seCliente;
        this.quemNegou = quemNegou;
        this.dataFiscal = dataFiscal;
        this.horaFiscal = horaFiscal;
        this.codcli = codcli;
        this.codoperadora = codoperadora;
        this.codfilial = codfilial;
        this.valor = valor;
        this.codResp = codResp;
        this.valorRepasse = valorRepasse;
        this.valorAVista = valorAVista;
        this.valorCartao = valorCartao;
        this.codRedeAutoriz = codRedeAutoriz;
        this.dthrExportacao = dthrExportacao;
        this.codAutorizacaoAux = codAutorizacaoAux;
        this.nsuHostAux = nsuHostAux;
        this.formaPagamentoCD = formaPagamentoCD;
        this.subtipoPagamentoCD = subtipoPagamentoCD;
        this.bandeiraPagamentoCD = bandeiraPagamentoCD;
        this.psp = psp;
        this.tid = tid;
        this.valorPagamento1 = valorPagamento1;
        this.formaPagamento1 = formaPagamento1;
        this.valorPagamento2 = valorPagamento2;
        this.formaPagamento2 = formaPagamento2;
        this.valorPagamento3 = valorPagamento3;
        this.formaPagamento3 = formaPagamento3;
        this.valorPagamento4 = valorPagamento4;
        this.formaPagamento4 = formaPagamento4;
        this.docCancel = docCancel;

        this.tipo = (tipoTRN == null ? TransactionType.NAO_IDENTIFICADO.getLabel() : tipoTRN.getLabel());
        TransactionStatusCode estadoTemp = TransactionStatusCode.DESCONHECIDO;

        try {
            estadoTemp = TransactionStatusCode.defineEstado(Integer.parseInt(estadoTrn), codResp);

        } catch (Exception e) {
        }

        this.estado = estadoTemp;
    }

    @JsonProperty
    public String getProduto() {
        return produto;
    }

    @JsonProperty
    public String getInfoTransacao() {
        return infoTransacao;
    }

    @JsonProperty
    public String getCupomFiscal() {
        return cupomFiscal;
    }

    @JsonProperty
    public String getCodAutorizacao() {
        return codAutorizacao;
    }

    @JsonProperty("cod_autorizacao_aux")
    public String getCodAutorizacaoAux() {
        return codAutorizacaoAux;
    }

    @JsonProperty
    public String getTipo() {
        return tipo;
    }

    @JsonProperty
    public TransactionStatusCode getEstado() {
        return estado;
    }

    @JsonProperty
    public String getNsuSitef() {
        return nsuSitef;
    }

    @JsonProperty
    public String getDocCancel() {
        return docCancel;
    }

    @JsonProperty
    public String getCodResp() {
        return codResp;
    }

    @JsonProperty
    public String getDataTrn() {
        return dataTrn;
    }

    @JsonProperty
    public String getHoraTrn() {
        return horaTrn;
    }

    @JsonProperty
    public String getValor() {
        return valor;
    }

    @JsonProperty
    public long getCodSit() {
        return codSit;
    }

    @JsonProperty
    public String getCodLojaSitef() {
        return codLojaSitef;
    }

    @JsonProperty
    public String getCodLojaTronco() {
        return codLojaTronco;
    }

    @JsonProperty
    public String getDocumento() {
        return documento;
    }

    @JsonProperty
    public String getNumParcelas() {
        return numParcelas;
    }

    @JsonProperty
    public String getNumPdv() {
        return numPdv;
    }

    @JsonProperty
    public String getNsuHost() {
        return nsuHost;
    }

    @JsonProperty
    public String getNsuHostAux() {
        return nsuHostAux;
    }

    @JsonProperty
    public String getNsuHostOriginal() {
        return nsuHostOriginal;
    }

    @JsonProperty
    public String getCodigoCliente() {
        return codigoCliente;
    }

    @JsonProperty
    public String getModoEntrada() {
        return modoEntrada;
    }

    @JsonProperty
    public String getCodEstabelecimento() {
        return codEstabelecimento;
    }

    @JsonProperty
    public String getCnpj() {
        return cnpj;
    }

    @JsonProperty
    public String getDescrRede() {
        return descrRede;
    }

    @JsonProperty("quem_negou")
    public String getQuemNegou() {
        return quemNegou;
    }

    @JsonProperty("data_fiscal")
    public String getDataFiscal() {
        return dataFiscal;
    }

    @JsonProperty("hora_fiscal")
    public String getHoraFiscal() {
        return horaFiscal;
    }

    @JsonProperty("id_cliente")
    public String getCodCli() {
        return codcli;
    }

    @JsonProperty("cod_operadora")
    public String getCodOperadora() {
        return codoperadora;
    }

    @JsonProperty("cod_filial")
    public String getCodFilial() {
        return codfilial;
    }

    @JsonProperty("valor_repasse")
    public String getValorRepasse() {
        return valorRepasse;
    }

    @JsonProperty("valor_avista")
    public String getValorAVista() {
        return valorAVista;
    }

    @JsonProperty("valor_cartao")
    public String getValorCartao() {
        return valorCartao;
    }

    @JsonProperty("cod_rede_autoriz")
    public String getCodRedeAutoriz() {
        return codRedeAutoriz;
    }

    @JsonProperty("data_exportacao")
    public String getDthrExportacao() {
        return dthrExportacao;
    }

    @JsonProperty("forma_pagamento_cd")
    public String getFormaPagamentoCD() {
        return formaPagamentoCD;
    }

    @JsonProperty("subtipo_pagamento_cd")
    public String getSubtipoPagamentoCD() {
        return subtipoPagamentoCD;
    }

    @JsonProperty("bandeira_pagamento_cd")
    public String getBandeiraPagamentoCD() {
        return bandeiraPagamentoCD;
    }

    @JsonProperty("psp")
    public String getPsp() {
        return psp;
    }

    @JsonProperty("tid")
    public String getTid() {
        return tid;
    }

    @JsonProperty("valor_pagamento_1")
    public String getValorPagamento1() {
        return valorPagamento1;
    }

    @JsonProperty("forma_pagamento_1")
    public String getFormaPagamento1() {
        return formaPagamento1;
    }

    @JsonProperty("valor_pagamento_2")
    public String getValorPagamento2() {
        return valorPagamento2;
    }

    @JsonProperty("forma_pagamento_2")
    public String getFormaPagamento2() {
        return formaPagamento2;
    }

    @JsonProperty("valor_pagamento_3")
    public String getValorPagamento3() {
        return valorPagamento3;
    }

    @JsonProperty("forma_pagamento_3")
    public String getFormaPagamento3() {
        return formaPagamento3;
    }


    @JsonProperty("valor_pagamento_4")
    public String getValorPagamento4() {
        return valorPagamento4;
    }

    @JsonProperty("forma_pagamento_4")
    public String getFormaPagamento4() {
        return formaPagamento4;
    }

    /*
     * Campos ignorados no JSON
     */
    @JsonIgnore
    public String getTabela() {
        return tabela;
    }

    @JsonIgnore
    public String getIpSitef() {
        return ipSitef;
    }

    @JsonIgnore
    public String getIdtTerminal() {
        return idtTerminal;
    }

    @JsonIgnore
    public String getDataPend() {
        return dataPend;
    }

    @JsonIgnore
    public String getHoraPend() {
        return horaPend;
    }

    @JsonIgnore
    public String getEstadoTrn() {
        return estadoTrn;
    }

    @JsonIgnore
    public TransactionType getTipoTRN() {
        return tipoTRN;
    }

    @JsonIgnore
    public boolean isNotPendente() {
        return estado != TransactionStatusCode.PENDENTE;
    }

    @Override
    public String toString() {
        return "Transacao [codSit=" + codSit + ", dataTrn=" + dataTrn + ", nsuSitef=" + nsuSitef + ", codLojaSitef="
                + codLojaSitef + ", horaTrn=" + horaTrn + ", valor=" + valor + ", tipo=" + tipo + ", codResp=" + codResp
                + ", estado=" + estado + ", codAutorizacao=" + codAutorizacao + ", cupomFiscal=" + cupomFiscal
                + ", produto=" + produto + ", infoTransacao=" + infoTransacao + ", documento=" + documento
                + ", numParcelas=" + numParcelas + ", numPdv=" + numPdv + ", nsuHost=" + nsuHost + ", nsuHostOriginal="
                + nsuHostOriginal + ", modoentrada=" + modoEntrada + ", codEstabelecimento=" + codEstabelecimento
                + ", cnpj=" + cnpj + ", descrRede=" + descrRede + ", tabela=" + tabela + ", ipSitef=" + ipSitef
                + ", dataPend=" + dataPend + ", horaPend=" + horaPend + ", estadoTrn=" + estadoTrn + ", tipoTRN="
                + tipoTRN + ", idtTerminal=" + idtTerminal + ", codigoCliente=" + codigoCliente + ", codoperadora="
                + codoperadora + ", codfilial=" + codfilial + ", valorRepasse=" + valorRepasse + ", valorAVista="
                + valorAVista + ", valorCartao=" + valorCartao + ", nsuHostAux=" + nsuHostAux + ", formaPagamentoCD="
                + formaPagamentoCD + ", subtipoPagamentoCD=" + subtipoPagamentoCD + ", bandeiraPagamentoCD="
                + bandeiraPagamentoCD + ", psp=" + psp + ", valor_pagamento_1=" + valorPagamento1
                + ", forma_pagamento_1=" + formaPagamento1 + ", valor_pagamento_2=" + valorPagamento2
                + ", forma_pagamento_2=" + formaPagamento2 + ", valor_pagamento_3=" + valorPagamento3
                + ", forma_pagamento_3=" + formaPagamento3 + ", valor_pagamento_4=" + valorPagamento4
                + ", forma_pagamento_4=" + formaPagamento4 + "]";
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((codLojaSitef == null) ? 0 : codLojaSitef.hashCode());
        result = prime * result + ((codResp == null) ? 0 : codResp.hashCode());
        result = prime * result + (int) (codSit ^ (codSit >>> 32));
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((dataPend == null) ? 0 : dataPend.hashCode());
        result = prime * result + ((dataTrn == null) ? 0 : dataTrn.hashCode());
        result = prime * result + ((estadoTrn == null) ? 0 : estadoTrn.hashCode());
        result = prime * result + ((horaPend == null) ? 0 : horaPend.hashCode());
        result = prime * result + ((idtTerminal == null) ? 0 : idtTerminal.hashCode());
        result = prime * result + ((ipSitef == null) ? 0 : ipSitef.hashCode());
        result = prime * result + ((nsuSitef == null) ? 0 : nsuSitef.hashCode());
        result = prime * result + ((tabela == null) ? 0 : tabela.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((tipoTRN == null) ? 0 : tipoTRN.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
        result = prime * result + ((codoperadora == null) ? 0 : codoperadora.hashCode());
        result = prime * result + ((codfilial == null) ? 0 : codfilial.hashCode());
        result = prime * result + ((valorRepasse == null) ? 0 : valorRepasse.hashCode());
        result = prime * result + ((valorAVista == null) ? 0 : valorAVista.hashCode());
        result = prime * result + ((valorCartao == null) ? 0 : valorCartao.hashCode());
        result = prime * result + ((codRedeAutoriz == null) ? 0 : codRedeAutoriz.hashCode());
        result = prime * result + ((dthrExportacao == null) ? 0 : dthrExportacao.hashCode());
        result = prime * result + ((codAutorizacaoAux == null) ? 0 : codAutorizacaoAux.hashCode());
        result = prime * result + ((formaPagamentoCD == null) ? 0 : formaPagamentoCD.hashCode());
        result = prime * result + ((subtipoPagamentoCD == null) ? 0 : subtipoPagamentoCD.hashCode());
        result = prime * result + ((bandeiraPagamentoCD == null) ? 0 : bandeiraPagamentoCD.hashCode());
        result = prime * result + ((psp == null) ? 0 : psp.hashCode());
        result = prime * result + ((codfilial == null) ? 0 : codfilial.hashCode());
        result = prime * result + ((valorPagamento1 == null) ? 0 : valorPagamento1.hashCode());
        result = prime * result + ((formaPagamento1 == null) ? 0 : formaPagamento1.hashCode());
        result = prime * result + ((valorPagamento2 == null) ? 0 : valorPagamento2.hashCode());
        result = prime * result + ((formaPagamento2 == null) ? 0 : formaPagamento2.hashCode());
        result = prime * result + ((valorPagamento3 == null) ? 0 : valorPagamento3.hashCode());
        result = prime * result + ((formaPagamento3 == null) ? 0 : formaPagamento3.hashCode());
        result = prime * result + ((valorPagamento4 == null) ? 0 : valorPagamento4.hashCode());
        result = prime * result + ((formaPagamento4 == null) ? 0 : formaPagamento4.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransactionTO other = (TransactionTO) obj;
        if (codLojaSitef == null) {
            if (other.codLojaSitef != null)
                return false;
        } else if (!codLojaSitef.equals(other.codLojaSitef))
            return false;
        if (codResp == null) {
            if (other.codResp != null)
                return false;
        } else if (!codResp.equals(other.codResp))
            return false;
        if (codSit != other.codSit)
            return false;
        if (estado != other.estado)
            return false;
        if (dataPend == null) {
            if (other.dataPend != null)
                return false;
        } else if (!dataPend.equals(other.dataPend))
            return false;
        if (dataTrn == null) {
            if (other.dataTrn != null)
                return false;
        } else if (!dataTrn.equals(other.dataTrn))
            return false;
        if (estadoTrn == null) {
            if (other.estadoTrn != null)
                return false;
        } else if (!estadoTrn.equals(other.estadoTrn))
            return false;
        if (horaPend == null) {
            if (other.horaPend != null)
                return false;
        } else if (!horaPend.equals(other.horaPend))
            return false;
        if (idtTerminal == null) {
            if (other.idtTerminal != null)
                return false;
        } else if (!idtTerminal.equals(other.idtTerminal))
            return false;
        if (ipSitef == null) {
            if (other.ipSitef != null)
                return false;
        } else if (!ipSitef.equals(other.ipSitef))
            return false;
        if (nsuSitef == null) {
            if (other.nsuSitef != null)
                return false;
        } else if (!nsuSitef.equals(other.nsuSitef))
            return false;
        if (tabela == null) {
            if (other.tabela != null)
                return false;
        } else if (!tabela.equals(other.tabela))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        if (tipoTRN != other.tipoTRN)
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        if (codoperadora == null) {
            if (other.codoperadora != null)
                return false;
        } else if (!codoperadora.equals(other.codoperadora))
            return false;
        if (codfilial == null) {
            if (other.codfilial != null)
                return false;
        } else if (!codfilial.equals(other.codfilial))
            return false;
        if (valorRepasse == null) {
            if (other.valorRepasse != null)
                return false;
        } else if (!valorRepasse.equals(other.valorRepasse))
            return false;
        if (valorAVista == null) {
            if (other.valorAVista != null)
                return false;
        } else if (!valorAVista.equals(other.valorAVista))
            return false;
        if (valorCartao == null) {
            if (other.valorCartao != null)
                return false;
        } else if (!valorCartao.equals(other.valorCartao))
            return false;
        else if (!codRedeAutoriz.equals(other.codRedeAutoriz))
            return false;
        else if (!dthrExportacao.equals(other.dthrExportacao))
            return false;
        else if (!codAutorizacaoAux.equals(other.codAutorizacaoAux))
            return false;
        else if (!formaPagamentoCD.equals(other.formaPagamentoCD))
            return false;
        else if (!subtipoPagamentoCD.equals(other.subtipoPagamentoCD))
            return false;
        else if (!bandeiraPagamentoCD.equals(other.bandeiraPagamentoCD))
            return false;
        else if (!psp.equals(other.psp))
            return false;
        if (valorPagamento1 == null) {
            if (other.valorPagamento1 != null)
                return false;
        } else if (!valorPagamento1.equals(other.valorPagamento1))
            return false;
        if (formaPagamento1 == null) {
            if (other.formaPagamento1 != null)
                return false;
        } else if (!formaPagamento1.equals(other.formaPagamento1))
            return false;
        if (valorPagamento2 == null) {
            if (other.valorPagamento2 != null)
                return false;
        } else if (!valorPagamento2.equals(other.valorPagamento2))
            return false;
        if (formaPagamento2 == null) {
            if (other.formaPagamento2 != null)
                return false;
        } else if (!formaPagamento2.equals(other.formaPagamento2))
            return false;
        if (valorPagamento3 == null) {
            if (other.valorPagamento3 != null)
                return false;
        } else if (!valorPagamento3.equals(other.valorPagamento3))
            return false;
        if (formaPagamento3 == null) {
            if (other.formaPagamento3 != null)
                return false;
        } else if (!formaPagamento3.equals(other.formaPagamento3))
            return false;
        if (valorPagamento4 == null) {
            if (other.valorPagamento4 != null)
                return false;
        } else if (!valorPagamento4.equals(other.valorPagamento4))
            return false;
        if (formaPagamento4 == null) {
            if (other.formaPagamento4 != null)
                return false;
        } else if (!formaPagamento4.equals(other.formaPagamento4))
            return false;

        return true;
    }
}
