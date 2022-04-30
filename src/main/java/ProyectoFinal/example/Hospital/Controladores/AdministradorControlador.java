/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Servicios.MedicoServicio;
import ProyectoFinal.example.Hospital.Servicios.PacienteServicio;
import ProyectoFinal.example.Hospital.Servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author AXEL
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;
    
    @Autowired
    PacienteServicio pacienteServicio;
    
    @Autowired
    MedicoServicio medicoServicio;
    
    @GetMapping("/principal")
    public String paginaPrincipal(){
        return "paginaPrincipal-Admin_axel";
    }
    
    @GetMapping("/listarUsuarios")
    public String listaUsuarios(Model modelo){
        List<Usuario> listadeUsuarios = usuarioServicio.listarUsuarios();
        
        modelo.addAttribute("listaUsuarios", listadeUsuarios);
        return "listaDeUsuarios-Admin_axel";
    }
    @GetMapping("/listarPersonal")
    public String listaPersonal(Model modelo){
        List<Medico> listaDeMedicos = medicoServicio.mostrarMedicos();
        
        modelo.addAttribute("listaMedicos", listaDeMedicos);
        return "listaDePersonal-Admin_axel";
    }
    @GetMapping("/listarPacientes")
    public String listaPacientes(Model modelo){
        List<Paciente>  listaDePacientes = pacienteServicio.listarPacientes();
        
        modelo.addAttribute("listaPacientes", listaDePacientes);
        return "listaDePacientes-Admin_axel";
    }
    @GetMapping("/registrarUsuario")
    public String registrarAUsuario(RedirectAttributes redirectAttributes
    ){
        
        return "redirect:/usuario/formulario";
    }
    @GetMapping("/registrarPaciente")
    public String registrarAPaciente(){
        
        return "redirect:/front/registroPaciente";
    }
    @GetMapping("/principalmiguel")
    public String paginaAdmin(){
        return "adminhospital-listas-migue";
    }
}
