package br.com.alura.forum.config.security;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
    
    @Value("${forum.jwt.expiration}")
    String expiration;
    
    @Value("${forum.jwt.secret}")
    String secret;

    public String gerarToken(Authentication auth) {
        var usuario = (Usuario) auth.getPrincipal();
        var hoje = Instant.now();
        return Jwts.builder()
                .setIssuer("Api da akura")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(Date.from(hoje))
                .setExpiration(Date.from(hoje.plusMillis(Long.parseLong(expiration))))
                .signWith(SignatureAlgorithm.HS256 , secret).compact();
    }

    public boolean isValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdDoUsuario(String token) {
        var body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        var subject = body.getSubject();
        return Long.valueOf(subject);
    }
}
