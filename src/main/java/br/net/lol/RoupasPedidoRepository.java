package br.net.lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoupasPedidoRepository extends JpaRepository<RoupasPedidos, Integer>{

}
