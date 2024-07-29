package es.dsrroma.school.springboot.reuniones.data;

import es.dsrroma.school.springboot.reuniones.models.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReunionRepository extends JpaRepository<Reunion, Long> {

}
