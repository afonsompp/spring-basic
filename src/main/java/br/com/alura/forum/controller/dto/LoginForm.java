package br.com.alura.forum.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public LoginForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}
