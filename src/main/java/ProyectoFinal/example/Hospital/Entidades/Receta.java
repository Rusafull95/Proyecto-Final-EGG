
package ProyectoFinal.example.Hospital.Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Receta {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    @OneToMany
    private List<Medicamentos> medicamentos;
    private String descripcionAdministracion;
    @OneToOne
    private Medico medico;
    @OneToOne
    private Paciente paciente;
    
    
    
}
