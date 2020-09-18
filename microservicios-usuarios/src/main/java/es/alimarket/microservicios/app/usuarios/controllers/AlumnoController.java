package es.alimarket.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.alimarket.microservicios.app.usuarios.models.entity.Alumno;
import es.alimarket.microservicios.app.usuarios.services.AlumnoService;

@RestController
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(alumnoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verDetalle(@PathVariable Long id) {
		Optional<Alumno> alumno = alumnoService.findById(id);
		
		if (alumno.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().body(alumno.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
		Alumno alumnoDB = alumnoService.save(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
	}
	
	@PutMapping("/{id]")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> oAlumno = alumnoService.findById(id);
		
		if (oAlumno.isEmpty())
			return ResponseEntity.notFound().build();
		
		Alumno alumnoDB = oAlumno.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoDB));
	}
	
	@DeleteMapping("/{id]")
	public ResponseEntity<?> eliminar( @PathVariable Long id) {
		Optional<Alumno> oAlumno = alumnoService.findById(id);
		
		if (oAlumno.isEmpty())
			return ResponseEntity.notFound().build();
		
		alumnoService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
