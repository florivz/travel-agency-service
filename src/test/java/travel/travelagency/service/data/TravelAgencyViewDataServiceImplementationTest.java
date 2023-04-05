package travel.travelagency.service.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.HashSet;
import javax.persistence.TypedQuery;
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
    TypedQuery<Booking> allBookings = getTypedQuery(createBookingList(null, null));

    //Typed query after filtering by parameter 'bookingID'
    TypedQuery<Booking> byId1Bookings = getTypedQuery(createBookingList(1, null));
    Mockito.when(allBookings.setParameter(Booking.BOOKING_ID, 1)).
        thenReturn(byId1Bookings);

    TypedQuery<Booking> byCustomerId1Bookings = getTypedQuery(createBookingList(null, 1));
    Mockito.when(allBookings.setParameter(Booking.CUSTOMER_ID, 1)).
        thenReturn(byCustomerId1Bookings);

    TypedQuery<Booking> byId3Bookings = getTypedQuery(createBookingList(3, null));
    TypedQuery<Booking> byId3AndCustomerId2Bookings = getTypedQuery(createBookingList(3, 2));
    Mockito.when(byId3Bookings.setParameter(Booking.CUSTOMER_ID, 2))
        .thenReturn(byId3AndCustomerId2Bookings);
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
    return typedQuery;
  }

  private List<Booking> createBookingList(Integer bookingID, Integer customerID) {
    List<Booking> bookingList = new LinkedList<>();
    bookingList.add(new Booking(1, getCustomer(1), new HashSet<>()));
    bookingList.add(new Booking(2, getCustomer(1), new HashSet<>()));
    bookingList.add(new Booking(3, getCustomer(2), new HashSet<>()));
    for(int i = 0; i < bookingList.size(); i++) {
      if(
        bookingID != null && !bookingID.equals(bookingList.get(i).getId())
        || customerID != null && !customerID.equals(bookingList.get(i).getCustomer().getId())
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

  @Test
  public void testGetBookingsWithNull() {
    List<Booking> expectedBookingList = createBookingList(null, null);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithId() {
    List<Booking> expectedBookingList = createBookingList(1, null);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(1, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithCustomerId() {
    List<Booking> expectedBookingList = createBookingList(null, 1);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, 1);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithIdAndCustomerId() {
    List<Booking> expectedBookingList = createBookingList(3, 2);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(3, 2);

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
    fail();
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
    fail();
  }

}