package br.net.lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByTipo(String tipo);
    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findByEmail(String email);
}
