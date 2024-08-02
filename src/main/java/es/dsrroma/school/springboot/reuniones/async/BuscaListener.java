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

import javax.swing.text.html.Option;
import java.util.Optional;

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
            Optional<Persona> persona = personaService.getById(info.getIdAsistente());
            //si persona no existe saldra un warning
            if (persona.isEmpty()) {
                LOG.warn("Mensaje recibido pero la persona {} no existe", info.getIdAsistente());
            }
            //utilizo el id reunion para obtener la reunion
            Optional<Reunion> reunion = reunionService.getById(info.getIdReunion());
            //si la reunion no existe se muestra un warning
            if (reunion.isEmpty()) {
                LOG.warn("Mensaje recibido pero la reunion {} no existe", info.getIdReunion());
            }
            //En caso de que la consulta sea exitosa
            if (persona.isPresent() && reunion.isPresent()) {
                LOG.info("{}{} Tiene una reunion a las {}: ", persona.get().getNombre(),
                        persona.get().getApellido(), reunion.get().getFecha());
            }

        } catch (JsonProcessingException e) {
            LOG.warn("Mensaje incorrecto", e);
        }

    }
}
