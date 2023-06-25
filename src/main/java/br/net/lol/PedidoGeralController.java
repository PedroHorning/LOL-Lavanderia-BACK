package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoGeralController {

    private final PedidoGeralRepository pedidoGeralRepository;
    private final RoupasPedidoRepository roupasPedidoRepository;

    @Autowired
    public PedidoGeralController(PedidoGeralRepository pedidoGeralRepository, RoupasPedidoRepository roupasPedidoRepository) {
        this.pedidoGeralRepository = pedidoGeralRepository;
        this.roupasPedidoRepository = roupasPedidoRepository;
    }
    
    @CrossOrigin(origins= "*")
    @GetMapping
    public List<PedidoGeral> listarPedidos() {
        return pedidoGeralRepository.findAll();
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/cliente/{idUsuario}")
    public List<PedidoGeral> listarPedidosPorUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        return pedidoGeralRepository.findByIdUsuario(idUsuario);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Optional<PedidoGeral> listarPedidoPorId(@PathVariable("id") Integer id) {
        return pedidoGeralRepository.findById(id);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public PedidoGeral adicionarPedidoGeral(@RequestBody PedidoGeral pedidoGeral) { 	
        PedidoGeral novoPedidoGeral = pedidoGeralRepository.save(pedidoGeral);
        
        RoupasPedidos roupasPedidos = new RoupasPedidos();
        roupasPedidos.setRoupa_id(roupasPedidos.getRoupa_id());
        roupasPedidos.setQuantidade(roupasPedidos.getQuantidade());
        roupasPedidos.setValor_total(roupasPedidos.getValor_total());

        roupasPedidoRepository.save(roupasPedidos);

        return novoPedidoGeral;
    }

    @CrossOrigin(origins= "*")
    @PostMapping
    public PedidoGeral adicionarPedido(@RequestBody PedidoGeral pedido) {
        return pedidoGeralRepository.save(pedido);
    }

    @CrossOrigin(origins= "*")
    @PutMapping("/{id}")
    public PedidoGeral atualizarPedido(@PathVariable Integer id, @RequestBody PedidoGeral pedidoAtualizado) {
        PedidoGeral pedido = pedidoGeralRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));

        pedido.setStatus(pedidoAtualizado.getStatus());

        return pedidoGeralRepository.save(pedido);
    }

    @CrossOrigin(origins= "*")
    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Integer id) {
        PedidoGeral pedido = pedidoGeralRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));

        pedidoGeralRepository.delete(pedido);
    }
}
