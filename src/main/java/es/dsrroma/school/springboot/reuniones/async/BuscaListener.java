package es.dsrroma.school.springboot.reuniones.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.dsrroma.school.springboot.reuniones.models.Persona;
import es.dsrroma.school.springboot.reuniones.models.Reunion;
import es.dsrroma.school.springboot.reuniones.services.PersonaService;
import es.dsrroma.school.springboot.reuniones.services.ReunionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuscaListener {
    private static final Logger LOG = LoggerFactory.getLogger(BuscaListener.class);
    private final ObjectMapper mapper;
    private final PersonaService personaService;
    private final ReunionService reunionService;

    public BuscaListener(ObjectMapper mapper, PersonaService personaService, ReunionService reunionService) {
        this.mapper = mapper;
        this.personaService = personaService;
        this.reunionService = reunionService;
    }

    //metodo para recibir mensajes
    public void recibirMensaje(String mensaje) {
        //Es necesario envolverlo dentro del try-cath
        try {
            //recupera el objeto infobusca, pidiendole al maper que lea el mensaje
            // y lo convierta utilizando la plantilla la clase infoBusca
            InfoBusca info = mapper.readValue(mensaje, InfoBusca.class);
            //utiliza el id asistente para obtener la persona
            Persona persona = personaService.getById(info.getIdAsistente());
            //utilizo el id reunion para obtener la reunion
            Reunion reunion = reunionService.getById(info.getIdReunion());
            //
            LOG.info("{}{} Tiene una reunion a las {}: ", persona.getNombre(),
                    persona.getApellido(), reunion.getFecha());
        } catch (JsonProcessingException e) {
            LOG.warn("Mensaje incorrecto", e);
        }

    }
}
