package es.alimarket.microservicios.app.examenes.services;

import java.util.List;

import es.alimarket.microservicios.commons.examenes.models.entity.Asignatura;
import es.alimarket.microservicios.commons.examenes.models.entity.Examen;
import es.alimarket.microservicios.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
	public List<Examen> findByNombre(String term);
	
	public Iterable<Asignatura> findAllAsignaturas();
}
