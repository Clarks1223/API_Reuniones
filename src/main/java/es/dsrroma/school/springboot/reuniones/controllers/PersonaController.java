package es.dsrroma.school.springboot.reuniones.controllers;

import es.dsrroma.school.springboot.reuniones.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @GetMapping//defino lo que regresara el metodo get en esta ruta
    public String getAllPersonas(Model model) {
        //agrego el atributo personas al modelo personas para poder acceder a el desde la vista
        model.addAttribute("personas", personaService.getAllPersonas());
        return "personas";
    }
}
