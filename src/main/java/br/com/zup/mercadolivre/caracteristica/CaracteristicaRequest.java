package br.com.zup.mercadolivre.caracteristica;

import br.com.zup.mercadolivre.produto.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome,
                                 @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto) {
        return new Caracteristica(this.nome, this.descricao, produto);
    }
}
