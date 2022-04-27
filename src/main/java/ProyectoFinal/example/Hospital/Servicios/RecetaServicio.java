/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Medicamentos;
import ProyectoFinal.example.Hospital.Entidades.Medico;
import ProyectoFinal.example.Hospital.Entidades.Paciente;
import ProyectoFinal.example.Hospital.Entidades.Receta;
import ProyectoFinal.example.Hospital.Repositorios.RecetaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecetaServicio {
    
    @Autowired
    RecetaRepositorio recetaRepositorio;
    
    public Receta crearReceta(String id, List<Medicamentos> medicamentos, String descripcionAdministracion, Medico medico, Paciente paciente) throws Exception{
        Receta nuevaReceta = new Receta();
        if(descripcionAdministracion==null||descripcionAdministracion.isEmpty()){
            throw new Exception("La receta debe contener una descripcion");
        }
         if(medicamentos==null||medicamentos.isEmpty()){
            throw new Exception("La receta debe contener algun medicamento");
        }
         if(medico==null){
            throw new Exception("Especificar medico");
        }
         if(null==paciente){
            throw new Exception("Especificar paciente");
        }
        
        nuevaReceta.setMedico(medico);
        nuevaReceta.setPaciente(paciente);
        nuevaReceta.setMedicamentos(medicamentos);
        nuevaReceta.setDescripcionAdministracion(descripcionAdministracion);
        
        return RecetaRepositorio.save(nuevaReceta);
    }
    
    public Receta modificarReceta(String id, String descripoionAdministracion) throws Exception{
        Receta recetaModificada = RecetaRepositorio.findById(id).orElse(null);
        
        if(recetaModificada == null){
            throw new Exception("No se encontro la Receta a Modificar");
        }
        if(descripoionAdministracion == null||descripoionAdministracion.isEmpty()){
            throw new Exception("La receta debe contener una descripcion");
        }
        
        recetaModificada.setDescripcionAdministracion(descripoionAdministracion);
        
        return recetaRepositorio.save(recetaModificada);
    }
    
    public Receta cambiarMedicamentos(String id, List<Medicamentos> medicamentos) throws Exception{
        Receta recetaModificada = RecetaRepositorio.findById(id).orElse(null);
        
        if(recetaModificada == null){
            throw new Exception("No se encontro la consulta a Modificar");
        }
        if(medicamentos==null||medicamentos.isEmpty()){
            throw new Exception("La receta debe contener algun medicamento");
        }
        recetaModificada.setMedicamentos(medicamentos);
        
        return RecetaRepositorio.save(recetaModificada);
    }
    
    
    public List<Receta> listarRecetas(){
        return RecetaRepositorio.findAll();
    }
    
}
