package br.net.lol;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.profile = :profile")
    List<Usuario> findByProfile(@Param("profile") String profile);
}
