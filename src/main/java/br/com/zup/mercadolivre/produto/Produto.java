package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.avaliacao.Avaliacao;
import br.com.zup.mercadolivre.avaliacao.AvaliacaoResponse;
import br.com.zup.mercadolivre.caracteristica.Caracteristica;
import br.com.zup.mercadolivre.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadolivre.caracteristica.CaracteristicaResponse;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.imagem.Imagem;
import br.com.zup.mercadolivre.imagem.ImagemResponse;
import br.com.zup.mercadolivre.pergunta.Pergunta;
import br.com.zup.mercadolivre.pergunta.PerguntaResponse;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;
    @NotBlank
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String descricao;
    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantidade;
    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal valor;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @NotNull
    @Size(min = 3)
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Imagem> imagens = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Pergunta> perguntas = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull @Positive Integer quantidade,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull Categoria categoria,
                   @NotNull Usuario usuario,
                   @NotNull @Size(min = 3) @Valid List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
        this.usuario = usuario;
        Set<Caracteristica> novasCaracteristicas = caracteristicas.stream().map(c -> c.toModel(this)).collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
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

    public BigDecimal getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void vincularImagens(List<String> imagens) {
        List<Imagem> ListaImagens = imagens.stream().map(imagem -> new Imagem(imagem, this)).collect(Collectors.toList());
        this.imagens.addAll(ListaImagens);
    }

    public void vincularAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public void vincularPergunta(Pergunta pergunta) {
        this.perguntas.add(pergunta);
    }

    public List<CaracteristicaResponse> toCaracteristicaResponse() {
        return caracteristicas.stream().map(c -> new CaracteristicaResponse(c.getId(), c.getNome(), c.getDescricao())).collect(Collectors.toList());
    }

    public List<ImagemResponse> toImagemResponse() {
        return imagens.stream().map(i -> new ImagemResponse(i.getId(), i.getCaminho())).collect(Collectors.toList());
    }

    public List<AvaliacaoResponse> toAvaliacaoResponse() {
        return avaliacoes.stream().map(a -> new AvaliacaoResponse(a.getId(), a.getNota(), a.getTitulo(), a.getDescricao())).collect(Collectors.toList());
    }

    public List<PerguntaResponse> toPerguntaResponse() {
        return perguntas.stream().map(p -> new PerguntaResponse(p.getId(), p.getTitulo())).collect(Collectors.toList());
    }

    public Double mediaNotas() {
        double contagem = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            contagem = contagem + avaliacao.getNota();
        }
        return contagem / avaliacoes.size();
    }

    public Integer totalNotas() {
        return avaliacoes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
