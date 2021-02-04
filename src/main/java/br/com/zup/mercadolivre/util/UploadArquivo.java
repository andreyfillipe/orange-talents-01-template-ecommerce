package br.com.zup.mercadolivre.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class UploadArquivo implements Upload {

    @Override
    public List<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(i -> "http://imagens.com/" +
                        UUID.randomUUID().toString() + "-" +
                        i.getOriginalFilename())
                .collect(Collectors.toList());
    }
}
