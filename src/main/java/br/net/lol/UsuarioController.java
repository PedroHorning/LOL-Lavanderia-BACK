package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario/fun")
    public List<Usuario> listarUsuariosFunc() {
        return usuarioService.listarUsuariosPorTipo("fun");
    }
    
    @PostMapping("/usuarios")
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.adicionarUsuario(usuario);
    }
    
    @GetMapping("/usuarios/login")
    public ResponseEntity<String> loginUsuario(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmailESenha(email, senha);
        
        if (usuario != null) {
            return ResponseEntity.ok("Login bem-sucedido.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }
    
    @GetMapping("/usuarios/tipo")
    public String buscarTipoUsuario(@RequestParam String email) {
        return usuarioService.buscarTipoUsuarioPorEmail(email);
    }
    
    @PutMapping("/usuarios/{cpf}")
    public ResponseEntity<String> editarUsuario(@PathVariable String cpf, @RequestBody Usuario usuario) {
        usuario.setCpf(cpf);
        Usuario usuarioEditado = usuarioService.editarUsuario(usuario);
        
        if (usuarioEditado != null) {
            return ResponseEntity.ok("Usuário editado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o CPF: " + cpf);
        }
    }
    
    @DeleteMapping("/usuarios/{cpf}")
    public ResponseEntity<String> deletarUsuario(@PathVariable String cpf) {
        usuarioService.deletarUsuario(cpf);
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }


}
