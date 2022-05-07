
package ProyectoFinal.example.Hospital.Servicios;


import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Turno;
import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Repositorios.MedicoRepositorio;
import ProyectoFinal.example.Hospital.Repositorios.PacienteRepositorio;
import ProyectoFinal.example.Hospital.Repositorios.SecretariaRepositorio;
import ProyectoFinal.example.Hospital.Repositorios.TurnosRepositorio;
import ProyectoFinal.example.Hospital.Repositorios.UsuarioRepositorio;
import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretariaServicio {
    
    @Autowired
    SecretariaRepositorio secretariaRepositorio;
    TurnosRepositorio turnosRepositorio;
    UsuarioRepositorio usuarioRepositorio;
    PacienteRepositorio pacienteRepositorio;
    MedicoRepositorio medicoRepositorio;
    
    public void validarTurno(Integer codigo){
        Optional<Turno> repuesta = turnosRepositorio.findById(codigo);
        if(repuesta.isPresent()){
            Turno turno = repuesta.get();
            turno.setEstado(EstadoDelTurno.ENPROCESO);
            
            turnosRepositorio.save(turno);
        }
    }
    
    public void asignarUsuarioAPaciente(String usuario, Integer dni){
        Optional<Usuario> repuestaUsuario = usuarioRepositorio.findById(usuario);
        Optional<Paciente> repuestaPaciente = pacienteRepositorio.findById(dni);
        if(repuestaUsuario.isPresent() && repuestaPaciente.isPresent()){
            Paciente usuarioAsignado = pacienteRepositorio.getById(dni);
            
            usuarioAsignado.setUsuario(usuarioRepositorio.getById(usuario));
            
            pacienteRepositorio.save(usuarioAsignado);
        }
    }
    
    public void asignarMedicoATurno(String numeroMatricula, Integer codigo){
        Optional<Medico> repuestaMedico = medicoRepositorio.findById(numeroMatricula);
        Optional<Turno> repuestaTurno = turnosRepositorio.findById(codigo);
        if(repuestaMedico.isPresent() && repuestaTurno.isPresent()){
            Turno medicoAsignado = turnosRepositorio.getById(codigo);
            
            medicoAsignado.setMedico(medicoRepositorio.getById(numeroMatricula));
            
            turnosRepositorio.save(medicoAsignado);
        }
    }

}
