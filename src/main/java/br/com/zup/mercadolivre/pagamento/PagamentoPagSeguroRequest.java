package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPagSeguroRequest implements PagamentoRequest {

    @NotBlank
    private String pagamentoId;
    @NotNull
    private StatusPagSeguro status;

    public PagamentoPagSeguroRequest(@NotBlank String pagamentoId, @NotBlank StatusPagSeguro status) {
        this.pagamentoId = pagamentoId;
        this.status = status;
    }

    public String getPagamentoId() {
        return pagamentoId;
    }

    public StatusPagSeguro getStatus() {
        return status;
    }

    public Pagamento toPagamento(Compra compra) {
        return new Pagamento(this.pagamentoId, this.status.normaliza(), compra);
    }
}
