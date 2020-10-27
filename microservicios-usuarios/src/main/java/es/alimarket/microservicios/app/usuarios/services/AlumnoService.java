package es.alimarket.microservicios.app.usuarios.services;


import java.util.List;

import es.alimarket.microservicios.commons.alumnos.models.entity.Alumno;
import es.alimarket.microservicios.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno>{
	public List<Alumno> findByNombreOrApellido(String term);
}
