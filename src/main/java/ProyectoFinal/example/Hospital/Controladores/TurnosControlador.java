/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Consulta;
import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Secretaria;
import ProyectoFinal.example.Hospital.Servicios.TurnosServicios;
import ProyectoFinal.example.Hospital.enums.Rol;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  
 * 
 */

@Controller
@RequestMapping("/turnos")
public class TurnosControlador {
    
    @Autowired
    private TurnosServicios turnosServicios;
    
    @GetMapping("")
    public String paginaPrincipal(@RequestParam("rol") Rol rol){
        if(rol == Rol.SECRETARIA){    
            return "GeneracionTurnosSec";
        }else{
            return  "solicitarTurno";
        }
    }
    
    @GetMapping("/generacionsec")
    public String generacionTurnosSec(
            @RequestParam("cita") Date cita, 
            @RequestParam("paciente") Paciente paciente, 
            @RequestParam("medico") Medico medico,  
            @RequestParam("consulta") Consulta consulta, 
            @RequestParam("especialidad") Especialidad especialidad, 
            @RequestParam("secretaria") Secretaria secretaria
    ) throws Exception{
        turnosServicios.crearTurnos(cita, paciente, medico, consulta, especialidad, secretaria);
        return "GeneracionTurnosSec";
    }
    
    @GetMapping("/solicitar")
    public String solicitarTurno(
            @RequestParam("cita") Date cita, 
            @RequestParam("paciente") Paciente paciente, 
            @RequestParam("medico") Medico medico,  
            @RequestParam("consulta") Consulta consulta, 
            @RequestParam("especialidad") Especialidad especialidad 
    ) throws Exception{
        turnosServicios.crearTurnos(cita, paciente, medico, consulta, especialidad, null);
        return "solicitarTurno";
    }
}
