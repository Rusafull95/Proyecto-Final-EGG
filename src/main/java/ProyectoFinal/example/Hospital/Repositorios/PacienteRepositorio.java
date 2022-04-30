package ProyectoFinal.example.Hospital.Repositorios;

import ProyectoFinal.example.Hospital.Entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, String> {
    
}
