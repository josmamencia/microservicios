package es.alimarket.microservicios.app.cursos.services;

import org.springframework.stereotype.Service;

import es.alimarket.microservicios.app.cursos.models.entity.Curso;
import es.alimarket.microservicios.app.cursos.models.repository.CursoRepository;
import es.alimarket.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {


}
