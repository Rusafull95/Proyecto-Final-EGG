/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

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
    
    //crearMedico recibe una matricula, una lista de especialidades y un usuario para crear un medico
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
    
    //modificarMedico recibe la matricula del medico que desea modificar y una lista de especialidades que va a ser las nuevas especialidades
    @Transactional
    public void modificarMedico(String numMatricula, List<Especialidad>especialidades) throws Exception{
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
        
            medicoRepositorio.save(medico);
        }else{
            throw new Exception("No se encontró el medico");
        }
    }
    
    //eliminarMedico recibe el número de matricula del medico que se desea eliminar y modifica el usuario.alta del medico y lo deja en false(este metodo puede que de problemas)
    public void eliminarMedico(String numMatricula) throws Exception{
        Optional<Medico> respuesta = medicoRepositorio.findById(numMatricula);
        if(respuesta.isPresent()){
            Medico medico = respuesta.get();
            medico.getUsuario().setAlta(false);
        
            medicoRepositorio.save(medico);
        }else{
            throw new Exception("No se encontró el turno");
        }
    }
    
    //mostrarMedicos devuelve una lista de todos los medico que posean el valor true en su alta de usuario
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
