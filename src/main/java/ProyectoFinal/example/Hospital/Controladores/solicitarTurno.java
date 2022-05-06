
package ProyectoFinal.example.Hospital.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Belén
 */

@Controller
public class solicitarTurno {
    
     @GetMapping("/solicitarTurno")
    public String solicitarTurno(){
        @ModelAttribute(@)
        @RequestParam("cita") Date cita;
        @RequestParam("hora") Time hora;
        @RequestParam("especialidad") String especialidad;
        
        
        
        return "solicitarTurno";
    }   
    
    
}
