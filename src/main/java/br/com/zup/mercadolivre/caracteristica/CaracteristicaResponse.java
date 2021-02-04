package br.com.zup.mercadolivre.caracteristica;

public class CaracteristicaResponse {

    private Long id;
    private String nome;
    private String descricao;

    public CaracteristicaResponse(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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
}
