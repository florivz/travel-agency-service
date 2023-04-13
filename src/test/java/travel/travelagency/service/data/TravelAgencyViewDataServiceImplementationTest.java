package travel.travelagency.service.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import org.mockito.Mockito;
import travel.travelagency.entities.*;
import java.util.List;
import java.util.LinkedList;

public class TravelAgencyViewDataServiceImplementationTest {

  private EntityManager EM;

  @BeforeEach
  public void createMockedEntityManager() {
    //Default typed query without filters
    TypedQuery<Booking> allBookings = getTypedQuery(createBookingList(null, null, null));

    //Typed query after filtering by parameter 'bookingID'
    TypedQuery<Booking> byId1Bookings = getTypedQuery(createBookingList(1, null, null));
    Mockito.when(allBookings.setParameter(Booking.BOOKING_ID, 1)).
        thenReturn(byId1Bookings);

    TypedQuery<Booking> byCustomerId1Bookings = getTypedQuery(createBookingList(null, 1, null));
    Mockito.when(allBookings.setParameter(Booking.CUSTOMER_ID, 1)).
        thenReturn(byCustomerId1Bookings);

    TypedQuery<Booking> byCustomerNameBookings = getTypedQuery(createBookingList(null, null, "Merkel"));
    Mockito.when(allBookings.setParameter(Booking.CUSTOMER_NAME, "Merkel")).
        thenReturn(byCustomerNameBookings);

    TypedQuery<Booking> byId3Bookings = getTypedQuery(createBookingList(3, null, null));
    TypedQuery<Booking> byIdAndCustomerId2Bookings = getTypedQuery(createBookingList(3, 2, null));
    TypedQuery<Booking> byIdAndCIDAndCNameBookings = getTypedQuery(createBookingList(3,2, "Scholz"));
    Mockito.when(byIdAndCustomerId2Bookings.setParameter(Booking.CUSTOMER_NAME, "Scholz"))
        .thenReturn(byIdAndCIDAndCNameBookings);
    Mockito.when(byId3Bookings.setParameter(Booking.CUSTOMER_ID, 2))
        .thenReturn(byIdAndCustomerId2Bookings);
    Mockito.when(allBookings.setParameter(Booking.BOOKING_ID, 3))
        .thenReturn(byId3Bookings);


    EM = Mockito.mock(EntityManager.class);
    Mockito.when(EM.createNamedQuery(Booking.FIND_WITH_FILTERS, Booking.class))
        .thenReturn(allBookings);
  }

  private TypedQuery<Booking> getTypedQuery(List<Booking> resultList) {
    TypedQuery<Booking> typedQuery = Mockito.mock(TypedQuery.class);
    Mockito.when(typedQuery.getResultList()).thenReturn(resultList);
    Mockito.when(typedQuery.setParameter(Booking.BOOKING_ID, null)).thenReturn(typedQuery);
    Mockito.when(typedQuery.setParameter(Booking.CUSTOMER_ID, null)).thenReturn(typedQuery);
    Mockito.when(typedQuery.setParameter(Booking.CUSTOMER_NAME, null)).thenReturn((typedQuery));
    return typedQuery;
  }

  private List<Booking> createBookingList(Integer bookingID, Integer customerID, String customerName) {
    List<Booking> bookingList = new LinkedList<>();
    bookingList.add(new Booking(1, getCustomer(1), new HashSet<>()));
    bookingList.add(new Booking(2, getCustomer(1), new HashSet<>()));
    bookingList.add(new Booking(3, getCustomer(2), new HashSet<>()));
    for(int i = 0; i < bookingList.size(); i++) {
      if(
        bookingID != null && !bookingID.equals(bookingList.get(i).getId())
        || customerID != null && !customerID.equals(bookingList.get(i).getCustomer().getId())
        || customerName != null && !customerName.equals(bookingList.get(i).getCustomer().getPersonalData().getLastName())
      ) {
        bookingList.remove(i--);
      }
    }
    return bookingList;
  }

  private Customer getCustomer(int customerID) {
    return switch (customerID) {
      case 1 -> new Customer(customerID, "DE19582919875254589658745236512589",
          new PersonalData(
              43,
              "Merkel",
              "Angela",
              "Dorothea",
              LocalDate.of(1954, 7, 17),
              new Address(4, "Street", "18", "93726", "Town", "Country")
          ),
          new Address(175,"Street","101a","19824","My Town","Disneyland" ));
      case 2 -> new Customer(
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
      default -> null;
    };
  }

  private HotelBooking getHotelBooking(int hotelBookingID) {
    return switch(hotelBookingID) {
      case 1 -> new HotelBooking(
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
        204,
        12
      );
      case 2 -> new HotelBooking(
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
        29,
        3
      );
      default -> null;
    };
  }

  private FlightBooking getFlightBooking(int flightBookingID) {
    return switch (flightBookingID) {
      case 1 -> new FlightBooking(
        123,
        new Flight(
          1,
          new FlightConnection(12, "DL", "0015", "FRA", "ATL"),
          LocalDate.of(2023, 5, 14),
          LocalTime.of(11, 30, 20),
          "UTC+02:00",
          LocalDate.of(2023, 5, 14),
          LocalTime.of(16, 15),
          "UTC-05:00",
          299.99,
          "EUR"
        ),
        36
      );
      case 2 -> new FlightBooking(
        82,
        new Flight(
          1,
          new FlightConnection(13, "DL", "0016", "ATL", "FRA"),
          LocalDate.of(2023, 7, 30),
          LocalTime.of(15, 5),
          "UTC-05:00",
          LocalDate.of(2023, 7, 31),
          LocalTime.of(7, 30, 38),
          "UTC+02:00",
          599.99,
          "EUR"
        ),
        4
      );
      default -> new FlightBooking();
    };
  }

  @Test
  public void testGetBookingsWithNull() {
    List<Booking> expectedBookingList = createBookingList(null, null, null);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, null, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithId() {
    List<Booking> expectedBookingList = createBookingList(1, null, null);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(1, null, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithCustomerId() {
    List<Booking> expectedBookingList = createBookingList(null, 1, null);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, 1, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithCustomerName() {
    List<Booking> expectedBookingList = createBookingList(null, null, "Merkel");

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, null, "Merkel");

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithParameters() {
    List<Booking> expectedBookingList = createBookingList(3, 2, "Scholz");

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(3, 2, "Scholz");

    assertEquals(expectedBookingList, actualBookingList);
  }

  /**
   * This method tests the <code>getTrips(Booking booking)</code> method for a booking
   * equal to <code>null</code>.
   * The expected <code>List</code> object shall be empty.
   */
  @Test
  public void testGetTripsWithNull() {
    List<Trip> expectedTripList = new LinkedList<>();

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Trip> actualTripList = service.getTrips(null);

    assertEquals(expectedTripList, actualTripList);
  }

  /**
   * This method tests the <code>getTrips(Booking booking)</code> method for a booking
   * whose set of trips is equal to <code>null</code>.
   * The expected <code>List</code> object shall be empty.
   */
  @Test
  public void testGetTripsWithNullTripSet() {
    List<Trip> expectedTripList = new LinkedList<>();

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Trip> actualTripList = service.getTrips(new Booking());

    assertEquals(expectedTripList, actualTripList);
  }

  /**
   * This method tests the <code>getTrips(Booking booking)</code> method for a booking
   * with at least one trip.
   * The expected <code>List</code> object shall contain the trips
   * belonging to the booking in question.
   */
  @Test
  public void testGetTripsWithTripSet() {
    fail();
  }

  /**
   * This method tests the <code>getHotelBookings(Trip trip)</code> method for a trip
   * equal to <code>null</code>.
   * The expected <code>List</code> object shall be empty.
   */
  @Test
  public void testGetHotelBookingsWithNull() {
    List<HotelBooking> expectedHotelBookingList = new LinkedList<>();

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<HotelBooking> actualFlightBookingList = service.getHotelBookings(null);

    assertEquals(expectedHotelBookingList, actualFlightBookingList);
  }

  /**
   * This method tests the <code>getHotelBookings(Trip trip)</code> method for a trip
   * whose set of hotel bookings is equal to <code>null</code>.
   * The expected <code>List</code> object shall be empty.
   */
  @Test
  public void testGetHotelBookingsWithNullHotelBookingSet() {
    List<HotelBooking> expectedHotelBookingList = new LinkedList<>();

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<HotelBooking> actualFlightBookingList = service.getHotelBookings(new Trip());

    assertEquals(expectedHotelBookingList, actualFlightBookingList);
  }

  /**
   * This method tests the <code>getHotelBookings(Trip trip)</code> method for a trip
   * with at least one hotel booking.
   * The expected <code>List</code> object shall contain the hotel bookings
   * belonging to the trip in question.
   */
  @Test
  public void testGetHotelBookingsWitHotelBookingSet() {
    List<HotelBooking> expectedHotelBookingList = List.of(
            this.getHotelBooking(1),
            this.getHotelBooking(2)
    );

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(this.EM);
    List<HotelBooking> actualHotelBookingList = service.getHotelBookings(
        new Trip(293, Set.of(this.getHotelBooking(1), this.getHotelBooking(2)), null)
    );

    Assertions.assertEquals(Set.of(expectedHotelBookingList), Set.of(actualHotelBookingList));
  }

  /**
   * This method tests the <code>getFlightBookings(Trip trip)</code> method for a trip
   * equal to <code>null</code>.
   * The expected <code>List</code> object shall be empty.
   */
  @Test
  public void testGetFlightBookingsWithNull() {
    List<FlightBooking> expectedFlightBookingList = new LinkedList<>();

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<FlightBooking> actualFlightBookingList = service.getFlightBookings(null);

    assertEquals(expectedFlightBookingList, actualFlightBookingList);
  }

  /**
   * This method tests the <code>getFlightBookings(Trip trip)</code> method for a trip
   * whose set of flight bookings is equal to <code>null</code>.
   * The expected <code>List</code> object shall be empty.
   */
  @Test
  public void testGetFlightBookingsWithNullFlightBookingSet() {
    List<FlightBooking> expectedFlightBookingList = new LinkedList<>();

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<FlightBooking> actualFlightBookingList = service.getFlightBookings(new Trip());

    assertEquals(expectedFlightBookingList, actualFlightBookingList);
  }

  /**
   * This method tests the <code>getFlightBookings(Trip trip)</code> method for a trip
   * with at least one flight booking.
   * The expected <code>List</code> object shall contain the flight bookings
   * belonging to the trip in question.
   */
  @Test
  public void testGetFlightBookingsWithFlightBookingSet() {
    List<FlightBooking> expectedFlightBookingList = List.of(
        this.getFlightBooking(1),
        this.getFlightBooking(2)
    );

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(this.EM);
    List<FlightBooking> actualFlightBookingList = service.getFlightBookings(
        new Trip(293, null, Set.of(this.getFlightBooking(1), this.getFlightBooking(2)))
    );

    Assertions.assertEquals(Set.of(expectedFlightBookingList), Set.of(actualFlightBookingList));
  }

}