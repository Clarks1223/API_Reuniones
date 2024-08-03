package es.dsrroma.school.springboot.reuniones.data;

import es.dsrroma.school.springboot.reuniones.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "personas")
//JPA recibe dos argumentos entidad y calve primaria
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
