package es.alimarket.microservicios.app.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.alimarket.microservicios.app.cursos.models.entity.Curso;
import es.alimarket.microservicios.app.cursos.services.CursoService;
import es.alimarket.microservicios.commons.alumnos.models.entity.Alumno;
import es.alimarket.microservicios.commons.controllers.CommonController;
import es.alimarket.microservicios.commons.examenes.models.entity.Examen;

@RestController
public class CursoController extends CommonController<Curso, CursoService>{

	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", service.findAll());
		
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
		
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Curso> oCurso = service.findById(id);
		
		if (oCurso.isEmpty())
			return ResponseEntity.notFound().build();
		
		Curso cursoDB = oCurso.get();
		cursoDB.setNombre(curso.getNombre());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
		Optional<Curso> oCurso = service.findById(id);
		
		if (oCurso.isEmpty())
			return ResponseEntity.notFound().build();
		
		Curso cursoDB = oCurso.get();
		alumnos.forEach(alumno -> {
			cursoDB.addAlumno(alumno);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Curso> oCurso = service.findById(id);
		
		if (oCurso.isEmpty())
			return ResponseEntity.notFound().build();
		
		Curso cursoDB = oCurso.get();
		cursoDB.removeAlumno(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
		Curso curso = service.findCursoByAlumnoId(id);
		if (curso == null)
			return ResponseEntity.notFound().build();
		
		List<Long> listaExamenesRespondidos = (List<Long>) service.obtenerExamenesRespondidosPorAlumno(id);
		List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
			if (listaExamenesRespondidos.contains(examen.getId())) {
				examen.setRespondido(true);
			}
			return examen;
		}).collect(Collectors.toList());
		
		curso.setExamenes(examenes);
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id) {
		Optional<Curso> oCurso = service.findById(id);
		
		if (oCurso.isEmpty())
			return ResponseEntity.notFound().build();
		
		Curso cursoDB = oCurso.get();
		examenes.forEach(examen -> {
			cursoDB.addExamen(examen);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id) {
		Optional<Curso> oCurso = service.findById(id);
		
		if (oCurso.isEmpty())
			return ResponseEntity.notFound().build();
		
		Curso cursoDB = oCurso.get();
		cursoDB.removeExamen(examen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
}
