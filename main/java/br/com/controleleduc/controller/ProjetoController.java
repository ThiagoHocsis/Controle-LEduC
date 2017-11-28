package br.com.controleleduc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.controleleduc.model.Projeto;
import br.com.controleleduc.model.Usuario;
import br.com.controleleduc.repository.ProjetoRepository;

@RestController
@RequestMapping(value = "/api")
public class ProjetoController {
	
	@Autowired
	private ProjetoRepository projetoRepository;

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/projetos", method = RequestMethod.GET)
	public List<Projeto> projetos(){
		return projetoRepository.findAll();
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/projetos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Projeto> projetoById(@PathVariable Long id) {
		Projeto projeto = projetoRepository.findOne(id);
		if (projeto == null) {
			return new ResponseEntity<Projeto>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Projeto>(projeto, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/projetos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Projeto> deleteUser(@PathVariable Long id) {
		Projeto projeto = projetoRepository.findOne(id);
		if (projeto == null) {
			return new ResponseEntity<Projeto>(HttpStatus.NO_CONTENT);
		} else {
			projetoRepository.delete(projeto);
			return new ResponseEntity<Projeto>(projeto, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/projetos", method = RequestMethod.POST)
	public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto) {
		if (projetoRepository.findOneByNomeProjeto(projeto.getNomeProjeto()) != null) {
			throw new RuntimeException("Projeto já existe");
		}
		return new ResponseEntity<Projeto>(projetoRepository.save(projeto), HttpStatus.CREATED);
	}

	/* metodo que altera usuario*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/projetos", method = RequestMethod.PUT)
	public Projeto atualizarProjeto(@RequestBody Projeto projeto) {
		if (projetoRepository.findOneByNomeProjeto(projeto.getNomeProjeto()) != null
				&& projetoRepository.findOneByNomeProjeto(projeto.getNomeProjeto()).getId() != projeto.getId()) {
			throw new RuntimeException("Nome de usuário já existe !");
		}
		return projetoRepository.save(projeto);
	}
	
}
