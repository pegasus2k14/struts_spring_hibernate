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
@ContextConfiguration(locations = {"classpath:datasource-test.xml","applicationContext.xml"})
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
    
    
}
