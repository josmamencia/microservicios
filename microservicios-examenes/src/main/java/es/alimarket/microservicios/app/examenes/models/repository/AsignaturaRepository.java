package es.alimarket.microservicios.app.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import es.alimarket.microservicios.commons.examenes.models.entity.Asignatura;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {

}
