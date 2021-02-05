package br.com.zup.mercadolivre.pagamento;

public enum StatusPagSeguro {

    SUCESSO, ERRO;

    public StatusPagamento normaliza() {
        if (this.equals(SUCESSO)) {
            return StatusPagamento.SUCESSO;
        }
        return StatusPagamento.ERRO;
    }
}
