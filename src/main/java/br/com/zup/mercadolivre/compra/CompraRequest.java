package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.config.validacao.beanValidation.ExistsId;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    @ExistsId(nomeClasse = Produto.class, nomeCampo = "id")
    private Long produtoId;
    @NotNull
    private CompraTipoPagamento tipoPagamento;

    public CompraRequest(@NotNull @Positive Integer quantidade,
                         @NotNull Long produtoId,
                         @NotNull CompraTipoPagamento tipoPagamento) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.tipoPagamento = tipoPagamento;
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(quantidade, tipoPagamento, produto, usuario);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public CompraTipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
}
