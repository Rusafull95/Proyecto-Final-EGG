/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lucas
 */
@Service
public class TurnosServicios {
    
    @Autowired
    private TurnosRepositorio turnosRepositorio;
    
    //crear turno recibe una fecha(cita), un objeto paciente, un objeto medico, un objeto consulta, un objeto especialidad y un objeto secretaria para crear un turno (el estado se pone por solo en solicitado)
    @Transactional
    public void crearTurnos(Date cita, Paciente paciente, Medico medico, Especialidad especialidad, Secretaria secretaria) throws Exception{
        boolean medEspe = false;
        for (Especialidad aux : medico.getEspecialidades()) {
            if(aux == especialidad){
                medEspe = true;
            }
        }
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
        if(!medEspe){
            throw new Exception("El médico no es experto en el campo ingresado");
        }
        Turnos turnos = new Turnos();
        turnos.setCita(cita);
        turnos.setPaciente(paciente);
        turnos.setMedico(medico);
        turnos.setEstado(EstadoDelTurno.SOLICITADO);
        turnos.setEspecialidad(especialidad);
        turnos.setSecretaria(secretaria);
        turnosRepositorio.save(turnos);
    }
    
    //modificarTurno recibe el código del turno que se desea modificar y los mismos parametros que el metodo crearTurno para modificar el turno seleccionado
    @Transactional
    public void modificarTurno(Integer codigo, Date cita, Paciente paciente, Medico medico, EstadoDelTurno estado, Consulta consulta, Especialidad especialidad, Secretaria secretaria) throws Exception{
//        boolean medEspe = false;
//        for (Especialidad aux : medico.getEspecialidades()) {
//            if(aux == especialidad){
//                medEspe = true;
//            }
//        }
//        if(cita == null){
//            throw new Exception("Debe indicar un día");
//        }
//        if(paciente == null){
//            throw new Exception("Debe indicar un paciente");
//        }
//        if(medico == null){
//            throw new Exception("Debe indicar un medico");
//        }
//        if(especialidad == null){
//            throw new Exception("Debe indicar una especialidad");
//        }
//        if(!medEspe){
//            throw new Exception("El médico no es experto en el campo ingresado");
//        }
        Optional<Turnos> respuesta = turnosRepositorio.findById(codigo);
        if(respuesta.isPresent()){
            Turnos turno = respuesta.get();
            if(cita != null){
            turno.setCita(cita); 
            }
            if(paciente != null){   
            turno.setPaciente(paciente);
            }
            if(medico != null){   
            turno.setMedico(medico);
            }
            if(estado != null){   
            turno.setEstado(estado);
            }
            if(consulta != null){
            turno.setConsulta(consulta);
            }
            if(especialidad != null){   
            turno.setEspecialidad(especialidad);
            }
            if(secretaria != null){   
            turno.setSecretaria(secretaria);
            }
        
            turnosRepositorio.save(turno);
        }else{
            throw new Exception("No se encontró el turno");
        }
    }
    
    //cancelarTurno recibe el código del turno que se desea eliminar y modifica el estado del turno a "CANCELADO"
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
    
    //mostrarTurnos muestra todos los turno incluso los cancelados
    public List<Turnos> mostrarTurnos(){
        List<Turnos>turnos = turnosRepositorio.findAll();
        return turnos;
    }
    
    public Turnos buscarTurnoPorCodigo(Integer codigo) throws Exception{
        Optional<Turnos> respuesta = turnosRepositorio.findById(codigo);
        if(respuesta.isPresent()){
            return respuesta.get();
        }else{
            throw new Exception("No se encontró el turno");
        }
    }
    
    //Solicitar turno-Belen-
    
     @Autowired
    private SolicitarTurnoServicio solicitarTurnoServicio;
            
      private String solicitarTurno(Model model){
       Turnos turno= new Turnos();
//       turno.setCita(new Date);
//       turno.sethora(new Date);
//       turno.setEspecialidad(new String);
        model.addAttribute("turno",turno);
        
        return "solicitarTurno";
    }   
    
   @PostMapping("/save")
   public String save(@RequestParam("solicitarTurno") string solicitarTurno) {
       System.out.println("El contenido es:" +solicitarTurno);
       Turno turno = new turno();
       solicitarTurnoServicio.guardarTurno(turno)
   }
}
