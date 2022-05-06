
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Turnos;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String solicitarTurno(Model model){
       Turnos turno= new Turnos();
//       turno.setCita(new Date);
//       turno.sethora(new Date);
//       turno.setEspecialidad(new String);
        model.addAttribute("turno",turno);
        
        return "solicitarTurno";
    }   
    
   
}