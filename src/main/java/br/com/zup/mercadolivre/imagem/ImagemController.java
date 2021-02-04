package br.com.zup.mercadolivre.imagem;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos/{id}/imagens")
public class ImagemController {

    @Autowired
    private Upload uploadArquivo;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@PathVariable Long id,
                                       @Valid ImagemRequest request,
                                       @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe com o id informado"));
        if (!produto.getUsuario().equals(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<String> imagens = uploadArquivo.enviar(request.getImagens());
        produto.vincularImagens(imagens);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}
