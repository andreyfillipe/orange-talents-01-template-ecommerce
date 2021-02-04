package br.com.zup.mercadolivre.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Deprecated
    public Categoria() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria(@NotBlank String nome,
                     Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
