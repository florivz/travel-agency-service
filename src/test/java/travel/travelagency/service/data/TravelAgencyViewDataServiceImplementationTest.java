package travel.travelagency.service.data;

import java.util.*;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import org.mockito.Mockito;
import travel.travelagency.entities.*;

import static org.junit.jupiter.api.Assertions.*;

public class TravelAgencyViewDataServiceImplementationTest {

  /**
   * This private inner class is used to set an id for a <code>Customer</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestCustomer extends Customer {

    private Integer id;

    public TestCustomer(Integer id, String lastName) {
      this.setPersonalData(new PersonalData());
      this.getPersonalData().setLastName(lastName);
      this.setId(id);
    }

    public void setId(Integer id) { this.id = id; }

    @Override
    public Integer getId() { return id; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      Customer customer = (Customer) obj;
      return id.equals(customer.getId()) && getPersonalData().equals(customer.getPersonalData());
    }

  }

  /**
   * This private inner class is used to set an id for a <code>Booking</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestBooking extends Booking {

    private Integer id;

    public TestBooking(Integer id, Integer customerID, String lastName, Set<Trip> trips) {
      this.id = id;
      this.setCustomer(new TestCustomer(customerID, lastName));
      this.setTripSet(trips);
    }

    public TestBooking(Integer id, Integer customerID, String lastName) {
      this(id, customerID, lastName, null);
    }

    public void setId(int id) {
      this.id = id;
    }

    @Override
    public Integer getId() { return id; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      Booking booking = (Booking) obj;
      return
        this.getId().equals(booking.getId()) &&
        this.getCustomer().equals(booking.getCustomer());
    }

  }

  /**
   * This private inner class is used to set an id for a <code>Trip</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestTrip extends Trip {

    private Integer id;

    public TestTrip(Integer id, Set<HotelBooking> hotelBookings, Set<FlightBooking> flightBookings) {
      this.id = id;
      this.setHotelBookingSet(hotelBookings);
      this.setFlightBookingSet(flightBookings);
    }

    public TestTrip(Integer id) {
      this(id, null, null);
    }

    public void setId(int id) {
      this.id = id;
    }

    @Override
    public Integer getId() { return id; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      Trip trip = (Trip) obj;
      return
          this.getId().equals(trip.getId()) &&
          ((this.getHotelBookingSet() == null && trip.getHotelBookingSet() == null)
              || this.getHotelBookingSet().equals(trip.getHotelBookingSet())) &&
          ((this.getFlightBookingSet() == null && trip.getFlightBookingSet() == null)
              || this.getFlightBookingSet().equals(trip.getFlightBookingSet()));
    }

  }

  /**
   * This private inner class is used to set an id for a <code>HotelBooking</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestHotelBooking extends HotelBooking {

    private Integer id;

    public TestHotelBooking(Integer id) {
      this.id = id;
    }

    public void setId(int id) {
      this.id = id;
    }

    @Override
    public Integer getId() { return id; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      HotelBooking trip = (HotelBooking) obj;
      return this.getId().equals(trip.getId());
    }

  }

  /**
   * This private inner class is used to set an id for a <code>FlightBooking</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestFlightBooking extends FlightBooking {

    private Integer id;

    public TestFlightBooking(Integer id) {
      this.id = id;
    }

    public void setId(int id) {
      this.id = id;
    }

    @Override
    public Integer getId() { return id; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      FlightBooking trip = (FlightBooking) obj;
      return this.getId().equals(trip.getId());
    }

  }

  /**
   * This private method creates a mocked <code>EntityManager</code> object that can be used for testing the
   * <code>getBookings(...) : List<Booking></code> method.
   * @param allBookings initial list of all bookings known to the entity manager
   * @param customerID customer that can be filtered by
   * @param customerName customer that can be filtered by
   * @return mocked <code>EntityManager</code> object
   */
  private EntityManager createBookingEntityManager(List<Booking> allBookings, Integer customerID, String customerName) {
    List<Booking> bookingsFilteredByCustomerID = allBookings.stream().filter(
            e -> e.getCustomer().getId().equals(customerID) || customerID == null
    ).toList();

    List<Booking> bookingsFilteredByCustomerIDAndCustomerName = bookingsFilteredByCustomerID.stream().filter(
            e -> e.getCustomer().getPersonalData().getLastName().equals(customerName) || customerName == null
    ).toList();

    TypedQuery<Booking> customerNameQuery = createBookingQuery(bookingsFilteredByCustomerIDAndCustomerName);

    TypedQuery<Booking> customerIdQuery = createBookingQuery(bookingsFilteredByCustomerID);
    Mockito.when(customerIdQuery.setParameter(Booking.CUSTOMER_LASTNAME, customerName)).thenReturn(customerNameQuery);

    TypedQuery<Booking> allBookingsQuery = createBookingQuery(allBookings);
    Mockito.when(allBookingsQuery.setParameter(Booking.CUSTOMER_ID, customerID)).thenReturn(customerIdQuery);

    EntityManager entityManager = Mockito.mock(EntityManager.class);
    Mockito.when(entityManager.createNamedQuery(Booking.FIND_WITH_FILTERS, Booking.class))
            .thenReturn(allBookingsQuery);

    return entityManager;
  }

  private TypedQuery<Booking> createBookingQuery(List<Booking> resultList) {
    TypedQuery<Booking> typedQuery = Mockito.mock(TypedQuery.class);
    Mockito.when(typedQuery.setParameter(Booking.CUSTOMER_ID, null)).thenReturn(typedQuery);
    Mockito.when(typedQuery.setParameter(Booking.CUSTOMER_LASTNAME, null)).thenReturn(typedQuery);
    Mockito.when(typedQuery.getResultList()).thenReturn(resultList);
    return typedQuery;
  }

  private List<Booking> createBookingsList() {
    return List.of(
      new TestBooking(1, 1, "Maier"),
      new TestBooking(2, 1, "Maier"),
      new TestBooking(3, 1, "Maier"),
      new TestBooking(4, 2, "Schmitz"),
      new TestBooking(5, 2, "Schmitz"),
      new TestBooking(6, 3, "Boden"),
      new TestBooking(7,4,"Weiß"),
      new TestBooking(8,4,"Weiß"),
      new TestBooking(9, 5, "Veitz", Set.of(
          new TestTrip(1), new TestTrip(2), new TestTrip(3)
      )),
      new TestBooking(10, 5, "Veitz", Set.of(
          new TestTrip(4), new TestTrip(5)
      )),
      new TestBooking(11, 5, "Veitz", Set.of(
          new TestTrip(2), new TestTrip(4)
      ))
    );
  }

  /**
   * This private method creates a mocked <code>EntityManager</code> object that mocked the method
   * <code>find(Booking.class, bookingID) : Booking</code> for the booking id provided.
   * @param allBookings initial list of all bookings known to the entity manager
   * @param bookingID booking that can be found
   * @return mocked <code>EntityManager</code> object
   */
  private EntityManager createTripEntityManager(List<Booking> allBookings, Integer bookingID) {
    EntityManager entityManager = Mockito.mock(EntityManager.class);
    for(Booking booking : allBookings) {
      if(booking.getId().equals(bookingID))
        Mockito.when(entityManager.find(Booking.class, bookingID)).thenReturn(booking);
    }
    return entityManager;
  }

  /**
   * This private method creates a mocked <code>EntityManager</code> object that mocked the method
   * <code>find(Trip.class, tripID) : Trip</code> for the booking id provided.
   * @param allTrips initial list of all trips known to the entity manager
   * @param tripID trip that can be found
   * @return mocked <code>EntityManager</code> object
   */
  private EntityManager createHotelAndFlightBookingEntityManager(List<Trip> allTrips, Integer tripID) {
    EntityManager entityManager = Mockito.mock(EntityManager.class);
    for(Trip trip : allTrips) {
      if(trip.getId().equals(tripID))
        Mockito.when(entityManager.find(Trip.class, tripID)).thenReturn(trip);
    }
    return entityManager;
  }

  private List<Trip> createTripList() {
    return List.of(
      new TestTrip(1,
        Set.of(new TestHotelBooking(1), new TestHotelBooking(2), new TestHotelBooking(3)),
        Set.of(new TestFlightBooking(1), new TestFlightBooking(2))
      ),
      new TestTrip(2,
              Set.of(new TestHotelBooking(4), new TestHotelBooking(5)),
              Set.of(new TestFlightBooking(3), new TestFlightBooking(4), new TestFlightBooking(5))
      ),
      new TestTrip(3)
    );
  }

  /**
   * This method tests the getBookings(int customerID) using a mocked entity manager
   */
  @Test
  public void testGetBookingsMethodWithValidCustomerID() {
    Integer customerID = 1;

    List<Booking> allBookings = createBookingsList();

    List<Booking> expectedBookings = allBookings.stream().filter(e -> e.getCustomer().getId() == customerID).toList();

    EntityManager entityManager = createBookingEntityManager(allBookings, customerID, null);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Booking> actualBookings = service.getBookings(customerID);

    assertEquals(expectedBookings, actualBookings);
  }

  @Test
  public void testGetBookingsMethodWithCustomerLastName() {
    String customerName = "Schmitz";

    List<Booking> allBookings = createBookingsList();

    List<Booking> expectedBookings = allBookings.stream().filter(
        e -> e.getCustomer().getPersonalData().getLastName().equals(customerName)
    ).toList();

    EntityManager entityManager = createBookingEntityManager(allBookings, null, customerName);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Booking> actualBookings = service.getBookings(customerName);

    assertThrows(RuntimeException.class, () -> service.getBookings(null));
    assertEquals(expectedBookings, actualBookings);
  }

  @Test
  public void testGetBookingsMethodWithCustomerIdAndLastName() {
    Integer customerID = 4;
    String customerName = "Weiß";

    List<Booking> allBookings = createBookingsList();

    List<Booking> expectedBookings = allBookings.stream().filter(
            e ->  e.getCustomer().getId().equals(customerID) &&
                  e.getCustomer().getPersonalData().getLastName().equals(customerName)
    ).toList();

    EntityManager entityManager = createBookingEntityManager(allBookings, customerID, customerName);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Booking> actualBookings = service.getBookings(customerID, customerName);

    assertThrows(RuntimeException.class, () -> service.getBookings(customerID, null));
    assertEquals(expectedBookings, actualBookings);
  }

  @Test
  public void testGetBookingMethodWithValidId() {
    fail();
  }

  @Test
  public void testGetBookingMethodWithInvalidId() {
    fail();
  }

  /**
   * This method tests the <code>getTrips(int bookingID)</code> method with a valid booking ID and a
   * corresponding booking that has at least one trip allocated.
   */
  @Test
  public void testGetTripsMethodWithValidId() {
    int bookingID = 9;

    List<Booking> allBookings = createBookingsList();

    List<Trip> expectedTrips = allBookings.stream().filter(
      e -> e.getId().equals(bookingID)
    ).toList().get(0).getTripSet().stream().toList();

    EntityManager entityManager = createTripEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Trip> actualTrips = service.getTrips(bookingID);

    assertEquals(expectedTrips, actualTrips);
  }

  /**
   * This method tests the <code>getTrips(int bookingID)</code> method with an invalid booking ID
   * to which no booking is known to the entity manager.
   */
  @Test
  public void testGetTripsMethodWithInvalidId() {
    int bookingID = 0;

    List<Booking> allBookings = createBookingsList();

    List<Trip> expectedTrips = null;

    EntityManager entityManager = createTripEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Trip> actualTrips = service.getTrips(bookingID);

    assertEquals(expectedTrips, actualTrips);
  }

  /**
   * This method tests the <code>getTrips(int bookingID)</code> method with a valid booking ID and a
   * corresponding booking that has no trips allocated.
   */
  @Test
  public void testGetTripsMethodWithoutTrips() {
    int bookingID = 1;

    List<Booking> allBookings = createBookingsList();

    List<Trip> expectedTrips = new LinkedList<>();

    EntityManager entityManager = createTripEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Trip> actualTrips = service.getTrips(bookingID);

    assertEquals(expectedTrips, actualTrips);
  }

  /**
   * This method tests the <code>getHotelBookings(int tripID)</code> method with a valid trip ID and a
   * corresponding trip that has at least one hotel booking allocated.
   */
  @Test
  public void testGetHotelBookingsMethodWithValidId() {
    int tripID = 1;

    List<Trip> allTrips = createTripList();

    List<HotelBooking> expectedTrips = allTrips.stream().filter(
            e -> e.getId().equals(tripID)
    ).toList().get(0).getHotelBookingSet().stream().toList();

    EntityManager entityManager = createHotelAndFlightBookingEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<HotelBooking> actualTrips = service.getHotelBookings(tripID);

    assertEquals(expectedTrips, actualTrips);
  }

  /**
   * This method tests the <code>getHotelBookings(int tripID)</code> method with an invalid trip ID
   * to which no trip is known to the entity manager.
   */
  @Test
  public void testGetHotelBookingsMethodWithInvalidId() {
    int tripID = 0;

    List<Trip> allTrips = createTripList();

    List<HotelBooking> expectedTrips = null;

    EntityManager entityManager = createHotelAndFlightBookingEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<HotelBooking> actualTrips = service.getHotelBookings(tripID);

    assertEquals(expectedTrips, actualTrips);
  }

  /**
   * This method tests the <code>getHotelBookings(int tripID)</code> method with a valid trip ID and a
   * corresponding trip that has no hotel bookings allocated.
   */
  @Test
  public void testGetHotelBookingsMethodWithoutHotelBookings() {
    int tripID = 3;

    List<Trip> allTrips = createTripList();

    List<HotelBooking> expectedTrips = new LinkedList<>();

    EntityManager entityManager = createHotelAndFlightBookingEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<HotelBooking> actualTrips = service.getHotelBookings(tripID);

    assertEquals(expectedTrips, actualTrips);
  }

}