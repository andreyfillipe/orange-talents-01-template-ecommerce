package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.config.validacao.beanValidation.ExistsId;
import br.com.zup.mercadolivre.config.validacao.beanValidation.UniqueValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(nomeClasse = Categoria.class, nomeCampo = "nome")
    private String nome;
    @Positive
    @ExistsId(nomeClasse = Categoria.class, nomeCampo = "id")
    private Long categoriaId;

    public CategoriaRequest(@NotBlank String nome,
                            @Positive Long categoriaId) {
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria;
        if (this.categoriaId != null) {
            Categoria categoriaMae = categoriaRepository.findById(this.categoriaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id da categoria mãe não existe"));
            categoria = new Categoria(this.nome, categoriaMae);
        } else {
            categoria = new Categoria(this.nome);
        }
        return categoria;
    }
}
