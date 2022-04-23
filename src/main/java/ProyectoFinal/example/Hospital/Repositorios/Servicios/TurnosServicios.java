/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Repositorios.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Consulta;
import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Secretaria;
import ProyectoFinal.example.Hospital.Entidades.Turnos;
import ProyectoFinal.example.Hospital.Repositorios.TurnosRepositorio;
import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
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
public class TurnosServicios {
    
    @Autowired
    private TurnosRepositorio turnosRepositorio;
    
    @Transactional
    public void crearTurnos(Date cita, Paciente paciente, Medico medico, EstadoDelTurno estado, Consulta consulta, Especialidad especialidad, Secretaria secretaria) throws Exception{
        if(cita == null){
            throw new Exception("Debe indicar un día");
        }
        if(paciente == null){
            throw new Exception("Debe indicar un paciente");
        }
        if(medico == null){
            throw new Exception("Debe indicar un medico");
        }
        if(especialidad == null){
            throw new Exception("Debe indicar una especialidad");
        }
        Turnos turnos = new Turnos();
        turnos.setCita(cita);
        turnos.setPaciente(paciente);
        turnos.setMedico(medico);
        turnos.setEstado(estado);
        turnos.setConsulta(consulta);
        turnos.setEspecialidad(especialidad);
        turnos.setSecretaria(secretaria);
        turnosRepositorio.save(turnos);
    }
    
    @Transactional
    public void modificarTurno(Integer codigo, Date cita, Paciente paciente, Medico medico, EstadoDelTurno estado, Consulta consulta, Especialidad especialidad, Secretaria secretaria) throws Exception{
        if(cita == null){
            throw new Exception("Debe indicar un día");
        }
        if(paciente == null){
            throw new Exception("Debe indicar un paciente");
        }
        if(medico == null){
            throw new Exception("Debe indicar un medico");
        }
        if(especialidad == null){
            throw new Exception("Debe indicar una especialidad");
        }
        Optional<Turnos> respuesta = turnosRepositorio.findById(codigo);
        if(respuesta.isPresent()){
            Turnos turno = respuesta.get();
            turno.setCita(cita);
            turno.setPaciente(paciente);
            turno.setMedico(medico);
            turno.setEstado(estado);
            turno.setConsulta(consulta);
            turno.setEspecialidad(especialidad);
            turno.setSecretaria(secretaria);
        
            turnosRepositorio.save(turno);
        }else{
            throw new Exception("No se encontró el turno");
        }
    }
    
    public void cancelarTurno(Integer codigo) throws Exception{
        Optional<Turnos> respuesta = turnosRepositorio.findById(codigo);
        if(respuesta.isPresent()){
            Turnos turno = respuesta.get();
            turno.setEstado(EstadoDelTurno.CANCELADO);
        
            turnosRepositorio.save(turno);
        }else{
            throw new Exception("No se encontró el turno");
        }
    }
    
    public List<Turnos> mostrarTurnos(){
        List<Turnos>turnos = turnosRepositorio.findAll();
        return turnos;
    }
}
