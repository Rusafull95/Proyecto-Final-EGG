/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Repositorios;

import ProyectoFinal.example.Hospital.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AXEL
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    //Query
    @Query("SELECT u from Usuario u where u.mail = :mail")
    public Usuario buscarPorMail(@Param("mail")String mail);
    
}
