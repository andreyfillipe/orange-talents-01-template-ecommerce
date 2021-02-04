package br.com.zup.mercadolivre.caracteristica;

import br.com.zup.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Column(nullable = false)
    private String descricao;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Deprecated
    public Caracteristica(){
    }

    public Caracteristica(@NotBlank String nome,
                          @NotBlank String descricao,
                          @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caracteristica that = (Caracteristica) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
