package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.util.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/produtos/{id}/perguntas")
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Email email;

    @PostMapping
    @Transactional
    public ResponseEntity<List<Pergunta>> salvar(@PathVariable Long id,
                                                 @RequestBody @Valid PerguntaRequest request,
                                                 @AuthenticationPrincipal Usuario usuario,
                                                 UriComponentsBuilder builder) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do produto não existe"));
        Pergunta pergunta = request.toModel(usuario, produto);
        produto.vincularPergunta(pergunta);
        produtoRepository.save(produto);

        email.pergunta(pergunta);

        return ResponseEntity.ok(Arrays.asList(pergunta));
    }
}
