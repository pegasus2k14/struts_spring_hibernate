package sv.com.miguel.capadatos;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sv.com.miguel.domain.Persona;

//Indicamos que esta es una Clase Repositorio de Spring
@Repository
public class PersonaDaoImpl implements PersonaDao{
    
    //Variable del tipo SessionFactory
    private SessionFactory sessionFactory;
    
    //inyectamos una instancia de SessionFactory desde el contenedor de Spring
    @Autowired
    private void PersonaDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    //Se necesita de una transaccion activa por ello la prueba unitaria
    //utiliza @transactional

    @Override
    public void insertarPersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona findPersonaById(long idPersona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> findAllPersonas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long contadorPersonas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona getPersonaByEmail(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
