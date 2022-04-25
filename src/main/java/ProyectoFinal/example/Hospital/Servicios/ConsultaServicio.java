/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Consulta;
import ProyectoFinal.example.Hospital.Repositorios.ConsultaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AXEL
 */
@Service
public class ConsultaServicio {
    
    @Autowired
    ConsultaRepositorio consultaRepositorio;
    
    public Consulta crearConsulta(String descripcion, Integer numero) throws Exception{
        Consulta nuevaConsulta = new Consulta();
        if(descripcion==null||descripcion.isEmpty()){
            throw new Exception("La descripcion debe contener algun Escrito");
        }
        if(numero==null||numero <0){
            throw new Exception("El numero de consulta debe estar tipado correctamente");
        }
        
        nuevaConsulta.setNumero(numero);
        nuevaConsulta.setDescripcion(descripcion);
        
        return consultaRepositorio.save(nuevaConsulta);
    }
    
    public Consulta modificarConsulta(String id, String descripoion) throws Exception{
        Consulta consultaModificada = consultaRepositorio.findById(id).orElse(null);
        
        if(consultaModificada == null){
            throw new Exception("No se encontro la consulta a Modificar");
        }
        if(descripoion == null||descripoion.isEmpty()){
            throw new Exception("La descripcion debe contener algun Escrito");
        }
        
        consultaModificada.setDescripcion(descripoion);
        
        return consultaRepositorio.save(consultaModificada);
    }
    
    public Consulta cambiarNumerodeConsulta(String id, Integer numero) throws Exception{
        Consulta consultaModificada = consultaRepositorio.findById(id).orElse(null);
        
        if(consultaModificada == null){
            throw new Exception("No se encontro la consulta a Modificar");
        }
        if(numero==null||numero <0){
            throw new Exception("El numero de consulta debe estar tipado correctamente");
        }
        consultaModificada.setNumero(numero);
        
        return consultaRepositorio.save(consultaModificada);
    }
    
    public List<Consulta> listarConsultas(){
        return consultaRepositorio.findAll();
    }
    
}
