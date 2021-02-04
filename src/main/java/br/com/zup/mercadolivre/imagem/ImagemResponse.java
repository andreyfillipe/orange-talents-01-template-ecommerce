package br.com.zup.mercadolivre.imagem;

public class ImagemResponse {

    private Long id;
    private String caminho;

    public ImagemResponse(Long id, String caminho) {
        this.id = id;
        this.caminho = caminho;
    }

    public Long getId() {
        return id;
    }

    public String getCaminho() {
        return caminho;
    }
}
