package br.com.zup.mercadolivre.avaliacao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/{id}/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@PathVariable Long id,
                                       @RequestBody @Valid AvaliacaoRequest request,
                                       @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        Avaliacao avaliacao = request.toModel(usuario, produto);
        produto.vincularAvaliacao(avaliacao);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}
