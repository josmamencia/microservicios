package es.alimarket.microservicios.app.cursos.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import es.alimarket.microservicios.commons.alumnos.models.entity.Alumno;
import es.alimarket.microservicios.commons.examenes.models.entity.Examen;

@Entity
@Table(name="cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date crateAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	List<Alumno> alumnos;
	
	@ManyToMany (fetch = FetchType.LAZY)
	private List<Examen> examenes;
	
	public Curso() {
		super();
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
	}

	@PrePersist
	public void prePersist () {
		this.crateAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCrateAt() {
		return crateAt;
	}

	public void setCrateAt(Date crateAt) {
		this.crateAt = crateAt;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void addAlumno(Alumno alumno) {
		if (this.alumnos == null) this.alumnos = new ArrayList<Alumno>();
		this.alumnos.add(alumno);
	}
	
	public void removeAlumno(Alumno alumno) {
		if (this.alumnos == null)
			return;
		
		this.alumnos.remove(alumno);
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	
	public void addExamen (Examen examen) {
		this.examenes.add(examen);
	}
	
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}
	
	
	
}
