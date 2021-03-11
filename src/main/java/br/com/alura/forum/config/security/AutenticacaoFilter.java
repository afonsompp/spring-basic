package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.repository.UsuarioRepository;

public class AutenticacaoFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = recuperarToken(request);
        boolean valido = tokenService.isValido(token);
        if (valido) {
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);

    }

    private void autenticarCliente(String token) {
        var id = tokenService.getIdDoUsuario(token);
        var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null,
                    usuario.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token != null && !token.isBlank() && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }
        return null;
    }

}