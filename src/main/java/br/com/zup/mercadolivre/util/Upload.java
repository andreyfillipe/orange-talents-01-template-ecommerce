package br.com.zup.mercadolivre.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Upload {

    List<String> enviar(List<MultipartFile> imagens);
}
