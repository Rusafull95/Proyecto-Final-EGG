/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Secretaria;
import ProyectoFinal.example.Hospital.Servicios.ConsultaServicio;
import ProyectoFinal.example.Hospital.Servicios.TurnosServicios;
import ProyectoFinal.example.Hospital.enums.Rol;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ConsultaServicio consultaServicio;
    @Autowired
    private TurnosServicios turnosServicios;
    
    @GetMapping("")
    public String paginaPrincipal(@RequestParam("rol") Rol rol){
        if(rol == Rol.SECRETARIA){    
            return "GeneracionTurnosSec";
        }else{
            return "solicitarTurno";
        }
    }
    
    @PostMapping("/generacionsec")
    public String generacionTurnosSec(
            @RequestParam("cita")@DateTimeFormat(pattern = "yyyy-mm-dd") Date cita, 
            @RequestParam("paciente") Paciente paciente, 
            @RequestParam("medico") Medico medico,  
            @RequestParam("especialidad") Especialidad especialidad, 
            @RequestParam("secretaria") Secretaria secretaria
    ) throws Exception{
        turnosServicios.crearTurnos(cita, paciente, medico, especialidad, secretaria);
        return "GeneracionTurnosSec";
    }
    
    @PostMapping("/solicitar")
    public String solicitarTurno(
            @RequestParam("cita")@DateTimeFormat(pattern = "yyyy-mm-dd") Date cita, 
            @RequestParam("paciente") Paciente paciente, 
            @RequestParam("medico") Medico medico,  
            @RequestParam("especialidad") Especialidad especialidad 
    ) throws Exception{
        turnosServicios.crearTurnos(cita, paciente, medico, especialidad, null);
        return "solicitarTurno";
    }
    
    @PatchMapping("/consulta")
    public String crearConsulta(@RequestParam("num") Integer num, @RequestParam("descripcion") String descripcion, @RequestParam("turnoId") Integer turnoId) throws Exception{
        turnosServicios.modificarTurno(turnoId, null, null, null, null, consultaServicio.crearConsulta(descripcion, num), null, null);
        return "index";
    }
    
    @GetMapping("/buscar")
    public String buscarTurno(ModelMap modelo, @RequestParam("codigo")Integer codigo) throws Exception{
        modelo.put("turno", turnosServicios.buscarTurnoPorCodigo(codigo));
        return "index";
    }
}
