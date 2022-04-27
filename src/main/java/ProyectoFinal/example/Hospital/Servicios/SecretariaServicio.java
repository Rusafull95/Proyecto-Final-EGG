
package ProyectoFinal.example.Hospital.Servicios;


import ProyectoFinal.example.Hospital.Entidades.Consulta;
import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Secretaria;
import ProyectoFinal.example.Hospital.Entidades.Turnos;
import ProyectoFinal.example.Hospital.Repositorios.SecretariaRepositorio;
import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretariaServicio {
    @Autowired
    SecretariaRepositorio secretariaRepositorio;
    
    @Transactional
    public void crearTurnos( LocalDate fechaActual, Paciente paciente, Medico medico, EstadoDelTurno estado, Consulta consulta, Especialidad especialidad, Secretaria secretaria) throws Exception{
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
        turnos.setPaciente(paciente);
        turnos.setMedico(medico);
        turnos.setEstado(estado);
        turnos.setConsulta(consulta);
        turnos.setEspecialidad(especialidad);
        turnos.setSecretaria(secretaria);
        turnosRepositorio.save(turnos);
    }
}
