/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Repositorios.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Repositorios.EspecialidadRepositorio;
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
public class EspecialidadServicios {
    
    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws Exception{
        if(nombre == null || "".equals(nombre.trim())){
            throw new Exception("El nombre no puede ser nulo");
        }
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(nombre);
        especialidadRepositorio.save(especialidad);
    }
    
    @Transactional
    public void modificarEspecialidad(String id, String nombre) throws Exception{
        if(nombre == null || "".equals(nombre.trim())){
            throw new Exception("El nombre no puede ser nulo");
        }
        Optional<Especialidad> respuesta = especialidadRepositorio.findById(id);
        if(respuesta.isPresent()){
            Especialidad especialidad = respuesta.get();
            especialidad.setNombre(nombre);
        
            especialidadRepositorio.save(especialidad);
        }else{
            throw new Exception("No se encontró la especialidad");
        }
    }
    
    public List<Especialidad> mostrarEspecialidades(){
        List<Especialidad>especialidades = especialidadRepositorio.findAll();
        return especialidades;
    }
}
