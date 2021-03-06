package br.com.alura.forum.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService  implements UserDetailsService{
    
    private final UsuarioRepository usuarioRepository;
   
    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(username);
        
        if (!usuario.isPresent()) throw new UsernameNotFoundException("Dados inválidos");

        return usuario.get();
    }

}
