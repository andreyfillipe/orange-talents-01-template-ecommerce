package br.com.zup.mercadolivre.pergunta;

public class PerguntaResponse {

    private Long id;
    private String titulo;

    public PerguntaResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
