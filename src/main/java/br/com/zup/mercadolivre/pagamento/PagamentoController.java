package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class PagamentoController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EventoCompra eventoCompra;

    @PostMapping("/pagamentos/pagseguro/{id}")
    @Transactional
    public ResponseEntity<Void> salvarPagSeguro(@PathVariable String id,
                                                  @RequestBody @Valid PagamentoPagSeguroRequest request,
                                                  UriComponentsBuilder builder) {
        pagar(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pagamentos/paypal/{id}")
    @Transactional
    public ResponseEntity<Void> salvarPayPal(@PathVariable String id,
                                                  @RequestBody @Valid PagamentoPayPalRequest request,
                                                  UriComponentsBuilder builder) {
        pagar(id, request);
        return ResponseEntity.ok().build();
    }

    private void pagar(String id, PagamentoRequest request) {
        Compra compra = compraRepository.findByTransacaoId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe Compra com o id informado"));
        compra.finalizarPagamento(request);
        compraRepository.save(compra);

        eventoCompra.processa(compra);
    }
}
