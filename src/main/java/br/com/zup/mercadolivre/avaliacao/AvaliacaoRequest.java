package br.com.zup.mercadolivre.avaliacao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.validation.constraints.*;

public class AvaliacaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public AvaliacaoRequest(@NotNull @Min(1) @Max(5) Integer nota,
                            @NotBlank String titulo,
                            @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Avaliacao toModel(Usuario usuario, Produto produto) {
        return new Avaliacao(nota, titulo, descricao, usuario, produto);
    }
}
