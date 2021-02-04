package br.com.zup.mercadolivre.avaliacao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer nota;
    @NotBlank
    @Column(nullable = false)
    private String titulo;
    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String descricao;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Deprecated
    public Avaliacao() {
    }

    public Avaliacao(@NotNull @Min(1) @Max(5) Integer nota,
                     @NotBlank String titulo,
                     @NotBlank @Size(max = 500) String descricao,
                     @NotNull Usuario usuario,
                     @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
