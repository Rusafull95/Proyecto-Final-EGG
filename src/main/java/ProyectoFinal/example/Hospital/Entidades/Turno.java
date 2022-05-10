/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import ProyectoFinal.example.Hospital.enums.EstadoDelTurno;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    @Temporal(TemporalType.DATE)
    private Date cita;
    private boolean realizado;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date hora;
    @OneToOne
    private Paciente paciente;
    @OneToOne
    private Medico medico;
    @Enumerated(EnumType.STRING)
    private EstadoDelTurno estado;
    @OneToOne
    private Consulta consulta;
    @OneToOne
    private Especialidad especialidad;
    @OneToOne
    private Secretaria secretaria;

    public Turno() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getCita() {
        return cita;
    }

    public void setCita(Date cita) {
        this.cita = cita;
    }
    
    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public EstadoDelTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoDelTurno estado) {
        this.estado = estado;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }
    
    
    
}
