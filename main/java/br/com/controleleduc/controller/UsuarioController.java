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

import br.com.controleleduc.model.Usuario;
import br.com.controleleduc.repository.UsuarioRepository;



@RestController
@RequestMapping(value = "/api")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public List<Usuario> usuarios() {
		return usuarioRepository.findAll();
	}

	/* metodo retorna os usuarios por id*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> userById(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findOne(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
	}

	/* metodo que deleta usuario verificando o id*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deleteUser(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuarioLogado = auth.getName();
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		} else if (usuario.getUsername().equalsIgnoreCase(usuarioLogado)) {
			throw new RuntimeException("You cannot delete your account");
		} else {
			usuarioRepository.delete(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}

	}

	/* metodo que adiciona usuario */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
		if (usuarioRepository.findOneByUsername(usuario.getUsername()) != null) {
			throw new RuntimeException("Usuário já existe");
		}
		return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
	}

	/* metodo que altera usuario*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/usuarios", method = RequestMethod.PUT)
	public Usuario updateUser(@RequestBody Usuario usuario) {
		if (usuarioRepository.findOneByUsername(usuario.getUsername()) != null
				&& usuarioRepository.findOneByUsername(usuario.getUsername()).getId() != usuario.getId()) {
			throw new RuntimeException("Nome de usuário já existe !");
		}
		return usuarioRepository.save(usuario);
	}

}
