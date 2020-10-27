package es.alimarket.microservicios.app.examenes.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import es.alimarket.microservicios.app.examenes.models.entity.Examen;
import es.alimarket.microservicios.commons.services.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, CrudRepository<Examen,Long>> implements ExamenService {

}
