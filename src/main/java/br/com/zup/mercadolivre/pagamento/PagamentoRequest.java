package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;

public interface PagamentoRequest {

    Pagamento toPagamento(Compra compra);
}
