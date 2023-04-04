package travel.travelagency.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TravelAgencyEntityManagerFactoryTest {

  private final static String DB_PROPERTIES_PATH = "db.properties";

  @Test
  public void testConstructor() {
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactory(DB_PROPERTIES_PATH));
  }

  @Test
  public void testCreateEntityManager() {
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactory(DB_PROPERTIES_PATH).createEntityManager());
    assertNotEquals(null, new TravelAgencyEntityManagerFactory(DB_PROPERTIES_PATH).createEntityManager());
  }

}
