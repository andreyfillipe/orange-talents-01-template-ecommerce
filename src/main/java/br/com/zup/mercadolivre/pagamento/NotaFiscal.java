package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoPagamentoSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.finalizadaComSucesso(), "Compra não foi processada com sucesso");
        RestTemplate restTemplate = new RestTemplate();
        Map<Object, Object> request = Map.of("compraId", compra.getTransacaoId(), "usuarioId", compra.getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
