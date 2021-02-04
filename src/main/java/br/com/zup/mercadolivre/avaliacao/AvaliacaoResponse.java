package br.com.zup.mercadolivre.avaliacao;

public class AvaliacaoResponse {

    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;

    public AvaliacaoResponse(Long id, Integer nota, String titulo, String descricao) {
        this.id = id;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
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
}
