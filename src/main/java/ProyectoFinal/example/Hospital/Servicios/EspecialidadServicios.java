/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Especialidad;
import ProyectoFinal.example.Hospital.Repositorios.EspecialidadRepositorio;
import java.util.ArrayList;
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
    
    //crearEspecialidad recibe un nombre y crea una Especialidad
    @Transactional
    public Especialidad guardarEspecialidad(String nombre) throws Exception{
        if(nombre.isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
       Especialidad especialidad = new Especialidad();
        
       return especialidadRepositorio.save(especialidad);
        
    }
    
    //modificarEspecialidad recibe el id de la especialidad que se desea modificar y el nuevo nombre de la especialidad
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
    
    //mostrarEspecialidades devuelve una lista de todas las especialidades
    public List<Especialidad> mostrarEspecialidades(){
        List<Especialidad>especialidades = especialidadRepositorio.findAll();
        return especialidades;
    }
}
