/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Repositorios.UsuarioRepositorio;
import ProyectoFinal.example.Hospital.enums.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AXEL
 */
@Service
public class UsuarioServicio {
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    //CREAR UN USUARIO NUEVO
    public Usuario crearUsuario(String mail, String nombre, String apellido, String password_1, String password_2) throws Exception{
        Usuario nuevoUsuario = new Usuario();
        
        verificarDatos(mail, nombre, apellido, password_1, password_2);
        
        nuevoUsuario.setMail(mail);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        
        nuevoUsuario.setPassword(password_1);
        
        nuevoUsuario.setAlta(false);
        
        return usuarioRepositorio.save(nuevoUsuario);
    }
    
    // Asignar El Rol Correspondiente al Usuario
    public Usuario asignarRol(Usuario usuario, String rol) throws Exception{
        
        Usuario usuarioConRol = buscarUsuarioPorId(usuario.getId());
        
        if(usuarioConRol == null){
            throw new Exception("No se encontro el usuario para asignarle un rol");
        }
        
        usuarioConRol.setRol(Rol.valueOf(rol));
        
        return usuarioRepositorio.save(usuarioConRol);
    }
    
    //VALIDACION DATOS CREACION USUARIO
    public void verificarDatos(String mail, String nombre, String apellido, String password_1, String password_2) throws Exception{ 
        
        if(mail==null||mail.isEmpty()){     //VERIFICA QUE El mail NO ESTE VACIO
            throw new Exception("El mail no debe ser nulo"); 
        }
        
        Usuario usuarioExistente = usuarioRepositorio.buscarPorMail(mail);
        
        if(usuarioExistente != null){
            throw new Exception("El mail ya esta en uso, por favor Ingrese uno nuevo");
        }
        
        if(nombre==null||nombre.isEmpty()){     //VERIFICA QUE El Nombre NO ESTE VACIO
            throw new Exception("El nombre no debe estar vacio");
        }
        if(apellido==null||apellido.isEmpty()){   //VERIFICA QUE El Apellido NO ESTE VACIO
            throw new Exception("El apellido no debe estar vacio"); 
        }
        
        //VERIFICA QUE LA 1ra CONTRASEÑA NO ESTE VACIA 
        if(password_1==null||password_1.isEmpty()){
            throw new Exception("La contraseña no puede estar vacia");
        }else if(password_1.length()<6){                                //VERIFICA QUE LA CONTRASEÑA TENGA COMO MINIMO 6 caracteres
            throw new Exception("La contraseña debe tener como minimo 6 caracteres");
        }
        
        
        //VERIFICA QUE LA 2da CONTRASEÑA NO ESTE VACIA
        if(password_2==null||password_2.isEmpty()){
            throw new Exception("Debe escribir la contraseña nuevamente");
        }
        
        // VERIFICA SI AMBAS CONTRASEÑAS SON IGUALES
        if(!password_1.equals(password_2)){
            throw new Exception("La contraseñas deben ser iguales");
        }
    }
    
    
    // Modificar Usuario, 
    // Cambiar contraseña Contraseña,
    public void modificarPassword(Usuario usuario, String passwordAnterior, String passwordNueva) throws Exception{
        Usuario usuarioAModificar = buscarUsuarioPorId(usuario.getId());
        
        if(usuarioAModificar==null){
            throw new Exception("No se encontro el usuario");
        }
        
        if(!passwordAnterior.equals(usuarioAModificar.getPassword())){
            throw new Exception("No es su contraseña actual, pruebe Nuevamente");
        }
        
        if(passwordNueva==null || passwordNueva.isEmpty()){
            throw new Exception("Debe Ingresar Una Contraseña valida");
        }
        if(passwordNueva.length()<6){
            throw new Exception("La nueva Contraseña debe tener 6 caracteres como minimo");
        }
        
        usuarioAModificar.setPassword(passwordNueva);
        
        usuarioRepositorio.save(usuarioAModificar);
    }
    
    // BUSCAR POR ID el USUARIO
    public Usuario buscarUsuarioPorId(String id){
        return usuarioRepositorio.findById(id).orElse(null);
    }
    
    //LISTAR USUARIOS
    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }
    
    
}
