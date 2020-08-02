package sv.com.miguel.spring;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sv.com.miguel.capadatos.PersonaDao;
import sv.com.miguel.domain.Persona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-test.xml","classpath:applicationContext.xml"})
public class TestPersonaDaoImpl {
    //Instancia de Logger
    private static Log logger = LogFactory.getLog("TestPersonaDaoImpl");
    
    //inyectamos una instancia de Personadao
    @Autowired
    private PersonaDao personaDao;
    
    
    //Metodo que deberia mostrar todas las personas registradas
    @Test   //indicamos que este es un metodo de prueba de JUnit
    @Transactional  //indicamos que es un metodo transaccional de Spring
    public void deberiaMostrarPersonas(){
        try{
            System.err.println("");
            logger.info("Inicio del test deberiaMostrarPersonas");
            
            //Recuperamos todas las personas
            List<Persona> listPersona = personaDao.findAllPersonas();
            int contadorPersonas=0;
            
            //Recorriendo el Listado de personas
            for(Persona persona:listPersona){
                logger.info("Persona: "+persona);
                contadorPersonas++;
            }
            //Segun el numero de personas recuperado, deberia ser el mismo
            assertEquals(contadorPersonas, personaDao.contadorPersonas());
            
            logger.info("Fin del test deberiaMostrarPersonas");
        }catch(Exception e){
            logger.error("Error JDBC: "+e);
        }
    }
    
    //Metodo que deberia devolver una Persona en base a si Id
    @Test
    @Transactional
    public void deberiaMostrarPersonaPorId(){
        try{
            System.err.println("");
            logger.info("Inicio de Test deberiaMostrarPersonaId");
            int idPersona=1;
            
            //Recuperamos la persona en base a su Id
            Persona persona = personaDao.findPersonaById(idPersona);
            
            //Comprobamos si el nombre de la persona recuperada es "Admin"
            assertEquals("Admin", persona.getNombre());
            //Imprimimos todo el objeto
            logger.info("Persona recuperada: idPersona=("+idPersona+")  "+persona);
            
            logger.info("Fin de Test deberiaMostrarPersonaId");
        }catch(Exception e){
            logger.error("Error JDBC: "+e);
        }
    }
    
    //Metodo que deberia insertar una nueva Persona en la BD
    
    @Test
    @Transactional
    public void deberiaInsertarPersona(){
        try{
            System.err.println("");
            logger.info("Inicio de Test deberiaInsertarPersona");
            //El Script de tatos tiene 3 registros
            assertEquals(3, personaDao.contadorPersonas());
            
            //Creamos una nueva persona
            Persona persona  = new Persona();
            persona.setNombre("Carlos");
            persona.setApePaterno("Romero");
            persona.setApeMaterno("Garcia");
            persona.setEmail("cRomero@gmail.com");
            //Insertamos la nueva persona
            personaDao.insertarPersona(persona);
            
            //recuperamos la persona recien insertada por su email
            persona = personaDao.getPersonaByEmail(persona);
            logger.info("Persona insertada: "+persona);
            
            //Deberia haber cuatro personas en la tabla Persona
            assertEquals(4, personaDao.contadorPersonas());
            
            logger.info("Fin de Test deberiaInsertarPersona");
        }catch(Exception e){
            logger.error("Error JDBC: "+e);
        }
    }
    
    
}
