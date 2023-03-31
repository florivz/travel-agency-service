package travel.travelagency.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelBookingTest {

  private static HotelBooking nullBooking, emptyBooking, booking, copyBooking, differentBooking;

  @BeforeAll
  public static void initializeHotelBookings() {
    nullBooking = null;
    emptyBooking = new HotelBooking();
    booking = new HotelBooking(
        123,
        new Hotel(
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
        ),
        204
    );
    copyBooking = new HotelBooking(
        123,
        new Hotel(
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
        ),
        204
    );
    differentBooking = new HotelBooking(
        938,
        new Hotel(
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
        ),
        29
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyBooking.equals(nullBooking));
    assertFalse(emptyBooking.equals(nullBooking));
  }

  @Test
  public void testEqualsMethodWithEmptyHotelBooking() {
    HotelBooking copyEmptyBooking = new HotelBooking();
    assertDoesNotThrow(() -> emptyBooking.equals(copyEmptyBooking));
    assertTrue(emptyBooking.equals(copyEmptyBooking));
  }

  @Test
  public void testEqualsMethodWithIdenticalHotelBooking() {
    assertDoesNotThrow(() -> booking.equals(copyBooking));
    assertTrue(booking.equals(copyBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentHotelBooking() {
    assertDoesNotThrow(() -> booking.equals(differentBooking));
    assertFalse(booking.equals(differentBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> booking.equals(obj));
    assertFalse(booking.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    HotelBooking testedHotelBooking = new HotelBooking(
      136,
      new Hotel(
      1,
      "Excelsior Hotel Ernst",
      500.00,
      "EUR",
        new Address(1, "Trankgasse", "1-5", "50667", "Köln", "Deutschland")
      ),
      15
    );

    String expectedResult = """
        Excelsior Hotel Ernst
        Trankgasse 1-5, 50667 Köln, Deutschland
        Price: 500.0 EUR
        Guests: 15""";

    assertEquals(expectedResult, testedHotelBooking.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalHotelBooking() {
    int expectedHashCode  = booking.hashCode();
    int actualHashCode    = copyBooking.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentHotelBooking() {
    int unexpectedHashCode  = booking.hashCode();
    int actualHashCode      = differentBooking.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
