/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author AXEL
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @GetMapping("/formulario")
    public String formularioRegistro(){
        return "usuarioRegistroFormulario_axel";
    }
    
}
