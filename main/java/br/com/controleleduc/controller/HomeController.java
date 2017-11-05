package br.com.controleleduc.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleleduc.model.Usuario;
import br.com.controleleduc.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


 /*Todos os serviços disponiveis nesse controller estão permitidos a todos os usuarios */
@RestController
public class HomeController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	
	/* Metodo para cadastrar usuario, está disponivel a todos e recebe como parametro um usuario */
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		if (usuarioRepository.findOneByUsername(usuario.getUsername()) != null) {
			throw new RuntimeException("Usuário já existe");
		}
	
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		usuario.setRoles(roles);
		return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
	}

	/* metodo retorna um usuario logado*/
	@RequestMapping("/usuario")
	public Usuario usuario(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuarioLogadoUsername = auth.getName();
		return usuarioRepository.findOneByUsername(usuarioLogadoUsername);
	}

	/* Metodo responsavel por autenticar usuario, recebe um token caso o usuario seja cadastrado*/
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException {
		
		String token = null;
	
		Usuario usuario = usuarioRepository.findOneByUsername(username);
		
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		
		if (usuario != null && usuario.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username).claim("roles", usuario.getRoles()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("usuario", usuario);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}

	}
	
}
