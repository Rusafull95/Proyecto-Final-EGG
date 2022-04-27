/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Medico {
    @Id
    private String numeroDeMatricula; 
    private String nombre;
    private String Apellido;
    @OneToMany
    private List<Especialidad> especialidades;
    @OneToMany
    private List<Turnos> ListaDeTurnos;
    @OneToOne
    private Usuario usuario;

    public Medico() {
    }

    public String getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public void setNumeroDeMatricula(String numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Turnos> getListaDeTurnos() {
        return ListaDeTurnos;
    }

    public void setListaDeTurnos(List<Turnos> ListaDeTurnos) {
        this.ListaDeTurnos = ListaDeTurnos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
