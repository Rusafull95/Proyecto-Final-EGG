/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author AXEL
 */
@Controller
@RequestMapping("/Secretaria")
public class SecretariaControlador {

    @GetMapping("/principal")
    public String secretariaPrincipal(ModelMap modelMap) {
        return "SecretariaPrincipal";
    }

    @GetMapping("/generadorTurno")
    public String generadorDeTurnos(ModelMap modelMap) {
        return "GeneracionTurnosSec";
    }

    @GetMapping("/registroUsuario")
    public String userRegistradoSec(ModelMap modelMap) {
        return "UserRegistradoSec";
    }
}
