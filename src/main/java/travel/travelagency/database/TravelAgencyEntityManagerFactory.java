package travel.travelagency.database;

import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TravelAgencyEntityManagerFactory {

  static final Logger logger = LogManager.getLogger(TravelAgencyEntityManagerFactory.class);

  private final EntityManagerFactory entityManagerFactory;

  public TravelAgencyEntityManagerFactory(String dbPropertiesPath) {
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
