package travel.travelagency.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TravelAgencyEntityManagerFactoryTest {

  @Test
  public void testConstructor() {
    assertDoesNotThrow(TravelAgencyEntityManagerFactory::new);
  }

  @Test
  public void testCreateEntityManager() {
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactory().createEntityManager());
    assertNotEquals(null, new TravelAgencyEntityManagerFactory().createEntityManager());
  }

}
