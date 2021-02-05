package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.util.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoCompra {

    @Autowired
    private Set<EventoPagamentoSucesso> eventos;

    @Autowired
    private Email email;

    public void processa(Compra compra) {
        if(compra.finalizadaComSucesso()) {
            eventos.forEach(evento -> evento.processa(compra));

            email.pagamentoSucesso(compra);
        } else {
            email.pagamentoErro(compra);
        }
    }
}
