package sv.com.miguel.capaservicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.com.miguel.capadatos.PersonaDao;
import sv.com.miguel.domain.Persona;

@Service("personaService") //indicamos que esta es una Clase de Servicio de Spring
@Transactional  //indicamos que todos los metodos de la Clase seran transaccionales
public class PersonaServiceImpl implements PersonaService{
    
    //inyectamos una instancia de PersonaDao
    @Autowired
    PersonaDao personaDao;

    @Override
    public List<Persona> listarPersonas() {
        return personaDao.findAllPersonas();
    }

    @Override
    public Persona recuperarPersona(Persona persona) {
        return personaDao.findPersonaById(persona.getIdPersona());
    }

    @Override
    public void agregarPersona(Persona persona) {
        personaDao.insertarPersona(persona);
    }

    @Override
    public void modificarPersona(Persona persona) {
        personaDao.updatePersona(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
        personaDao.deletePersona(persona);
    }
    
}
