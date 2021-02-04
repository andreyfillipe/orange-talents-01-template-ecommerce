package br.com.zup.mercadolivre.util.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMail implements Mail{
    @Override
    public void enviar(String body, String subject, String nameFrom, String from, String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);
    }
}
