package br.com.zup.mercadolivre.imagem;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ImagemRequest(@NotNull @Size(min = 1) List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
