package br.com.zup.mercadolivre.usuario;

import br.com.zup.mercadolivre.config.validacao.beanValidation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @UniqueValue(nomeClasse = Usuario.class, nomeCampo = "email", message = "Já existe usuário com este login cadastrado")
    private String email;
    @NotBlank(message = "A senha é obrigatório")
    @Size(min = 6, message = "senha deve conter no mínimo 6 caracteres")
    private String senha;

    public UsuarioRequest(@NotBlank
                          @Email String email,
                          @NotBlank @Size String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.email, this.senha);
    }
}
