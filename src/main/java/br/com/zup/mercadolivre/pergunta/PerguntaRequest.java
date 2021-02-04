package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(titulo, usuario, produto);
    }
}
