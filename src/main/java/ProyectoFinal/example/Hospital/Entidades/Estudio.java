
package ProyectoFinal.example.Hospital.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Estudio {
     @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
     @OneToOne
    private Consulta consulta;
    private String nombreAnalisis;
    private String descripcion;
    private byte[] archivo;
    @OneToOne
    private Medico medico;
    @OneToOne
    private Paciente paciente;
   
}
