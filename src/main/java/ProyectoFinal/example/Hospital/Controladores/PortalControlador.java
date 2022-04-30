/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author AXEL
 */
@Controller

public class PortalControlador {
    
    @GetMapping("")
    public String index(ModelMap modelMap){
        return "Index";
    }
    
    
    @GetMapping("/horarios")
    public String horario(ModelMap modelMap){
        return "ListaEspecialidades";
    }
    
}
