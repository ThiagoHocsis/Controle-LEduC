package br.com.controleleduc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleleduc.model.Projeto;


public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	public Projeto findOneByNomeProjeto(String nomeProjeto);
}
