package es.alimarket.microservicios.app.respuestas.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.alimarket.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {
	
	@Query("select r from Respuesta as r join fetch r.alumno as a join fetch r.pregunta as p join fetch p.examen as e where a.id = ?1 and e.id = ?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen (Long alumnoId, Long examenId);
	
	@Query("select distinct e.id from Respuesta as r join r.alumno as a join r.pregunta as p join p.examen as e where a.id = ?1")
	public Iterable<Long> findExamenesIdsConRespuestasPorAlumno (Long alumnoId);

}
