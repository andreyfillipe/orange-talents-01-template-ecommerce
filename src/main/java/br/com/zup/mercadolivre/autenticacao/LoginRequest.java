package br.com.zup.mercadolivre.autenticacao;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public LoginRequest(@NotBlank String email, @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha) ;
    }
}
