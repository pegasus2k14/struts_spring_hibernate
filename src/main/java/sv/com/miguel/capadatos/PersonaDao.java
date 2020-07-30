package sv.com.miguel.capadatos;

import java.util.List;
import sv.com.miguel.domain.Persona;

public interface PersonaDao {
    
    //firmas de metodos 
    void insertarPersona(Persona persona);
    
    void updatePersona(Persona persona);
    
    void deletePersona(Persona persona);
    
    Persona findPersonaById(long idPersona);
    
    List<Persona> findAllPersonas();
    
    long contadorPersonas();
    
    Persona getPersonaByEmail(Persona persona);
}
