/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Turno;
import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Servicios.MedicoServicio;
import ProyectoFinal.example.Hospital.Servicios.TurnosServicios;
import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IMPORTANTE: no se mandan todos los datos a todas las url asi que avisar si se necesitan más datos en algún html o si se quiere modificar algo(todo se puede cambiar).
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
    
    @Autowired
    private TurnosServicios turnosServicios;
    
    @GetMapping("/principal")
    public String paginaPrincipal(){
        return "principal-Medico";
    }
    
    @GetMapping("/principal1")
    public String paginaPrincipal1(Model modelo,
            HttpSession httpSession
    ) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        System.out.println(usuario);
//        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
//        modelo.addAttribute("turnosHoy", medicoServicio.turnosHoy(numMatricula));
       // modelo.put("4Turnos", medicoServicio.TurnosEnProceso(numMatricula));
        return "principal-Medico";
    }
    
    @PostMapping("/crearmedico")
    public String crearMedico(
            @RequestParam("matricula") String numMatricula, 
            @RequestParam("especialidades") List<Especialidad>especialidades, 
            @RequestParam("usuario") Usuario usuario
    ) throws Exception{
        medicoServicio.crearMedico(numMatricula, especialidades, usuario);
        return "principal-Medico";
    }
    
    @GetMapping("/turnos/hoy")
    public String turnosHoy(@ModelAttribute("turnosHoy") List<Turno>turnosHoy){
        return "principal-Medico";
    }
    
    @GetMapping("/turnos")
    public String turnos(Model modelo, HttpSession httpSession) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
        modelo.addAttribute("listaDeTurnos", medicoServicio.medicoTurnos(numMatricula));
        return "listaDeTurnosCompleta-Medico";
    }

    @GetMapping("/turnos/atendidos")
    public String turnosAtendidos(Model modelo, HttpSession httpSession) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
        modelo.addAttribute("listaDeTurnosAtendidos", medicoServicio.turnosAtendidos(numMatricula));
        return "turnoAtendido-Medico";
    }
    
    @GetMapping("/turnos/enproceso")
    public String turnosEnProgreso(Model modelo, HttpSession httpSession) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
        modelo.addAttribute("listaDeTurnosEnProgreso", medicoServicio.turnosEnProceso(numMatricula));
        return "listaTurnoEnProgreso-Medico";
    }
    
    @PostMapping("/turnos/enproceso/darbaja")
    public String turnosEnProgresoBaja(Model modelo, HttpSession httpSession, @RequestParam("codigo") Integer codigo) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
        turnosServicios.cancelarTurno(codigo);
        modelo.addAttribute("listaDeTurnosEnProgreso", medicoServicio.turnosEnProceso(numMatricula));
        return "listaTurnoEnProgreso-Medico";
    }
    
    @PostMapping("/turnos/enproceso/editar")
    public String turnosEnProgresoEditar(Model modelo, HttpSession httpSession, @RequestParam("codigo") Integer codigo) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
        modelo.addAttribute("id", codigo);
        modelo.addAttribute("listaDeTurnosEnProgreso", medicoServicio.turnosEnProceso(numMatricula));
        return "listaTurnoEnProgreso-Medico";
    }
    
    @PostMapping("/turnos/enproceso/editado")
    public String turnosEnProgresoEditado(Model modelo, 
            HttpSession httpSession, 
            @RequestParam("codigo") Integer codigo, 
            @ModelAttribute("id") Integer id,
            @ModelAttribute("turno") Turno turno
            ) throws Exception{
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String numMatricula = medicoServicio.buscarMedicoByUsuario(usuario).getNumeroDeMatricula();
        modelo.addAttribute("id", id);
        modelo.addAttribute("listaDeTurnosEnProgreso", medicoServicio.turnosEnProceso(numMatricula));

            turnosServicios.modificarTurno(turno.getCodigo(), turno.getCita(), turno.getHora(), turno.getPaciente(), turno.getMedico(), turno.getEstado(), turno.getConsulta(), turno.getEspecialidad(), turno.getSecretaria());
            modelo.addAttribute("id", null);

        return "listaTurnoEnProgreso-Medico";
    }
    
    @GetMapping("/especialidad")
    public String buscarPorEspecialidad(Model modelo, @RequestParam("especialidad") Especialidad especialidad){
        modelo.addAttribute("listaMedEsp", medicoServicio.buscarMedPorEspecialidad(especialidad));
        return "principal-Medico";
    }
    
    @GetMapping("/turno")
    public String turno(Model modelo, @RequestParam("codigo")Integer codigo) throws Exception{
        modelo.addAttribute("turnoX", turnosServicios.buscarTurnoPorCodigo(codigo));
        return "index";
    }
}