package br.com.zup.mercadolivre.util.email;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Email {

    @Autowired
    private Mail mail;

    public void pergunta(@NotNull @Valid Pergunta pergunta) {
        mail.enviar("<html></html>",
                    "Assunto do email",
                    "email@email.com.br",
                    pergunta.getUsuario().getEmail(),
                    pergunta.getProduto().getUsuario().getEmail());
    }

    public void compra(@NotNull @Valid Compra compra) {
        mail.enviar("<html></html>",
                "Compra de novo produto",
                "email@email.com.br",
                compra.getUsuario().getEmail(),
                compra.getProduto().getUsuario().getEmail());
    }

    public void pagamentoSucesso(@NotNull @Valid Compra compra) {
        mail.enviar("<html></html>",
                "Pagamento realizado com sucesso",
                "email@email.com.br",
                compra.getUsuario().getEmail(),
                compra.getProduto().getUsuario().getEmail());
    }

    public void pagamentoErro(@NotNull @Valid Compra compra) {
        mail.enviar("<html></html>",
                "Pagamento não aprovado",
                "email@email.com.br",
                compra.getUsuario().getEmail(),
                compra.getProduto().getUsuario().getEmail());
    }
}
