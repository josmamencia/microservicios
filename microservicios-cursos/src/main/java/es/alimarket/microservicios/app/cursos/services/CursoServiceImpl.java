package es.alimarket.microservicios.app.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.alimarket.microservicios.app.cursos.clients.RespuestaFeignClient;
import es.alimarket.microservicios.app.cursos.models.entity.Curso;
import es.alimarket.microservicios.app.cursos.models.repository.CursoRepository;
import es.alimarket.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeignClient respuestaFeignClient;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesRespondidosPorAlumno(Long alumnoId) {
		
		return respuestaFeignClient.obtenerExamenesRespondidosPorAlumno(alumnoId);
	}


}
