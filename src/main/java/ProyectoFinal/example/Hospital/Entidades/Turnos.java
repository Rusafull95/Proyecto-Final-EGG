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

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Turnos {
    @Id
    private Integer codigo;
    @Temporal(TemporalType.DATE)
    private Date cita;
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
    
    
    
}
