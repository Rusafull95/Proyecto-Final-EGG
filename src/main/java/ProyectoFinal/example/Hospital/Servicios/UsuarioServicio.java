/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Servicios;

import ProyectoFinal.example.Hospital.Entidades.Usuario;
import ProyectoFinal.example.Hospital.Repositorios.UsuarioRepositorio;
import ProyectoFinal.example.Hospital.enums.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author AXEL
 */

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    //CREAR UN USUARIO NUEVO
    public Usuario crearUsuario(String mail, String nombre, String apellido, String password_1, String password_2) throws Exception{
        Usuario nuevoUsuario = new Usuario();
        
        verificarDatos(mail, nombre, apellido, password_1, password_2);
        
        nuevoUsuario.setMail(mail);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        nuevoUsuario.setPassword(encoder.encode(password_1));
        
        nuevoUsuario.setAlta(false);
        nuevoUsuario.setRol(Rol.PACIENTE);
        
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
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuarioAModificar.setPassword(encoder.encode(passwordNueva));
        
        
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
    
    public void agregarUsuarioALaSesion(Usuario usuario) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("usuario", usuario);
    }
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        try { // esta parte configura los permisos de los usuarios
            Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
            List<GrantedAuthority> autorities = new ArrayList<>();
            
//            agregarUsuarioALaSesion(usuario);
            autorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            
            return new User(mail, usuario.getPassword(), autorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }

    public Usuario modificarDatosUsuario(String id, String mail, String nombre, String apellido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
