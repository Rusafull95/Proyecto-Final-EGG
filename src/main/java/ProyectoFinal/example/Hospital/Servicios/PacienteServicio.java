/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Consulta;
import ProyectoFinal.example.Hospital.Entidades.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Repositorios.PacienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServicio {
    
    @Autowired
    PacienteRepositorio pacienteRepositorio;
    

    public void crearPaciente(Integer dni, String domicilio, String obraSocial, Date fechaDeNacimiento, List<Consulta> historial, Usuario usuario){
        Paciente paciente = new Paciente();
        paciente.setDni(dni);
        paciente.setDomicilio(domicilio);
        paciente.setObraSocial(obraSocial);
        paciente.setFechaDeNacimiento(fechaDeNacimiento);
        paciente.setUsuario(usuario);
        pacienteRepositorio.save(paciente);
    }
    
    public void eliminarPaciente(Integer dni) throws Exception{
        Optional<Paciente> repuesta = pacienteRepositorio.findById(dni);
        if(repuesta.isPresent()){
            Paciente paciente = repuesta.get();
            paciente.getUsuario().setAlta(false);
            
            pacienteRepositorio.save(paciente);
        }
    }
    
    public Paciente modificarPaciente(Integer dni, String domicilio, String obraSocial, Date fechaDeNacimiento, List<Consulta> historial, Usuario usuario){
        Paciente pacienteModificado = pacienteRepositorio.findById(dni).orElse(null);
        
        pacienteModificado.setDomicilio(domicilio);
        pacienteModificado.setObraSocial(obraSocial);
        pacienteModificado.setFechaDeNacimiento(fechaDeNacimiento);
        pacienteModificado.setUsuario(usuario); //Hacer logica por si el paciente no tiene usuario en la Web.
        
        return pacienteRepositorio.save(pacienteModificado);
    }
    
    public List<Paciente> mostrarPacientes(){
        List<Paciente> paciente = pacienteRepositorio.findAll(); 
        List<Paciente> pacientes = new ArrayList();
        for (Paciente aux : pacientes) {
            if (aux.getUsuario().isAlta()){
                paciente.add(aux);
            }
        }
        return paciente;
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepositorio.findAll();
    }
    
}
