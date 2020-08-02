package sv.com.miguel.capaservicio;


import java.util.List;
import sv.com.miguel.domain.Persona;

public interface PersonaService {
   //firmas de metodos
    public List<Persona> listarPersonas();
    
    public Persona recuperarPersona(Persona persona);
    
    public void agregarPersona(Persona persona);
    
    public void modificarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
}

