package es.alimarket.microservicios.app.examenes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.alimarket.microservicios.app.examenes.services.ExamenService;
import es.alimarket.microservicios.commons.controllers.CommonController;
import es.alimarket.microservicios.commons.examenes.models.entity.Examen;
import es.alimarket.microservicios.commons.examenes.models.entity.Pregunta;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
		
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen> oExamen = service.findById(id);
		
		if (oExamen.isEmpty())
			return ResponseEntity.notFound().build();
		
		Examen examenDB = oExamen.get();
		examenDB.setNombre(examen.getNombre());
		
		// Eliminar las preguntas que no vienen
		List<Pregunta> eliminadas = examenDB.getPreguntas()
	    		.stream()
	    		.filter(pdb -> !examen.getPreguntas().contains(pdb))
	    		.collect(Collectors.toList());
	     
	    eliminadas.forEach(examenDB::removePregunta);
		
		// Añadir preguntas recibidas
		examenDB.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar (@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas () {
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
	
}
