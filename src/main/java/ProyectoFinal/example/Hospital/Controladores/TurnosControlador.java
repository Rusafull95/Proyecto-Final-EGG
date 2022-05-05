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
import ProyectoFinal.example.Hospital.Entidades.Turnos;
import ProyectoFinal.example.Hospital.Servicios.TurnosServicios;
import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  IMPORTANTE: generación turnos esta sujeta a cambios
 * 
 */
@Controller
@RequestMapping("/turnos")
public class TurnosControlador {
    
    @Autowired
    private TurnosServicios turnosServicios;
    
    @GetMapping("/generacionsec")
    public String generacionTurnosSec(
            @RequestParam("cita") Date cita, 
            @RequestParam("paciente") Paciente paciente, 
            @RequestParam("medico") Medico medico, 
            @RequestParam("estado") EstadoDelTurno estado, 
            @RequestParam("consulta") Consulta consulta, 
            @RequestParam("especialidad") Especialidad especialidad, 
            @RequestParam("secretaria") Secretaria secretaria
    ) throws Exception{
        turnosServicios.crearTurnos(cita, paciente, medico, estado, consulta, especialidad, secretaria);
        return "GeneracionTurnosSec";
    }
}
