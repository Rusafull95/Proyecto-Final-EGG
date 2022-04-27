/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Controladores;

import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Turnos;
import ProyectoFinal.example.Hospital.Servicios.MedicoServicio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lucas
 */
@Controller
@RequestMapping("/medico")
public class MedicoControlador {
    
    @Autowired
    private MedicoServicio medicoServicio;
    
    @GetMapping("/principal")
    public String paginaPrincipal(){
        return "principal-Medico";
    }
    
    @GetMapping("/turnoshoy")
    public String turnosHoy(ModelMap modelo, String numMatricula) throws Exception{
        Medico medico = medicoServicio.mostrarMedicoPorId(numMatricula);
        List<Turnos>turnos = medico.getListaDeTurnos();
        List<Turnos>turnosHoy = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Date hoy = calendar.getTime();
        for (Turnos aux : turnos) {
            if(aux.getCita().getDay() == hoy.getDay())
                turnosHoy.add(aux);
        }
        modelo.put("turnosHoy", turnosHoy);
        return "principal-Medico";
    }
    
    @GetMapping("/turnos")
    public String turnos(ModelMap modelo, String numMatricula) throws Exception{
        Medico medico = medicoServicio.mostrarMedicoPorId(numMatricula);
        List<Turnos>turnos = medico.getListaDeTurnos();
        modelo.put("listaDeTurnos", turnos);
        return "principal-Medico";
    }
}
