package es.alimarket.microservicios.app.cursos.services;

import es.alimarket.microservicios.app.cursos.models.entity.Curso;
import es.alimarket.microservicios.commons.services.CommonService;

public interface CursoService extends CommonService<Curso> {

	public Curso findCursoByAlumnoId (Long id);
	
	public Iterable<Long> obtenerExamenesRespondidosPorAlumno (Long alumnoId);
}
