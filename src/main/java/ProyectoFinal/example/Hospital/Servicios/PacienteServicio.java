package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Repositorios.PacienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServicio {
    
    @Autowired
    PacienteRepositorio pacienteRepositorio;
    
    public List<Paciente> listarPacientes(){
        return pacienteRepositorio.findAll();
    }
    
}
