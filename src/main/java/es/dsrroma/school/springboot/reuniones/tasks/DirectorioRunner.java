package es.dsrroma.school.springboot.reuniones.tasks;

import es.dsrroma.school.springboot.reuniones.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.*;

@Component//se registra la clase como componente
@Order(2)
public class DirectorioRunner implements CommandLineRunner {
    // Logger para registrar información en los logs
    private static final Logger LOG = LoggerFactory.getLogger(
            DirectorioRunner.class);
    // Inyección de dependencias: el servicio PersonaService será inyectado automáticamente por Spring
    @Autowired
    private PersonaService personaService;

    // Método requerido por la interfaz CommandLineRunner. Se ejecuta cuando la aplicación arranca
    @Override
    public void run(String... args) throws Exception {
        // Registro en el log del mensaje "Directorio de personas: "
        LOG.info("Directorio de personas: ");
        // Obtiene todas las personas del servicio personaService y las registra una por una
        personaService.getAllPersonas().forEach(persona -> {
            // Registro en el log de la representación en cadena de cada objeto persona
            LOG.info(persona.toString());
        });
        // Registro en el log del mensaje "Fin del directorio"
        LOG.info("Fin del directorio");
    }
}
