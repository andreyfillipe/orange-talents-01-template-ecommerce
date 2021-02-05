package br.com.zup.mercadolivre.sistemaexterno;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotBlank
    private String compraId;
    @NotNull
    private Long usuarioId;

    public NotaFiscalRequest(@NotBlank String compraId, @NotNull Long usuarioId) {
        this.compraId = compraId;
        this.usuarioId = usuarioId;
    }

    public String getCompraId() {
        return compraId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
