package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.avaliacao.AvaliacaoResponse;
import br.com.zup.mercadolivre.caracteristica.CaracteristicaResponse;
import br.com.zup.mercadolivre.imagem.ImagemResponse;
import br.com.zup.mercadolivre.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Double mediaNotas;
    private Integer totalNotas;
    private List<CaracteristicaResponse> caracteristicas;
    private List<ImagemResponse> imagens;
    private List<AvaliacaoResponse> avaliacoes;
    private List<PerguntaResponse> perguntas;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.mediaNotas = produto.mediaNotas();
        this.totalNotas = produto.totalNotas();
        this.caracteristicas = produto.toCaracteristicaResponse();
        this.imagens = produto.toImagemResponse();
        this.avaliacoes = produto.toAvaliacaoResponse();
        this.perguntas = produto.toPerguntaResponse();
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

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }

    public List<AvaliacaoResponse> getAvaliacoes() {
        return avaliacoes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
