package sv.com.miguel.capadatos;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
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
    public PersonaDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
   
   //Metodo que retorna una instancia de la Session actual o activa a partir del
    //SessionFactory
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    

    @Override
    public void insertarPersona(Persona persona) {
        getCurrentSession().saveOrUpdate(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        getCurrentSession().saveOrUpdate(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        getCurrentSession().delete(persona);
    }

    @Override
    public Persona findPersonaById(long idPersona) {
        return getCurrentSession().get(Persona.class, idPersona);
    }

    @Override
    public List<Persona> findAllPersonas() {
        return getCurrentSession().createQuery("FROM Persona").list();
    }

    @Override
    public long contadorPersonas() {
        Query q = getCurrentSession().createQuery("select count(p) from Persona p");
        return (long) q.getSingleResult();
    }

    @Override
    public Persona getPersonaByEmail(Persona persona) {
        String email = persona.getEmail();
        Query q = getCurrentSession().createQuery("Select p from Persona p where p.nombre =:email");
        return (Persona) q.getSingleResult();
    }
    
}
