package com.fiserv.dummy_transaction_api.core.application.transaction.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <p>
 * Entidade de domínio que descreve os "estados consolidados" de uma transação.
 * Estes estados são determinados pela combinação do estado bruto da transação e
 * o código de resposta.
 * </p>
 *
 * Essa classe foi retirada do Projeto Zeus
 */
public enum TransactionStatusCode {

	/**
	 * <p>
	 * A transação iniciou o primeiro pedido ao host, informando os dados da
	 * transação e não obteve nenhuma resposta ainda autorizando a transação.
	 * </p>
	 */
	AGUARDANDO_RESPOSTA_HOST(false, 1),

	/**
	 * <p>
	 * A transação foi autorizada pelo host e o host foi informado
	 * posteriormente por intervenção humana que a transação não foi a termo.
	 * </p>
	 */
	CANCELADA_BACK_OFFICE(false, 134),

	/**
	 *
	 */
	CANCELADA_ADMINISTRATIVA(false, 150),

	/**
	 *
	 */
	CANCELADA_AUTOMATICA(false, 136),

	/**
	 *
	 */
	CANCELADA_ELETRONICA(false, 137),

	/**
	 * <p>
	 * Caso haja uma falha, por exemplo na hora de imprimir o comprovante, o
	 * host é informado de que a transação abortou.
	 * </p>
	 */
	CANCELADA_PDV(false, 6),

	/**
	 * Foi feito um pedido de autorização mas o host não respondeu ao pedido, o
	 * sitef respondeu ao PDV negando a transação.
	 */
	CANCELADA_TIMEOUT_HOST(false, 5),

	/**
	 * <p>
	 * A transação foi autorizada pelo host e a transação é marcada por
	 * intervenção humana como confirmada.
	 * </p>
	 */
	CONFIRMADA_BACK_OFFICE(true, 132),

	/**
	 *
	 */
	CONFIRMADA_ADMINISTRATIVO(true, 148),

	/**
	 * <p>
	 * A transação foi autorizada pelo host, comprovante impresso e enviada uma
	 * mensagem ao host confirmando a finalização.
	 * </p>
	 */
	CONFIRMADA_PDV(true, 4),

	/**
	 * Codigo para indicar um estado que não tenha sido mapeado.
	 */
	DESCONHECIDO(false, null),

	/**
	 * A transação foi a termo mas após a compra o cliente pediu o estorno da
	 * transação.
	 */
	ESTORNADA(false, 135),

	/**
	 * A transação foi a termo mas o cliente pediu o estorno para uma parte da
	 * transação.
	 */
	ESTORNADA_PARCIAL(false, 133),

	/**
	 * A transação foi negada pelo host e não pôde ir a termo.
	 */
	NEGADA(false, 4),

	/**
	 * Transação aprovada pelo chip mas está sendo negada pelo host.
	 */
	NEGADA_OFFLINE(false, 37),

	/**
	 * <p>
	 * O host autorizou a transação mas não foi informado se ela foi efetivada
	 * ou não no PDV.
	 * </p>
	 */
	PENDENTE(false, 2),

	/**
	 * <p>
	 * A transação foi aprovada por chip, o sitef respondeu com o comprovante e
	 * está aguardando o PDV retornar o sucesso da transação.
	 * </p>
	 */
	DESFAZIMENTO_PENDENCIA_VISA(false, 52),

	DESFAZIMENTO_NEGADA_ADM(false, 54),

	/**
	 * <p>
	 * A transação está em loop de envio ao host e no momento está aguardando
	 * para ser enviada novamente.
	 * </p>
	 */
	PENDENTE_ENVIO_HOST_OFFLINE(false, 35),

	PENDENTE_AJUSTES(false, 86),

	// Os estados abaixo ocorrem em módulos SiTef que utilizam 4 pernas, como
	// Stone

	/**
	 * Aguardando a resposta da confirmação.
	 */
	AGUARDANDO_RESP_CONF_HOST(false, 87),

	/**
	 * Aguardando a reposta da confirmação gerada pela manutenção de pendência.
	 */
	AGUARDANDO_RESP_CONF_HOST_LOCAL(false, 88),

	/**
	 * Aguardando resposta do desfazimento / não-confirmação.
	 */
	AGUARDANDO_RESP_DESFAZ_HOST(false, 89),

	/**
	 * Aguardando reposta do desfazimento / não confirmação gerada por
	 * manutenção de pendência.
	 */
	AGUARDANDO_RESP_DESFAZ_HOST_LOCAL(false, 90),

	/**
	 * Aguardando reposta do desfazimento / não confirmação gerada por timeout.
	 */
	AGUARDANDO_RESP_DESFAZ_HOST_TIMEOUT(false, 91),

	/**
	 * Host negou a transação de confirmação
	 */
	AJUSTE_CONFIRMACAO_HOST(false, 92),

	/**
	 * Host negou a transação de confirmação gerada pela manutenção de
	 * pendência.
	 */
	AJUSTE_CONFIRMACAO_HOST_LOCAL(false, 93),

	/**
	 * Host negou transação de desfazimento / não confirmação.
	 */
	AJUSTE_DESFAZIMENTO_HOST(false, 94),

	/**
	 * Host negou transação de desfazimento / não confirmação gerada pela
	 * manutenção de pendência.
	 */
	AJUSTE_DESFAZIMENTO_HOST_LOCAL(false, 95),

	/**
	 * Host negou transação de desfazimento / não confirmação gerada por um
	 * timeout.
	 */
	CANCELADA_TIMEOUT(false, 96);

	private static final Integer EFETUADA = Integer.valueOf(4);

	private final boolean valid;
	private final Integer statusTrn;

	private TransactionStatusCode(boolean valid, Integer statusTrn) {
		this.valid = valid;
		this.statusTrn = statusTrn;
	}

	/**
	 * Retorna se uma transação é valida(true) ou não(false:negada ou pendente).
	 *
	 * @return Se o estado representa uma transação válida(true) ou não(false).
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Retorna o valor do estado trn referente a este código de estado.
	 *
	 * @return O estado trn referente à este código de estado.
	 */
	public Integer getStatusTrn() {
		return statusTrn;
	}

	/**
	 * Define o código de estado de acordo com o estadoTrn e o código de
	 * resposta.
	 *
	 * @param estadoTrn
	 *            O estado da transação.
	 * @param codigoResposta
	 *            O código de resposta para a transação.
	 * @return O código de estado correspondente para o par passado.
	 */
	public static TransactionStatusCode defineEstado(Integer estadoTrn, String codigoResposta) {

		if (EFETUADA.equals(estadoTrn)) {
			return Arrays.asList("000", "00").contains(codigoResposta) ? CONFIRMADA_PDV : NEGADA;
		}

		return buscaPorEstadoTrn(estadoTrn);
	}

	public static int fromCodigoEstado(String codigoEstado) {
		for (TransactionStatusCode ce : values()) {
			if (ce.toString().equalsIgnoreCase(codigoEstado)) {
				return ce.statusTrn;
			}
		}
		return -1;
	}

	/**
	 * Retorna o conjunto de códigos de transação que representam transações
	 * validadas.
	 *
	 * @return O conjunto de códigos de transação que representam transações
	 *         validadas.
	 */
	public static Collection<TransactionStatusCode> validadas() {
		Collection<TransactionStatusCode> codigosEstado = new ArrayList<>();
		for (TransactionStatusCode codigoEstado : values()) {
			if (codigoEstado.equals(DESCONHECIDO)) {
				continue;
			}
			if (codigoEstado.isValid()) {
				codigosEstado.add(codigoEstado);
			}
		}
		return codigosEstado;
	}

	/**
	 * Retorna o conjunto de códigos de transação que representam transações não
	 * validadas.
	 *
	 * @return O conjunto de códigos de transação que representam transações não
	 *         validadas.
	 */
	public static Collection<TransactionStatusCode> naoValidadas() {
		Collection<TransactionStatusCode> codigosEstado = new ArrayList<>();
		for (TransactionStatusCode codigoEstado : values()) {
			if (codigoEstado.equals(DESCONHECIDO)) {
				continue;
			}
			if (!codigoEstado.isValid()) {
				codigosEstado.add(codigoEstado);
			}
		}
		return codigosEstado;
	}

	private static TransactionStatusCode buscaPorEstadoTrn(Integer estadoTrn) {
		for (TransactionStatusCode codigo : values()) {
			if (codigo.getStatusTrn() != null && codigo.getStatusTrn().equals(estadoTrn)) {
				return codigo;
			}
		}
		return DESCONHECIDO;
	}
}
