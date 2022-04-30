/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author AXEL
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;
    
    @GetMapping("/principal")
    public String paginaPrincipal(){
        return "paginaPrincipal-Admin_axel";
    }
    
    @GetMapping("/listarUsuarios")
    public String listaUsuarios(Model modelo){
        List<Usuario> listaUsuarios = usuarioServicio.listarUsuarios();
        
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        return "listaDeUsuarios-Admin_axel";
    }
    @GetMapping("/listarPersonal")
    public String listaPersonal(){
        return "listaDePersonal-Admin_axel";
    }
    @GetMapping("/listarPacientes")
    public String listaPacientes(){
        return "listaDePacientes-Admin_axel";
    }
     
    @GetMapping("/principalmiguel")
    public String paginaAdmin(){
        return "adminhospital-listas-migue";
    }
}
