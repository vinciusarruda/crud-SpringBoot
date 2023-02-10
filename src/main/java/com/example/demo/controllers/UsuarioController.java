package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public List<Usuario> findAll() {

		List<Usuario> resultado = repository.findAll();
		return resultado;
	}

	@GetMapping(value = "/{id}")
	public Usuario findById(@PathVariable("id") Long id) {
		Usuario resultado = repository.findById(id).get();
		return resultado;

	}

	@PostMapping
	public Usuario inserir(@RequestBody Usuario user) {
		Usuario resultado = repository.save(user);
		return resultado;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> updateTutorial(@PathVariable("id") long id, @RequestBody Usuario user) {
		Optional<Usuario> dadosUser = repository.findById(id);

		if (dadosUser.isPresent()) {
			Usuario _user = dadosUser.get();
			_user.setEmail(user.getEmail());
			_user.setNome(user.getNome());
			_user.setDepartamento(user.getDepartamento());
			return new ResponseEntity<>(repository.save(_user),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
}