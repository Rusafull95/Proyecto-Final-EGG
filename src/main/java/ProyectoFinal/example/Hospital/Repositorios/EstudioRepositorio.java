/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Repositorios;

import ProyectoFinal.example.Hospital.Entidades.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AXEL
 */
@Repository
public interface EstudioRepositorio extends JpaRepository<Estudio, String>{
    
}
