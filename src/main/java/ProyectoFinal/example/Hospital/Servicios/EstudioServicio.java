/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Consulta;
import ProyectoFinal.example.Hospital.Entidades.Estudio;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Repositorios.EstudioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AXEL
 */
@Service
public class EstudioServicio {

    @Autowired
    private EstudioRepositorio estudioRepositorio;

    public Estudio crearEstudio(String nombreAnalisis, String descripcion, byte[] archivo, Consulta consulta, Medico medico, Paciente paciente) throws Exception {
        Estudio nuevoEstudio = new Estudio();
        if(nombreAnalisis == null || nombreAnalisis.isEmpty()){
            throw new Exception("El nombre Del analisis no debe estar vacio");
        }
        if(descripcion == null || descripcion.isEmpty()){
            throw new Exception("El nombre Del analisis no debe estar vacio");
        }
        
        nuevoEstudio.setNombreAnalisis(nombreAnalisis);
        nuevoEstudio.setDescripcion(descripcion);
        
        if(archivo != null){
            nuevoEstudio.setArchivo(archivo);
        }
        
        if(consulta == null){
            throw new Exception("La consulta no debe estar Vacia");
        }
        nuevoEstudio.setConsulta(consulta);
        
        if(medico == null){
            throw new Exception("El medico debe estar cargado");
        }
        nuevoEstudio.setMedico(medico);
        
        if(paciente == null){
            throw new Exception("El paciente debe estar cargado");
        }
        nuevoEstudio.setPaciente(paciente);

        return estudioRepositorio.save(nuevoEstudio);
    }

    public Estudio modificarEstudio(String id, String nombreAnalisis, String descripcion, byte[] archivo, Consulta consulta, Medico medico, Paciente paciente) throws Exception {

        Estudio estudioModificado = estudioRepositorio.findById(id).orElse(null);
        if (estudioModificado == null) {
            throw new Exception("No se encontro El estudio a Modificar");
        }
        if(nombreAnalisis == null || nombreAnalisis.isEmpty()){
            throw new Exception("El nombre Del analisis no debe estar vacio");
        }
        if(descripcion == null || descripcion.isEmpty()){
            throw new Exception("El nombre Del analisis no debe estar vacio");
        }
        estudioModificado.setNombreAnalisis(nombreAnalisis);
        estudioModificado.setDescripcion(descripcion);

        if(archivo != null){
            estudioModificado.setArchivo(archivo);
        }
        
        if(consulta == null){
            throw new Exception("La consulta no debe estar Vacia");
        }
        estudioModificado.setConsulta(consulta);
        
        if(medico == null){
            throw new Exception("El medico debe estar cargado");
        }
        estudioModificado.setMedico(medico);
        
        if(paciente == null){
            throw new Exception("El paciente debe estar cargado");
        }
        estudioModificado.setPaciente(paciente);

        return estudioRepositorio.save(estudioModificado);
    }
    
    public List<Estudio> listarEstudios(){
        return estudioRepositorio.findAll();
    }
    
}
