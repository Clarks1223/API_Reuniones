package es.dsrroma.school.springboot.reuniones.tasks;

import es.dsrroma.school.springboot.reuniones.services.ReunionService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// Anotación que marca esta clase como un componente gestionado por el contenedor de Spring
@Component
@Order(1)// Anotación que define el orden en el que se deben ejecutar los componentes implementando ApplicationRunner o CommandLineRunner
public class AgendaRunner implements ApplicationRunner {
    // Logger para registrar información en los logs. Utiliza LoggerFactory para crear una instancia de Logger
    public static final Logger LOG = LoggerFactory.getLogger(AgendaRunner.class);

    // Inyección de dependencias: el servicio ReunionService será inyectado automáticamente por Spring
    @Autowired
    private ReunionService reunionService;

    // Método requerido por la interfaz ApplicationRunner. Se ejecuta cuando la aplicación arranca
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Registro en el log del mensaje "Lista de reuniones:"
        LOG.info("Lista de reuniones:");
        // Obtiene todas las reuniones del servicio reunionService y las registra una por una
        reunionService.getAllReuniones().forEach(reunion -> {
            // Registro en el log de la representación en cadena de cada objeto reunion
            LOG.info(reunion.toString());
        });
        // Registro en el log del mensaje "Fin de reuniones"
        LOG.info("Fin de reuniones");
    }
}
