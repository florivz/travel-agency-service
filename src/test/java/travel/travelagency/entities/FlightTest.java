package travel.travelagency.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FlightTest {

  private static Flight nullFlight, emptyFlight, flight, copyFlight, differentFlight;

  @BeforeAll
  public static void initializeConnections() {
    nullFlight = null;
    emptyFlight = new Flight();
    flight = new Flight(
        1,
        new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 05, 14),
        LocalTime.of(11, 30, 20),
        ZoneId.of("UTC+02:00"),
        LocalDate.of(2023, 05, 14),
        LocalTime.of(16, 15),
        ZoneId.of("UTC-05:00"),
        299.99,
        "EUR"
    );
    copyFlight = new Flight(
        1,
        new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 05, 14),
        LocalTime.of(11, 30, 20),
        ZoneId.of("UTC+02:00"),
        LocalDate.of(2023, 05, 14),
        LocalTime.of(16, 15),
        ZoneId.of("UTC-05:00"),
        299.99,
        "EUR"
    );
    differentFlight = new Flight(
        1,
        new FlightConnection(13, "DL", "0016", "ATL", "FRA"),
        LocalDate.of(2023, 07, 30),
        LocalTime.of(15, 5),
        ZoneId.of("UTC-05:00"),
        LocalDate.of(2023, 07, 31),
        LocalTime.of(7, 30, 38),
        ZoneId.of("UTC+02:00"),
        599.99,
        "EUR"
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyFlight.equals(nullFlight));
    assertFalse(emptyFlight.equals(nullFlight));
  }

  @Test
  public void testEqualsMethodWithEmptyFlightConnection() {
    Flight copyEmptyFlight = new Flight();
    assertDoesNotThrow(() -> emptyFlight.equals(copyEmptyFlight));
    assertTrue(emptyFlight.equals(copyEmptyFlight));
  }

  @Test
  public void testEqualsMethodWithIdenticalFlightConnection() {
    assertDoesNotThrow(() -> flight.equals(copyFlight));
    assertTrue(flight.equals(copyFlight));
  }

  @Test
  public void testEqualsMethodWithDifferentFlightConnection() {
    assertDoesNotThrow(() -> flight.equals(differentFlight));
    assertFalse(flight.equals(differentFlight));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> flight.equals(obj));
    assertFalse(flight.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Flight flight = new Flight(
        1,
        new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 05, 14),
        LocalTime.of(11, 30, 20),
        ZoneId.of("UTC+02:00"),
        LocalDate.of(2023, 05, 14),
        LocalTime.of(16, 15),
        ZoneId.of("UTC-05:00"),
        299.99,
        "EUR"
    );

    assertEquals("""
        DL0015 from FRA to ATL
        Departure:\s""" + flight.getDepartureTimestamp().toString() + '\n' + """
        Arrival  :\s""" + flight.getArrivalTimestamp().toString() + '\n' + """
        Price    : 299.99 EUR""",
        flight.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalFlightConnection() {
    int expectedHashCode  = flight.hashCode();
    int actualHashCode    = copyFlight.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentFlightConnection() {
    int unexpectedHashCode  = flight.hashCode();
    int actualHashCode      = differentFlight.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
