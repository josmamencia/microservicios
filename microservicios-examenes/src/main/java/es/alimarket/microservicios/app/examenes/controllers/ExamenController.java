package es.alimarket.microservicios.app.examenes.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.alimarket.microservicios.app.examenes.models.entity.Examen;
import es.alimarket.microservicios.app.examenes.services.ExamenService;
import es.alimarket.microservicios.commons.controllers.CommonController;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id) {
		Optional<Examen> oExamen = service.findById(id);
		
		if (oExamen.isEmpty())
			return ResponseEntity.notFound().build();
		
		Examen examenDB = oExamen.get();
		examenDB.setNombre(examen.getNombre());
		
		// Eliminar las preguntas que no vienen
		examenDB.getPreguntas().stream()
								.filter(pdb -> !examen.getPreguntas().contains(pdb))
								.forEach(examenDB::removePregunta);
		
		// Añadir preguntas recibidas
		examenDB.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
	}
	
}
