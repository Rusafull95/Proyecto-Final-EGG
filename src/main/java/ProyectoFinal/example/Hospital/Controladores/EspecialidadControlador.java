/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Servicios.EspecialidadServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  Modelos:
 * 1)_"listaEspecialidades"_ (URL: "/especialidad/lista") muestra la lista de especialidades
 */
@Controller
@RequestMapping("/especialidad")
public class EspecialidadControlador {
    
    @Autowired
    private EspecialidadServicios especialidadServicios;
    
    @GetMapping("/lista")
    public String listaDeEspecialidades(ModelMap modelo){
        modelo.put("listaEspecialidades", especialidadServicios.mostrarEspecialidades());
        return "ListaEspecialidades";
    }
    
    @PatchMapping("/modificar")
    public String modificarListaDeEspecialidades(
            ModelMap modelo, @RequestParam("idEspecialidad") String id, 
            @RequestParam("especialidadNombre") String nombre
    ) throws Exception{
        especialidadServicios.modificarEspecialidad(id, nombre);
        return "ListaEspecialidades";
    }
}
