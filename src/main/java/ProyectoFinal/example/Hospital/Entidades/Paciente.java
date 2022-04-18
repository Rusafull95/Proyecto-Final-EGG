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

@Entity
public class Paciente {
    @Id
    private Integer dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String obraSocial;
    private Date fechaDeNacimiento;
    private List<Consulta> historial;
    
    
    
}
