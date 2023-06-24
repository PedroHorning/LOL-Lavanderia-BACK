package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoGeralController {

    private final PedidoGeralRepository pedidoGeralRepository;

    @Autowired
    public PedidoGeralController(PedidoGeralRepository pedidoGeralRepository) {
        this.pedidoGeralRepository = pedidoGeralRepository;
    }
    
    @CrossOrigin(origins= "*")
    @GetMapping
    public List<PedidoGeral> listarPedidos() {
        return pedidoGeralRepository.findAll();
    }
    
    @CrossOrigin(origins= "*")
    @GetMapping("/{id}")
    public PedidoGeral obterPedidoPorId(@PathVariable Integer id) {
        return pedidoGeralRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
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
