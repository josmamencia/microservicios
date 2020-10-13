package es.alimarket.microservicios.commons.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import es.alimarket.microservicios.commons.services.CommonService;

public class CommonController<E, S extends CommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verDetalle(@PathVariable Long id) {
		Optional<E> entity = service.findById(id);
		
		if (entity.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().body(entity.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody E entity) {
		E entityDB = service.save(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
	}
	
	@DeleteMapping("/{id]")
	public ResponseEntity<?> eliminar( @PathVariable Long id) {
		Optional<E> oAlumno = service.findById(id);
		
		if (oAlumno.isEmpty())
			return ResponseEntity.notFound().build();
		
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
