package es.dsrroma.school.springbott.busca;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

@Component
public class BuscaRunner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    private ConfigurableApplicationContext context;

    public BuscaRunner(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    //se pregunta por consola el id de la reunion y la persona
    @Override
    public void run(String... args) throws Exception {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("Ingrese el ID de la reunion: ");
            long idReunion = Long.parseLong(s.next());
            System.out.println("Ingrese el ID de la persona: ");
            long idPersona = Long.parseLong(s.next());
            //se crea una instancia de infobusca y se envian los items recibidos
            InfoBusca info = new InfoBusca();
            info.setIdReunion(idReunion);
            info.setIdAsistente(idPersona);
            //
            rabbitTemplate.convertAndSend(
                    "servicios", "reuniones.busca.personas",
                    objectMapper.writeValueAsString(info));
            //se cierra el contexto
            context.close();
        }
    }

}
