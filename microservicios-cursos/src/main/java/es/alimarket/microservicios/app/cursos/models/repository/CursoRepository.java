package es.alimarket.microservicios.app.cursos.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.alimarket.microservicios.app.cursos.models.entity.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {

	@Query("select c from Curso as c join fetch c.alumnos as a where a.id = ?1")
	public Curso findCursoByAlumnoId (Long id);
}
