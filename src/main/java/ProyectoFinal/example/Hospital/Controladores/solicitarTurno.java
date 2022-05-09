
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Turnos;
import ProyectoFinal.example.Hospital.Servicios.SolicitarTurnoServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Belén
 */

@Controller
public class solicitarTurno {
    
    @Autowired
    private SolicitarTurnoServicio solicitarTurnoServicio;
            
      private String solicitarTurno(Model model){
       Turnos turno= new Turnos();
//       turno.setCita(new Date);
//       turno.sethora(new Date);
//       turno.setEspecialidad(new String);
        model.addAttribute("turno",turno);
        
        return "solicitarTurno";
    }   
    
   @PostMapping("/save")
   public String save(@RequestParam("solicitarTurno") string solicitarTurno) {
       System.out.println("El contenido es:" +solicitarTurno);
       Turno turno = new turno
       solicitarTurnoServicio.guardarTurno(turno)
   }
}
