/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontControlador {
    @GetMapping("/secretariaPrincipal")
    public String secretariaPrincipal(){
        return "secretariaPrincipal";
    }
    
    @GetMapping("/registroPaciente")
    public String registroPaciente(){
        return "registroPaciente";
    }

   
      @GetMapping("/login")
    public String login(){
        return "login";
    }

    

    @GetMapping("/generacionTurnosSec")
    public String generacionTurnosSec(){
        return "GeneracionTurnosSec";
    }
    
 @GetMapping("/principalMedico")
    public String principalMedico(){
        return "principal-Medico";
    }
    

    @GetMapping("/principalAdministrador")
    public String principalaAdministrador(){
        return "paginaPrincipal-Admin_axel";
    }
    
        @GetMapping("/listaDeUsuariosAdmin")
    public String listaDeUsuariosAdmin(){
        return "listaDeUsuarios-Admin_axel";
    }
    
         @GetMapping("/listaDePersonalAdmin")
    public String listaDePersonalAdmin(){
        return "listaDePersonal-Admin_axel";
    }

    @GetMapping("/listaDePacientesAdmin")
    public String listaDePacientesAdmin(){
        return "listaDePacientes-Admin_axel";
    }
    
     @GetMapping("/usuarioRegistroFormulario")
    public String usuarioRegistroFormulario(){
        return "usuarioRegistroFormulario_axel";
    }

    

    

}
