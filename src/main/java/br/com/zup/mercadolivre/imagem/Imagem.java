package br.com.zup.mercadolivre.imagem;

import br.com.zup.mercadolivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @URL
    private String caminho;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Deprecated
    public Imagem(){
    }

    public Imagem(@NotBlank String caminho,
                  @NotNull @URL Produto produto) {
        this.caminho = caminho;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getCaminho() {
        return caminho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem imagem = (Imagem) o;
        return Objects.equals(id, imagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
