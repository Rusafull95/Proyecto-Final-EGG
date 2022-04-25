
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

    public Receta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Medicamentos> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamentos> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getDescripcionAdministracion() {
        return descripcionAdministracion;
    }

    public void setDescripcionAdministracion(String descripcionAdministracion) {
        this.descripcionAdministracion = descripcionAdministracion;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
    
}
