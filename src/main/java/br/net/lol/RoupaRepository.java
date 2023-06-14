package br.net.lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoupaRepository extends JpaRepository<Roupa, Integer> {
    
}
