package travel.travelagency.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookingTest {

  private static Booking nullBooking, emptyBooking, booking, copyBooking, differentBooking;
  private static Customer customer, copyCustomer, differentCustomer;
  private static Set<Trip> tripSet, copyTripSet, differentTripSet;
  private static Set<HotelBooking> hotelBookingSet, copyHotelBookingSet, differentHotelBookingSet;
  private static Set<FlightBooking> flightBookingSet, copyFlightBookingSet, differentFlightBookingSet;

  @BeforeAll()
  public static void initializeObjects() {
    initializeCustomers();
    initializeHotelBookingSets();
    initializeFlightBookingSets();
    initializeTripSets();
    initializeBookings();
  }

  public static void initializeCustomers() {
    customer = new Customer(
        123,
        "DE19582919875254589658745236512589",
        new PersonalData(
            43,
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 07, 17),
            new Address(4, "Street", "18", "93726", "Town", "Country")
        ),
        new Address(175,"Street","101a","19824","My Town","Disneyland" )
    );
    copyCustomer = new Customer(
        123,
        "DE19582919875254589658745236512589",
        new PersonalData(
            43,
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 07, 17),
            new Address(4, "Street", "18", "93726", "Town", "Country")
        ),
        new Address(175,"Street","101a","19824","My Town","Disneyland" )
    );
    differentCustomer = new Customer(
        497,
        "DE12457886135615487659132658132548",
        new PersonalData(
            98,
            "Scholz",
            "Olaf",
            "",
            LocalDate.of(1987, 11, 17),
            new Address(7, "Way", "9", "65958", "Stadt", "Osmanien")
        ),
        new Address(7, "Dwy", "18b", "HKL21", "Town", "Ivy")
    );
  }

  public static void initializeHotelBookingSets() {
    hotelBookingSet = new HashSet<>();
    hotelBookingSet.add(new HotelBooking(
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
    ));
    hotelBookingSet.add(new HotelBooking(
        136,
        new Hotel(
            1,
            "Excelsior Hotel Ernst",
            500.00,
            "EUR",
            new Address(1, "Trankgasse", "1-5", "50667", "Köln", "Deutschland")
        ),
        15
    ));
    copyHotelBookingSet = new HashSet<>();
    copyHotelBookingSet.add(new HotelBooking(
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
    ));
    copyHotelBookingSet.add(new HotelBooking(
        136,
        new Hotel(
            1,
            "Excelsior Hotel Ernst",
            500.00,
            "EUR",
            new Address(1, "Trankgasse", "1-5", "50667", "Köln", "Deutschland")
        ),
        15
    ));
    differentHotelBookingSet = new HashSet<>();
    differentHotelBookingSet.add(new HotelBooking(
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
    ));
    differentHotelBookingSet.add(new HotelBooking(
        136,
        new Hotel(
            1,
            "Excelsior Hotel Ernst",
            500.00,
            "EUR",
            new Address(1, "Trankgasse", "1-5", "50667", "Köln", "Deutschland")
        ),
        15
    ));
  }

  public static void initializeFlightBookingSets() {
    flightBookingSet = new HashSet<>();
    flightBookingSet.add(new FlightBooking(
        123,
        new Flight(
            1,
            new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 05, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 05, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99,
            "EUR"
        ),
        36
    ));
    flightBookingSet.add(new FlightBooking(
        136,
        new Flight(
            1,
            new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 05, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 05, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99,
            "EUR"
        ),
        19
    ));
    copyFlightBookingSet = new HashSet<>();
    copyFlightBookingSet.add(new FlightBooking(
        123,
        new Flight(
            1,
            new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 05, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 05, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99,
            "EUR"
        ),
        36
    ));
    copyFlightBookingSet.add(new FlightBooking(
        136,
        new Flight(
            1,
            new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 05, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 05, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99,
            "EUR"
        ),
        19
    ));
    differentFlightBookingSet = new HashSet<>();
    differentFlightBookingSet.add(new FlightBooking(
        82,
        new Flight(
            1,
            new FlightConnection(13, "DL", "0016", "ATL", "FRA"),
            LocalDate.of(2023, 07, 30),
            LocalTime.of(15, 5),
            "UTC-05:00",
            LocalDate.of(2023, 07, 31),
            LocalTime.of(7, 30, 38),
            "UTC+02:00",
            599.99,
            "EUR"
        ),
        4
    ));
    differentFlightBookingSet.add(new FlightBooking(
        136,
        new Flight(
            1,
            new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 05, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 05, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99,
            "EUR"
        ),
        19
    ));
  }

  public static void initializeTripSets() {
    tripSet = new HashSet<>();
    tripSet.add(new Trip(1, hotelBookingSet, flightBookingSet));
    copyTripSet = new HashSet<>();
    copyTripSet.add(new Trip(1, copyHotelBookingSet, copyFlightBookingSet));
    differentTripSet = new HashSet<>();
    differentTripSet.add(new Trip(3, differentHotelBookingSet, differentFlightBookingSet));
  }

  public static void initializeBookings() {
    nullBooking = null;
    emptyBooking = new Booking();
    booking = new Booking(1, new Customer(), tripSet);
    copyBooking = new Booking(1, new Customer(), copyTripSet);
    differentBooking = new Booking(12, new Customer(), differentTripSet);
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyBooking.equals(nullBooking));
    assertFalse(emptyBooking.equals(nullBooking));
  }

  @Test
  public void testEqualsMethodWithEmptyBooking() {
    Booking copyEmptyBooking = new Booking();
    assertDoesNotThrow(() -> emptyBooking.equals(copyEmptyBooking));
    assertTrue(emptyBooking.equals(copyEmptyBooking));
  }

  @Test
  public void testEqualsMethodWithIdenticalBooking() {
    assertDoesNotThrow(() -> booking.equals(copyBooking));
    assertTrue(booking.equals(copyBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentBooking() {
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
    Booking testedBooking = new Booking(1234, customer, tripSet);

    String expectedResult =
        "Booking no. : 1234\n" +
        "Customer:\n" +
            customer.toString() + '\n' +
        "Trips:\n" +
            tripSet.toString();

    assertEquals(expectedResult, testedBooking.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalBooking() {
    int expectedHashCode  = booking.hashCode();
    int actualHashCode    = copyBooking.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentBooking() {
    int unexpectedHashCode  = booking.hashCode();
    int actualHashCode      = differentBooking.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}