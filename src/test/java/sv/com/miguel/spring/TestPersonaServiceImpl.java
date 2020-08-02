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
import sv.com.miguel.capaservicio.PersonaService;
import sv.com.miguel.domain.Persona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-test.xml","classpath:applicationContext.xml"})
public class TestPersonaServiceImpl {
  //Objeto Log
    private static Log logger = LogFactory.getLog("TestPersonaServiceImpl");
    
    //Inyectamos una instancia de PersonaService.java
    @Autowired
    private PersonaService personaService;
    
    //Inyectamos una instancia de PersonaDao
    @Autowired
    private PersonaDao personaDao;
    
    @Test  //indicamos que es un metodo de prueba
    @Transactional  //hacemos que el metodo sea transaccional
    public void deberiaMostrarPersonas(){
        try{
            System.err.println("");
            logger.info("Inicio Test deberiaMostrarPersonas");
            //obtenemos el numero de persona registradas en la BD
            int contadorPersonas = this.desplegarPersonas();
            
            assertEquals(contadorPersonas, personaDao.contadorPersonas());
            
            logger.info("Fin Test deberiaMostrarPersonas");
            System.err.println("");
        }catch(Exception e){
            logger.error("Error Servicio: "+ e);
        }
    }
    
    public int desplegarPersonas(){
        //recuperamos la lista de personas registradas
        List<Persona> listPersonas = personaService.listarPersonas();
        
        int contadorPersonas=0;
        for(Persona p :listPersonas){
            logger.info("Persona: "+p);
            contadorPersonas++;
        }
        return contadorPersonas++;
    }
    
    /**
     * Metodo para probar las operaciones
     */
    
    @Test
    @Transactional
    public void testOperaciones(){
        try{
            System.err.println("");
            logger.info("Inicio Test testOperaciones");
            
            //Creamos una nueva instancia de Persona
            Persona persona = new Persona();
            persona.setNombre("Andrea");
            persona.setApePaterno("Lara");
            persona.setEmail("aLara@Gmail.com");
            
            //Agregamos la nueva persona
            personaService.agregarPersona(persona);
            
            //Deberian haber 4 personas
            assertEquals(4, personaDao.contadorPersonas());
            
            //Actualizamos la Persona con Id = 1
            Persona persona2 = personaService.recuperarPersona(new Persona(1));
            persona2.setNombre("Administrador");
            personaService.modificarPersona(persona2);
            
            //desplegamos las personas registradas
            this.desplegarPersonas();
            
            logger.info("Fin Test testOperaciones");
            System.err.println("");
        }catch(Exception e){
            logger.error("Error Servicio: "+e);
        }
    }
    
    
    @Test
    @Transactional
    public void testCompruebaOperaciones(){
        try{
            System.err.println("");
            logger.info("Inicio test testCompruebaOperaciones");
            
            //Deberia haber 3 personas, el caso anterior aplico ROLLBACK por
            //el uso de AbstarctTransactionalJUnit4SpringContextTest
            assertEquals(3, personaDao.contadorPersonas());
            this.desplegarPersonas();
            logger.info("Fin test testCompruebaOperaciones");
            System.err.println("");
        }catch(Exception e){
            logger.error("Error Servicio: "+e);
        }
    }
}
