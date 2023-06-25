package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private EmailService emailService;

    @CrossOrigin(origins= "*")
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @CrossOrigin(origins= "*")
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
    private String gerarSenhaAleatoria() {
        SecureRandom random = new SecureRandom();
        int senhaNumerica = random.nextInt(10000); 
        return String.format("%04d", senhaNumerica); 
    }

    private String gerarSenhaHash(String senha) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            byte[] senhaComSalt = concatBytes(senha.getBytes(), salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senhaComSalt);

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    private byte[] concatBytes(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    @CrossOrigin(origins= "*")
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
    	String senhaAleatoria = gerarSenhaAleatoria();
        String senhaHash = gerarSenhaHash(senhaAleatoria);
        usuario.setPassword(senhaHash);
        
        Usuario savedUsuario = usuarioRepository.save(usuario);

        emailService.enviarEmailSenha(usuario.getEmail(), senhaAleatoria);
        
        return savedUsuario;
    }

    @CrossOrigin(origins= "*")
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioData) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setCpf(usuarioData.getCpf());
            usuario.setName(usuarioData.getName());
            usuario.setEmail(usuarioData.getEmail());
            usuario.setCep(usuarioData.getCep());
            usuario.setTelefone(usuarioData.getTelefone());
            usuario.setPassword(usuarioData.getPassword());
            usuario.setNumber(usuarioData.getNumber());
            usuario.setStreet(usuarioData.getStreet());
            usuario.setNeighborhood(usuarioData.getNeighborhood());
            usuario.setCity(usuarioData.getCity());
            usuario.setState(usuarioData.getState());
            usuario.setProfile(usuarioData.getProfile());

            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @CrossOrigin(origins= "*")
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}
