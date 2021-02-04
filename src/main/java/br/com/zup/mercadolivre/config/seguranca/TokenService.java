package br.com.zup.mercadolivre.config.seguranca;

import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${spring.jwt.expiration}")
    private String expiration;

    @Value("${spring.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Mercado Livre")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(dataAtual)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUsuarioId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject());
    }
}
