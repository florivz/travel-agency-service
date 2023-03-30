package travel.travelagency.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelTest {

  private static Hotel nullHotel, emptyHotel, hotel, copyHotel, differentHotel;

  @BeforeAll
  public static void initializeHotels() {
    nullHotel = null;
    emptyHotel = new Hotel();
    hotel = new Hotel(
        8398,
        "Luxor Deluxe",
        20000.01,
        "GIB",
        new Address(
            43,
            "Meerenge von Gibraltar",
            "1",
            "00001",
            "Zwischen Marokko und Gibraltar",
            "Mittelmeer"
        )
    );
    copyHotel = new Hotel(
        8398,
        "Luxor Deluxe",
        20000.01,
        "GIB",
        new Address(
            43,
            "Meerenge von Gibraltar",
            "1",
            "00001",
            "Zwischen Marokko und Gibraltar",
            "Mittelmeer"
        )
    );
    differentHotel = new Hotel(
        8398,
        "Billig Hotel",
        1.99,
        "EUR",
        new Address(
            99,
            "Reeperbahn",
            "69",
            "12345",
            "Hamburg-St. Pauli",
            "Deutschland"
        )
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyHotel.equals(nullHotel));
    assertFalse(emptyHotel.equals(nullHotel));
  }

  @Test
  public void testEqualsMethodWithEmptyHotel() {
    Hotel copyEmptyHotel = new Hotel();
    assertDoesNotThrow(() -> emptyHotel.equals(copyEmptyHotel));
    assertTrue(emptyHotel.equals(copyEmptyHotel));
  }

  @Test
  public void testEqualsMethodWithIdenticalHotel() {
    assertDoesNotThrow(() -> hotel.equals(copyHotel));
    assertTrue(hotel.equals(copyHotel));
  }

  @Test
  public void testEqualsMethodWithDifferentHotel() {
    assertDoesNotThrow(() -> hotel.equals(differentHotel));
    assertFalse(hotel.equals(differentHotel));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> hotel.equals(obj));
    assertFalse(hotel.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Hotel excelsior = new Hotel(
        1,
        "Excelsior Hotel Ernst",
        500.00,
        "EUR",
        new Address(1, "Trankgasse", "1-5", "50667", "Köln", "Deutschland")
    );

    assertEquals("""
        Excelsior Hotel Ernst
        Trankgasse 1-5, 50667 Köln, Deutschland
        Price: 500.0 EUR""",
        excelsior.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalTraveller() {
    int expectedHashCode  = hotel.hashCode();
    int actualHashCode    = copyHotel.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentTraveller() {
    int unexpectedHashCode  = hotel.hashCode();
    int actualHashCode      = differentHotel.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
