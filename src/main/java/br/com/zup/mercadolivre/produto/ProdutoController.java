package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.config.validacao.beanValidation.CaracteristicaIgualValidator;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CaracteristicaIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@RequestBody @Valid ProdutoRequest request,
                                       @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(categoriaRepository, usuario);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponse> filtrarPorId(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe com o id informado"));
        ProdutoResponse produtoResponse = new ProdutoResponse(produto);

        return ResponseEntity.ok(produtoResponse);
    }
}
