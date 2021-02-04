package br.com.zup.mercadolivre.util.email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

public interface Mail {

    /**
     *
     * @param body corpo do email
     * @param subject assunto do email
     * @param nameFrom nome para aparecer no provedor de email
     * @param from email de origem
     * @param to email de destino
     */
    void enviar(@NotBlank String body,
                @NotBlank String subject,
                @NotBlank String nameFrom,
                @NotBlank @Email String from,
                @NotBlank @Email String to);
}
