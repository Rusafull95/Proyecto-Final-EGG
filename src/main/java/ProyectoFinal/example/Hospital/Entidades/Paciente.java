/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import java.util.Date;
import java.util.List;
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
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;
    @OneToOne
    private Usuario usuario;
    
    
    
}
