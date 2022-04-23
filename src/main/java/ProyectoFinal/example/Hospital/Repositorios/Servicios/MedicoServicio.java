/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Repositorios.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Repositorios.MedicoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class MedicoServicio {
    
    @Autowired
    private MedicoRepositorio medicoRepositorio;
    
    @Transactional
    public void crearMedico(String numMatricula, List<Especialidad>especialidades, Usuario usuario) throws Exception{
        if(numMatricula == null || "".equals(numMatricula.trim())){
            throw new Exception("Debe ingresar su matricula");
        }
        if (especialidades == null) {
            throw new Exception("Debe especificar sus especialidades");
        }
        Medico medico = new Medico();
        medico.setNumeroDeMatricula(numMatricula);
        medico.setEspecialidades(especialidades);
        medico.setUsuario(usuario);
        medicoRepositorio.save(medico);
    }
    
    @Transactional
    public void modificarMedico(String numMatricula, List<Especialidad>especialidades, Usuario usuario) throws Exception{
        if(numMatricula == null || "".equals(numMatricula.trim())){
            throw new Exception("Debe ingresar su matricula");
        }
        if (especialidades == null) {
            throw new Exception("Debe especificar sus especialidades");
        }
        Optional<Medico> respuesta = medicoRepositorio.findById(numMatricula);
        if(respuesta.isPresent()){
            Medico medico = respuesta.get();
            medico.setEspecialidades(especialidades);
            medico.setUsuario(usuario);
        
            medicoRepositorio.save(medico);
        }else{
            throw new Exception("No se encontró el medico");
        }
    }
    
    public void cancelarTurno(String numMatricula) throws Exception{
        Optional<Medico> respuesta = medicoRepositorio.findById(numMatricula);
        if(respuesta.isPresent()){
            Medico medico = respuesta.get();
            medico.getUsuario().setAlta(false);
        
            medicoRepositorio.save(medico);
        }else{
            throw new Exception("No se encontró el turno");
        }
    }
    
    public List<Medico> mostrarMedicos(){
        List<Medico>medico = medicoRepositorio.findAll();
        List<Medico>medicos = new ArrayList();
        for (Medico aux : medico) {
            if(aux.getUsuario().isAlta()){
                medicos.add(aux);
            }
        }
        return medicos;
    }
}
