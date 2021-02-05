package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.util.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private Email email;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid CompraRequest request,
                                    @AuthenticationPrincipal Usuario usuario,
                                    UriComponentsBuilder builder) {
        Produto produto = produtoRepository.findById(request.getProdutoId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe com o id informado"));
        if (!produto.baixarEstoque(request.getQuantidade())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente");
        }
        Compra compra = request.toModel(produto, usuario);
        compraRepository.save(compra);

        email.compra(compra);

        String location;
        if (compra.getTipoPagamento() == CompraTipoPagamento.PAYPAL) {
            String urlRetornoAppPosPagamento = builder.path("/pagamentos/paypal/{transacaoId}").buildAndExpand(compra.getTransacaoId()).toString();
            location = "paypal.com/" + compra.getTransacaoId() + "?redirectUrl=" + urlRetornoAppPosPagamento;
        } else {
            String urlRetornoAppPosPagamento = builder.path("/pagamentos/pagseguro/{transacaoId}").buildAndExpand(compra.getTransacaoId()).toString();
            location = "pagseguro.com?returnId=" + compra.getTransacaoId() + "&redirectUrl=" + urlRetornoAppPosPagamento;
        }

        return ResponseEntity.status(302).header(location).body(location);
    }
}
