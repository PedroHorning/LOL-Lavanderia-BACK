package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos-roupas")
@CrossOrigin(origins = "*")
public class RoupasPedidosController {

    private final RoupasPedidoRepository roupasPedidoRepository;
    private final RoupaRepository roupasRepository;

    @Autowired
    public RoupasPedidosController(RoupasPedidoRepository roupasPedidoRepository, RoupaRepository roupasRepository) {
        this.roupasPedidoRepository = roupasPedidoRepository;
        this.roupasRepository = roupasRepository;
    }

    @GetMapping
    public List<RoupasPedidos> getAllPedidosRoupas() {
        return roupasPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public RoupasPedidos getPedidoRoupasById(@PathVariable("id") Integer id) {
        return roupasPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
    }

    @PostMapping
    public RoupasPedidos createPedidoRoupas(@RequestBody RoupasPedidos pedidoRoupas) {
        Integer roupaId = pedidoRoupas.getRoupa_id();
        Optional<Roupa> roupaOptional = roupasRepository.findById(roupaId);
        if (roupaOptional.isEmpty()) {
            throw new RuntimeException("Roupa não encontrada com o ID: " + roupaId);
        }
        Roupa roupa = roupaOptional.get();
        Double valorUnitario = roupa.getPrice();
        Integer quantidade = pedidoRoupas.getQuantidade();
        Double valorTotal = valorUnitario * quantidade;
        Integer deadline = roupa.getDeadline();
        Integer deadlineTotal = deadline * quantidade;

        pedidoRoupas.setValor_total(valorTotal);
        pedidoRoupas.setDeadline_total(deadlineTotal);

        return roupasPedidoRepository.save(pedidoRoupas);
    }

    @PutMapping("/{id}")
    public RoupasPedidos updatePedidoRoupas(@PathVariable("id") Integer id, @RequestBody RoupasPedidos pedidoRoupas) {
        if (!roupasPedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com o ID: " + id);
        }

        Integer roupaId = pedidoRoupas.getRoupa_id();
        Optional<Roupa> roupaOptional = roupasRepository.findById(roupaId);
        if (roupaOptional.isEmpty()) {
            throw new RuntimeException("Roupa não encontrada com o ID: " + roupaId);
        }
        Roupa roupa = roupaOptional.get();
        Double valorUnitario = roupa.getPrice();
        Integer quantidade = pedidoRoupas.getQuantidade();
        Double valorTotal = valorUnitario * quantidade;
        Integer deadline = roupa.getDeadline();
        Integer deadlineTotal = deadline * quantidade;

        pedidoRoupas.setRoupas_pedido_id(id);
        pedidoRoupas.setValor_total(valorTotal);
        pedidoRoupas.setDeadline_total(deadlineTotal);

        return roupasPedidoRepository.save(pedidoRoupas);
    }

    @DeleteMapping("/{id}")
    public void deletePedidoRoupas(@PathVariable("id") Integer id) {
        if (!roupasPedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com o ID: " + id);
        }
        roupasPedidoRepository.deleteById(id);
    }
}
