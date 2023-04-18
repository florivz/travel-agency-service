package travel.travelagency.database;

import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class creates <code>EntityManager</code> connected to the database.
 * @author I551381
 * @version 1.0
 */
public class TravelAgencyEntityManagerFactory {

  static final Logger logger = LogManager.getLogger(TravelAgencyEntityManagerFactory.class);

  /**
   * private <code>EntityManagerFactory</code> used to create <code>EntityManager</code> objects.
   */
  private final EntityManagerFactory entityManagerFactory;

  /**
   * This constructor reads the provided database properties file and creates an <code>EntityManagerFactory</code>
   * object which creates <code>EntityManager</code> objects.
   */
  public TravelAgencyEntityManagerFactory() {
    String dbPropertiesPath = "db.properties";
    Properties p = this.getDBAccessProperties(dbPropertiesPath);
    try {
      String persistenceUnit = p.getProperty("persistence_unit");
      if(persistenceUnit != null)
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
      else {
        final String msg = "'persistence_unit' property not found in database properties";
        throw new RuntimeException(msg);
      }
    } catch (Exception e) {
      final String MSG = "Unable to create EntityManagerFactory";
      logger.error(MSG);
      throw new RuntimeException(MSG);
    }
  }

  /**
   * This method is used to create an <code>EntityManager</code> object that can persist, read, update, and delete
   * Entities in the database connected to the peristence unit specified in the constructor.
   * @return <code>EntityManager</code> object connected to the database
   */
  public EntityManager createEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  private Properties getDBAccessProperties(String dbPropertiesPath) {
    Properties dbAccessProperties;
    try(InputStream is = getClass().getClassLoader().getResourceAsStream(dbPropertiesPath)) {
      dbAccessProperties = new Properties();
      dbAccessProperties.load( is );
    } catch (Exception e) {
      final String msg = "Loading database connection properties failed";
      throw new RuntimeException(msg);
    }
    return dbAccessProperties;
  }

}
