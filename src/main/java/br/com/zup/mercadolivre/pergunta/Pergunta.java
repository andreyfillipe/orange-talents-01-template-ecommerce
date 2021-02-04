package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String titulo;
    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Deprecated
    public Pergunta(){
    }

    public Pergunta(@NotBlank String titulo,
                    @NotNull Usuario usuario,
                    @NotNull Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(id, pergunta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
