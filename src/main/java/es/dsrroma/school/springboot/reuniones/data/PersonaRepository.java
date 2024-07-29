package es.dsrroma.school.springboot.reuniones.data;

import es.dsrroma.school.springboot.reuniones.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//JPA recibe dos argumentos entidad y calve primaria
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
