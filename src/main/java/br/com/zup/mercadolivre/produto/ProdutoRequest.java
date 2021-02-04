package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.config.validacao.beanValidation.ExistsId;
import br.com.zup.mercadolivre.config.validacao.beanValidation.UniqueValue;
import br.com.zup.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    @UniqueValue(nomeClasse = Produto.class, nomeCampo = "nome")
    private String nome;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @ExistsId(nomeClasse = Categoria.class, nomeCampo = "id")
    private Long categoriaId;
    @NotNull
    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutoRequest(@NotBlank String nome,
                          @NotBlank
                          @Size(max = 1000) String descricao,
                          @Positive Integer quantidade,
                          @NotNull @Positive BigDecimal valor,
                          @NotNull Long categoriaId,
                          @Size(min = 3) List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(this.categoriaId).orElseThrow(() -> new IllegalArgumentException("Categoria não existe com o id informado"));
        return new Produto(this.nome,
                           this.descricao,
                           this.quantidade,
                           this.valor,
                           categoria,
                           usuario,
                           this.caracteristicas
                           );
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> buscarCaracteristicaIgual() {
        Set<String> nomesRepetidos = new HashSet<>();
        Set<String> resultados = new HashSet<>();
        for (CaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if (!nomesRepetidos.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
}
