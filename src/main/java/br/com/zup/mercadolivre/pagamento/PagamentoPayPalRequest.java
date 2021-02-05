package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPayPalRequest implements PagamentoRequest {

    @NotBlank
    private String pagamentoId;
    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;

    public PagamentoPayPalRequest(@NotBlank String pagamentoId, @NotNull @Min(0) @Max(1) Integer status) {
        this.pagamentoId = pagamentoId;
        this.status = status;
    }

    public String getPagamentoId() {
        return pagamentoId;
    }

    public Integer getStatus() {
        return status;
    }

    public Pagamento toPagamento(Compra compra) {
        StatusPagamento statusPagamento = this.status == 1 ? StatusPagamento.SUCESSO : StatusPagamento.ERRO;
        return new Pagamento(this.pagamentoId, statusPagamento , compra);
    }
}
