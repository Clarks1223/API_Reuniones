package es.dsrroma.school.springboot.reuniones.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "personalizado")
public class EndpointPers {
    @ReadOperation
    public String personalizado() {
        return "personalizado";
    }
}
