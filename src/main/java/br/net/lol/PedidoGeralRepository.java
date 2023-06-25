package br.net.lol;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoGeralRepository extends JpaRepository <PedidoGeral, Integer> {
	List<PedidoGeral> findByIdUsuario(Integer idUsuario);
	Optional<PedidoGeral> findById(Integer id);
}
