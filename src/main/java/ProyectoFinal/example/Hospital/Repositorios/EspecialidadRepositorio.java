package ProyectoFinal.example.Hospital.Repositorios;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<Especialidad, String>{
    
}
