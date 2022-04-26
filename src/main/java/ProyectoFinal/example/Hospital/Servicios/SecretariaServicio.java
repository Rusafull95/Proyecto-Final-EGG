
package ProyectoFinal.example.Hospital.Servicios;


import ProyectoFinal.example.Hospital.Repositorios.SecretariaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretariaServicio {
    @Autowired SecretariaRepositorio secretariaRepositorio;
    

}
