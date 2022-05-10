/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Turno;
import ProyectoFinal.example.Hospital.Servicios.EspecialidadServicios;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontControlador {

    @GetMapping("/secretariaPrincipal")
    public String secretariaPrincipal() {
        return "secretariaPrincipal";
    }

    @GetMapping("/registroPaciente")
    public String registroPaciente() {
        
        return "registroPaciente";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Autowired
    EspecialidadServicios especialidadServicios;

    @GetMapping("/generacionTurnosSec")
    public String generacionTurnosSec(Model modelo, String especialidad) {
        List<Especialidad> especialidades = new ArrayList<Especialidad>();
        try {
            especialidades = (List<Especialidad>) especialidadServicios.guardarEspecialidad(especialidad);
            Turno turno = new Turno();
            modelo.addAttribute("especialidades", especialidades);
            modelo.addAttribute("turno", turno);
            return "GeneracionTurnosSec";
        } catch (Exception ex) {
            ex.getStackTrace();
            modelo.addAttribute("error2", ex);
            return "GeneracionTurnosSec";
        }

    }

    @GetMapping("/principalMedico")
    public String principalMedico() {
        return "principal-Medico";
    }

    @GetMapping("/principalAdministrador")
    public String principalaAdministrador() {
        return "paginaPrincipal-Admin_axel";
    }

    @GetMapping("/listaDeUsuariosAdmin")
    public String listaDeUsuariosAdmin() {
        return "listaDeUsuarios-Admin_axel";
    }

    @GetMapping("/listaDePersonalAdmin")
    public String listaDePersonalAdmin() {
        return "listaDePersonal-Admin_axel";
    }

    @GetMapping("/listaDePacientesAdmin")
    public String listaDePacientesAdmin() {
        return "listaDePacientes-Admin_axel";
    }

    @GetMapping("/usuarioRegistroFormulario")
    public String usuarioRegistroFormulario() {
        return "usuarioRegistroFormulario_axel";
    }

    @GetMapping("/index")
    public String index() {
        return "Index";
    }

    @GetMapping("/turnoAtendido")
    public String turnoAtendido() {
        return "turnoAtendido-Medico";
    }

    @GetMapping("/turnoEnProceso")
    public String turnoEnProceso() {
        return "listaTurnoEnProceso-Medico";
    }

    @GetMapping("/solicitarTurno")
    public String solicitarTurno() {
        return "solicitarTurno";
    }

    @GetMapping("/especialidades")
    public String listaEspecialidades() {
        return "ListaEspecialidades";
    }

    @GetMapping("/userRegistradoSec")
    public String UserRegistrados() {
        return "UserRegistradoSec";
    }

    @GetMapping("/quienesSomos")
    public String quieneSomos() {
        return "QuieneSomos";
    }
}
