/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medico {
    @Id
    private String numeroDeMatricula;
    private String nombre;
    private String Apellido;
    private List<String> especialidad;
    private List<Turnos> ListaDeTurnos;
}
