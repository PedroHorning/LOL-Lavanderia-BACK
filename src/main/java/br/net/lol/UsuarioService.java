package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuariosPorTipo(String tipo) {
        return usuarioRepository.findByTipo(tipo);
    }
    
    public Usuario adicionarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public Usuario buscarUsuarioPorEmailESenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public String buscarTipoUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario != null) {
            return usuario.getTipo();
        } else {
            throw new RuntimeException("Usuário não encontrado com o email: " + email);
        }
    }

    public Usuario editarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o CPF: " + usuario.getCpf()));

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setCep(usuario.getCep());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setTipo(usuario.getTipo());

        return usuarioRepository.save(usuarioExistente);
    }
    
    public void deletarUsuario(String cpf) {
        Usuario usuarioExistente = usuarioRepository.findById(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o CPF: " + cpf));

        usuarioRepository.delete(usuarioExistente);
    }


}
