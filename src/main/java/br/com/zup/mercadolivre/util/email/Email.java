package br.com.zup.mercadolivre.util.email;

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
}
