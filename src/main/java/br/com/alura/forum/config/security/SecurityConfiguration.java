package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.forum.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    private final UsuarioRepository repository;

    public SecurityConfiguration(UsuarioRepository repository) {
        this.repository = repository;
    }

    // Configuração de autenticação de clientes
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        AutenticacaoService autenticacaoService = new AutenticacaoService(repository);
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());    
    }
    // configuração de autorização de acesso as rotas
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topico").permitAll()
                .antMatchers(HttpMethod.GET, "/topico/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    // configuração de utilização de recursos estáticos(html, css, js, imagens)
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
