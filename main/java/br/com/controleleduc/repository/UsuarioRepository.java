package br.com.controleleduc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleleduc.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Usuario findOneByUsername(String username);
}
