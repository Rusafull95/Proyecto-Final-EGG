/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Turnos;
import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Repositorios.MedicoRepositorio;
import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    
    //crearMedico recibe una matrícula, una lista de especialidades y un usuario para crear un médico
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
            throw new Exception("Debe ingresar su matrícula");
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
            throw new Exception("No se encontró el médico");
        }
    }
    
    //eliminarMedico recibe el número de matrícula del médico que se desea eliminar y modifica el usuario.alta del médico y lo deja en false(este método puede que de problemas)
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
    
    //mostrarMedicos devuelve una lista de todos los médico que posean el valor true en su alta de usuario
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
    
    //MostrarMedicoPorId devuelve un médico según su número de matrícula
    public Medico mostrarMedicoPorId(String numMatricula) throws Exception{
        Optional<Medico> respuesta = medicoRepositorio.findById(numMatricula);
        if(respuesta.isPresent()){
            Medico medico = respuesta.get();
        
            return medico;
        }else{
            throw new Exception("No se encontró el médico");
        }
    }
    
    //turnosHoy devuelve los turnos de hoy de un médico ségun su número matrícula
    public List<Turnos>turnosHoy(String numMatricula) throws Exception{
        Medico medico = mostrarMedicoPorId(numMatricula);
        List<Turnos>turnos = medico.getListaDeTurnos();
        List<Turnos>turnosHoy = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Date hoy = calendar.getTime();
        for (Turnos aux : turnos) {
            if(aux.getCita().getDay() == hoy.getDay())
                turnosHoy.add(aux);
        }
        return turnosHoy;
    }
    
    //medicoTurnos devuelve todos los turnos de un médico ségun su número de matrícula
    public List<Turnos>medicoTurnos(String numMatricula) throws Exception{
        Medico medico = mostrarMedicoPorId(numMatricula);
        List<Turnos>turnos = medico.getListaDeTurnos();
        return turnos;
    }
    
    //turnosAtendidos devuelve los turnos atendidos de los médicos
    public List<Turnos>turnosAtendidos(String numMatricula) throws Exception{
        Medico medico = mostrarMedicoPorId(numMatricula);
        List<Turnos>turnosMedico = medico.getListaDeTurnos();
        List<Turnos>turnos = new ArrayList();
        for (Turnos aux : turnosMedico) {
            if(aux.getEstado() == EstadoDelTurno.ATENDIDO){
                turnos.add(aux);
            }
        }
        return turnos;
    }
    
        //turnosAtendidos devuelve los turnos atendidos de los médicos
    public List<Turnos>turnosEnProceso(String numMatricula) throws Exception{
        Medico medico = mostrarMedicoPorId(numMatricula);
        List<Turnos>turnosMedico = medico.getListaDeTurnos();
        List<Turnos>turnos = new ArrayList();
        for (Turnos aux : turnosMedico) {
            if(aux.getEstado() == EstadoDelTurno.ENPROCESO){
                turnos.add(aux);
            }
        }
        return turnos;
    }
}
