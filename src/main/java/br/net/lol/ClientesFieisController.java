package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clientes/fieis")
public class ClientesFieisController {
	private final ClientesFieisRepository clientesFieisRepository;
	private final UsuarioRepository usuarioRepository;

    @Autowired
    public ClientesFieisController(ClientesFieisRepository clientesFieisRepository, UsuarioRepository usuarioRepository) {
        this.clientesFieisRepository = clientesFieisRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping
    public List<ClientesFieis> getAllClientesFieis() {
    	List<ClientesFieis> clientesFieis = clientesFieisRepository.findAll();

        clientesFieis.sort(Comparator.comparing(ClientesFieis::getQuantidade_total).reversed());

        return clientesFieis.stream().limit(3).collect(Collectors.toList());
    }
}
