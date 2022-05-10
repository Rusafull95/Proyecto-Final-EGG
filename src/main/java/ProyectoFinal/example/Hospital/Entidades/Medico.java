/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Medico {
    @Id
    private String numeroDeMatricula;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Especialidad> especialidades;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Turno> ListaDeTurnos;
    @OneToOne(cascade = CascadeType.ALL)
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

    public List<Turno> getListaDeTurnos() {
        return ListaDeTurnos;
    }

    public void setListaDeTurnos(List<Turno> ListaDeTurnos) {
        this.ListaDeTurnos = ListaDeTurnos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
