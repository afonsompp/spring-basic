package br.com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.controller.dto.LoginForm;
import br.com.alura.forum.controller.dto.TokenDto;

@RestController
@RequestMapping("/login")
@Profile("prod")
public class AutenticacaoController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> create(@RequestBody @Valid LoginForm user) {
        UsernamePasswordAuthenticationToken token = user.converter();

        try {
            Authentication auth = authManager.authenticate(token);
            var jwt = tokenService.gerarToken(auth);
            return ResponseEntity.ok(new TokenDto(jwt, "Bearer"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}