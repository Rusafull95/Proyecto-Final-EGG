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
    @OneToMany
    private List<Especialidad> especialidades;
    @OneToMany
    private List<Turnos> ListaDeTurnos;
    @OneToOne
    private Usuario usuario;
}
