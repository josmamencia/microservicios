package es.alimarket.microservicios.app.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import es.alimarket.microservicios.commons.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
