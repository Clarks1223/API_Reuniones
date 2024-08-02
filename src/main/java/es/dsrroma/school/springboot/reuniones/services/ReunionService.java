package es.dsrroma.school.springboot.reuniones.services;

import es.dsrroma.school.springboot.reuniones.data.ReunionRepository;
import es.dsrroma.school.springboot.reuniones.models.Persona;
import es.dsrroma.school.springboot.reuniones.models.Reunion;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReunionService {
    private final ReunionRepository reunionRepository;

    //Inyecta a traves del constructor
    public ReunionService(ReunionRepository reunionRepository) {
        this.reunionRepository = reunionRepository;
    }

    public List<Reunion> getAllReuniones() {
        return reunionRepository.findAll();
    }

    public Reunion getById(long id) {
        return reunionRepository.findById(id).get();
    }
}
