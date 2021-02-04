package br.com.zup.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank
    @Size(min = 6)
    @Column(nullable = false)
    private String senha;
    @NotNull
    private LocalDateTime datacadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String email,
                   @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
