package br.com.zup.mercadolivre.sistemaexterno;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemaExternoController {

    @PostMapping("/notas-fiscais")
    @Transactional
    public ResponseEntity<Void> emitirNotaFiscal(@RequestBody @Valid NotaFiscalRequest request) {
        System.out.println("Emissão de notas fiscais");
        System.out.println("Id da Transação: " + request.getCompraId());
        System.out.println("Id do Usuário: " + request.getUsuarioId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ranking-vendedores")
    @Transactional
    public ResponseEntity<Void> emitirRankingVendedor(@RequestBody @Valid RankingVendedorRequest request) {
        System.out.println("Ranking de vendedores");
        System.out.println("Id da Transação: " + request.getCompraId());
        System.out.println("Id do Usuário: " + request.getUsuarioId());
        return ResponseEntity.ok().build();
    }
}
