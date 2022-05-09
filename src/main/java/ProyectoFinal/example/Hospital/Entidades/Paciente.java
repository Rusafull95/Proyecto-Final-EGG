/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Paciente {
    @Id
    private Integer dni;
    private String domicilio;
    private String obraSocial;
    private String plan;
    private String cantGrupoFamiliar;
    private String numeroDeAfiliado;
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Paciente() {
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
      public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
    
      public String getCantGrupoFamiliar() {
        return cantGrupoFamiliar;
    }

    public void setCantGrupoFamiliar(String cantGrupoFamiliar) {
        this.cantGrupoFamiliar = cantGrupoFamiliar;
    }
    
      public String getNumeroDeAfiliado() {
        return numeroDeAfiliado;
    }

    public void setNumeroDeAfiliado(String numeroDeAfiliado) {
        this.numeroDeAfiliado = numeroDeAfiliado;
    }
}
