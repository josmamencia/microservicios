package es.alimarket.microservicios.app.usuarios.services;

import org.springframework.stereotype.Service;

import es.alimarket.microservicios.app.usuarios.models.repository.AlumnoRepository;
import es.alimarket.microservicios.commons.alumnos.models.entity.Alumno;
import es.alimarket.microservicios.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

}
