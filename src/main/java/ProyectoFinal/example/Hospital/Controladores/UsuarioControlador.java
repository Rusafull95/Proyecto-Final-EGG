/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author AXEL
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/formulario")
    public String formularioRegistro(
            ModelMap modelo,
            @RequestParam(name = "idUsuario", required = false) String id,
             RedirectAttributes redirectAttributes
    ) {
        try {
            if (id != null) {
                Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
                modelo.addAttribute("id", usuario.getId());
                modelo.addAttribute("mail", usuario.getMail());
                modelo.addAttribute("nombre", usuario.getNombre());
                modelo.addAttribute("apellido", usuario.getApellido());

                modelo.addAttribute("modificar", "Modificar");
            } else {
                modelo.addAttribute("mail", "");
                modelo.addAttribute("nombre", "");
                modelo.addAttribute("apellido", "");
                modelo.addAttribute("password_1", "");
                modelo.addAttribute("password_2", "");
                modelo.addAttribute("registrar", "Registrarme");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "El FORMULARIO No pudo ser cargado");
            return "redirect:/";
        }

        return "usuarioRegistroFormulario_axel";
    }

    @PostMapping("/registrar")
    public String registroUsuario(Model modelo,
            @RequestParam(name ="id", required=false) String id,
            @RequestParam(name ="mail") String mail,
            @RequestParam(name ="nombre") String nombre,
            @RequestParam(name ="apellido") String apellido,
            @RequestParam(name = "password_1") String password_1,
            @RequestParam(name = "password_2") String password_2
    ) {
        try {
            Usuario usuario = null;
            if (id == null) {
                usuario = usuarioServicio.crearUsuario(mail, nombre, apellido, password_1, password_2);
            }else{
                usuario = usuarioServicio.modificarDatosUsuario(id,mail,nombre,apellido);
            }
            modelo.addAttribute("valido", "Usuario " + id != null ? "editado" : "registrado con exito");
            return "RegistroPaciente";
            
        } catch (Exception ex) {
            ex.printStackTrace();
            modelo.addAttribute("id", id);
            modelo.addAttribute("mail", mail);
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("apellido", apellido);

            modelo.addAttribute("error", ex.getMessage());
            return "usuarioRegistroFormulario_axel";
        }
    }

}
