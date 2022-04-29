/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Servicios.MedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IMPORTANTE: no se mandan todos los datos a todas las url asi que avisar si se necesitan más datos un algún html o si se quiere modificar algo(todo se puede cambiar).
 * falta acomodar algunas rutas con los html
 * 
 *  Modelos:
 * 1)_"turnosHoy"_ (URL: "/medico/turnos/hoy")turnos de hoy del médico(recibe un parametro llamado "matricula")
 * 2)_"listaDeTurnos"_ (URL: "/medico/turnos") todos los turnos de un médico sin importar el estado (recibe un parametro llamado "matricula")
 * 3)_"listaDeTurnosAtendidos"_ (URL: "/medico/turnos/atendidos") todos los turnos atendidos de un médico (recibe un parametro llamado "matricula")
 * 4)_"listaDeTurnosEnProgreso"_ (URL: "/medico/turnos/enprogreso") todos los turnos en progreso de un médico (recibe un parametro llamado "matricula")
 */
@Controller
@RequestMapping("/medico")
public class MedicoControlador {
    
    @Autowired
    private MedicoServicio medicoServicio;
    
    @GetMapping("/principal")
    public String paginaPrincipal(){
        return "principal-Medico";
    }
    
    @GetMapping("/turnos/hoy")
    public String turnosHoy(ModelMap modelo, @RequestParam("matricula") String numMatricula) throws Exception{
        modelo.put("turnosHoy", medicoServicio.turnosHoy(numMatricula));
        return "principal-Medico";
    }
    
    @GetMapping("/turnos")
    public String turnos(ModelMap modelo, @RequestParam("matricula") String numMatricula) throws Exception{
        modelo.put("listaDeTurnos", medicoServicio.medicoTurnos(numMatricula));
        return "listaDeTurnosCompleta-Medico";
    }

    @GetMapping("/turnos/atendidos")
    public String turnosAtendidos(ModelMap modelo, @RequestParam("matricula") String numMatricula) throws Exception{
        modelo.put("listaDeTurnosAtendidos", medicoServicio.turnosAtendidos(numMatricula));
        return "turnoAtendido-Medico";
    }
    
    @GetMapping("/turnos/enproceso")
    public String turnosEnProgreso(ModelMap modelo, @RequestParam("matricula") String numMatricula) throws Exception{
        modelo.put("listaDeTurnosEnProgreso", medicoServicio.turnosEnProceso(numMatricula));
        return "listaTurnoEnProgreso-Medico";
    }
}