package es.dsrroma.school.springboot.reuniones.services;

import es.dsrroma.school.springboot.reuniones.data.PersonaRepository;
import es.dsrroma.school.springboot.reuniones.models.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {
    //creo la variable para almacenar la lista de objetos
    private final PersonaRepository personaRepository;

    //Se inyectan atravez del constructor
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAllPersonas() {
        //recupero todos los registros
        return personaRepository.findAll();
    }

    public Persona getById(long id) {
        return personaRepository.findById(id).get();
    }
}
