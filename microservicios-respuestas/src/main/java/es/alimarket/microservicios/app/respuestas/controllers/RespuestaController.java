package es.alimarket.microservicios.app.respuestas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.alimarket.microservicios.app.respuestas.models.entity.Respuesta;
import es.alimarket.microservicios.app.respuestas.services.RespuestaService;

@RestController
public class RespuestaController {
	@Autowired
	private RespuestaService respuestaService;
	
	@PostMapping
	public ResponseEntity<?> crear (@RequestBody Iterable<Respuesta> respuestas) {
		
		Iterable<Respuesta> respuestasBD = respuestaService.saveAll(respuestas);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasBD);
	}
	
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen (@PathVariable Long alumnoId, @PathVariable Long examenId) {
		Iterable<Respuesta> respuestas = respuestaService.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesRespondidosPorAlumno (@PathVariable Long alumnoId) {
		Iterable<Long> examenesIds = respuestaService.findExamenesIdsConRespuestasPorAlumno(alumnoId);
		return ResponseEntity.ok(examenesIds);
	}

}
