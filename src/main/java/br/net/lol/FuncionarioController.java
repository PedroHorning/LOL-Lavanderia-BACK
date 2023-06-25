package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FuncionarioController {
	private final FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    @Autowired
    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    
    @CrossOrigin(origins= "*")
    @GetMapping("/funcionario")
    public List<Funcionario> getFuncionario() {
        return funcionarioRepository.findAll();
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
    @PostMapping("/funcionario")
    public Funcionario addFuncionario(@RequestBody Funcionario funcionario) {
        String senhaAleatoria = gerarSenhaAleatoria();
        String senhaHash = gerarSenhaHash(senhaAleatoria);
        funcionario.setPassword(senhaHash);
        
        Usuario usuario = new Usuario();
        usuario.setName(funcionario.getName());
        usuario.setEmail(funcionario.getEmail());
        usuario.setProfile("funcionário");
        usuario.setPassword(senhaHash);
        
		usuarioRepository.save(usuario);

        return funcionarioRepository.save(funcionario);
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping("/funcionario/{id}")
    public Funcionario updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> existingFuncionario = funcionarioRepository.findById(id);
        if (existingFuncionario.isPresent()) {
            Funcionario updatedFuncionario = existingFuncionario.get();
            updatedFuncionario.setName(funcionario.getName());
            updatedFuncionario.setEmail(funcionario.getEmail());
            updatedFuncionario.setBirthDate(funcionario.getBirthDate());
            
            return funcionarioRepository.save(updatedFuncionario);
        } else {
            throw new RuntimeException("Funcionario not found with id: " + id);
        }
    }

    @CrossOrigin(origins= "*")
    @DeleteMapping("/funcionario/{id}")
    public String deleteFuncionario(@PathVariable Integer id) {
        Optional<Funcionario> existingFuncionario = funcionarioRepository.findById(id);
        if (existingFuncionario.isPresent()) {
        	funcionarioRepository.delete(existingFuncionario.get());
            return "Funcionario deleted successfully.";
            	
        } else {
            throw new RuntimeException("Funcionario not found with id: " + id);
        }
    }
}
