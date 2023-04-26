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

    private final Integer ID;

    public TestCustomer(Integer id, String lastName) {
      this.setPersonalData(new PersonalData());
      this.getPersonalData().setLastName(lastName);
      this.ID = id;
    }

    @Override
    public Integer getID() { return ID; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      Customer customer = (Customer) obj;
      return ID.equals(customer.getID()) && getPersonalData().equals(customer.getPersonalData());
    }

  }

  /**
   * This private inner class is used to set an id for a <code>Booking</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestBooking extends Booking {

    private final Integer ID;

    public TestBooking(Integer id, Integer customerID, String lastName, Set<Trip> trips) {
      this.ID = id;
      this.setCustomer(new TestCustomer(customerID, lastName));
      this.setTripSet(trips);
    }

    public TestBooking(Integer id, Integer customerID, String lastName) {
      this(id, customerID, lastName, null);
    }

    @Override
    public Integer getID() { return ID; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      Booking booking = (Booking) obj;
      return
        this.getID().equals(booking.getID()) &&
        this.getCustomer().equals(booking.getCustomer());
    }

  }

  /**
   * This private inner class is used to set an id for a <code>Trip</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestTrip extends Trip {

    private final Integer ID;

    public TestTrip(Integer id, Set<HotelBooking> hotelBookings, Set<FlightBooking> flightBookings) {
      this.ID = id;
      this.setHotelBookingSet(hotelBookings);
      this.setFlightBookingSet(flightBookings);
    }

    public TestTrip(Integer id) {
      this(id, null, null);
    }

    @Override
    public Integer getID() { return ID; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      Trip trip = (Trip) obj;
      return
          this.getID().equals(trip.getID()) &&
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

    private final Integer ID;

    public TestHotelBooking(Integer id) {
      this.ID = id;
    }

    @Override
    public Integer getID() { return ID; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      HotelBooking trip = (HotelBooking) obj;
      return this.getID().equals(trip.getID());
    }

  }

  /**
   * This private inner class is used to set an id for a <code>FlightBooking</code> object.
   * This class is only necessary for unit testing purposes.
   */
  private static class TestFlightBooking extends FlightBooking {

    private final Integer ID;

    public TestFlightBooking(Integer id) {
      this.ID = id;
    }

    @Override
    public Integer getID() { return ID; }

    @Override
    public boolean equals(Object obj) {
      if(! this.getClass().equals(obj.getClass()))
        return false;
      FlightBooking trip = (FlightBooking) obj;
      return this.getID().equals(trip.getID());
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
  private EntityManager createBookingsEntityManager(List<Booking> allBookings, Integer customerID, String customerName) {
    List<Booking> bookingsFilteredByCustomerID = allBookings.stream().filter(
            e -> e.getCustomer().getID().equals(customerID) || customerID == null
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
  private EntityManager createBookingEntityManager(List<Booking> allBookings, Integer bookingID) {
    EntityManager entityManager = Mockito.mock(EntityManager.class);
    for(Booking booking : allBookings) {
      if(booking.getID().equals(bookingID))
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
  private EntityManager createTripEntityManager(List<Trip> allTrips, Integer tripID) {
    EntityManager entityManager = Mockito.mock(EntityManager.class);
    for(Trip trip : allTrips) {
      if(trip.getID().equals(tripID))
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

    List<Booking> expectedBookings = allBookings.stream().filter(e -> e.getCustomer().getID().equals(customerID)).toList();

    EntityManager entityManager = createBookingsEntityManager(allBookings, customerID, null);

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

    EntityManager entityManager = createBookingsEntityManager(allBookings, null, customerName);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Booking> actualBookings = service.getBookings(customerName);

    assertThrows(RuntimeException.class, () -> service.getBookings(null));
    assertEquals(expectedBookings, actualBookings);
  }

  @Test
  public void testGetBookingsMethodWithCustomerIdAndLastName() {
    int customerID = 4;
    String customerName = "Weiß";

    List<Booking> allBookings = createBookingsList();

    List<Booking> expectedBookings = allBookings.stream().filter(
            e ->  e.getCustomer().getID().equals(customerID) &&
                  e.getCustomer().getPersonalData().getLastName().equals(customerName)
    ).toList();

    EntityManager entityManager = createBookingsEntityManager(allBookings, customerID, customerName);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<Booking> actualBookings = service.getBookings(customerID, customerName);

    assertThrows(RuntimeException.class, () -> service.getBookings(customerID, null));
    assertEquals(expectedBookings, actualBookings);
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager.
   */
  @Test
  public void testGetBookingMethodWithValidId() {
    int bookingID = 1;

    List<Booking> allBookings = createBookingsList();

    Booking expectedBooking = allBookings.stream().filter(
        e -> e.getID().equals(bookingID)
    ).toList().get(0);

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    Booking actualBooking = service.getBooking(bookingID);

    assertEquals(expectedBooking, actualBooking);
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with an invalid booking ID to which no
   * corresponding <code>Booking</code> object is known to the entity manager.
   */
  @Test
  public void testGetBookingMethodWithInvalidId() {
    int bookingID = 0;

    List<Booking> allBookings = createBookingsList();

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNull(service.getBooking(bookingID));
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager, and a customer id that does
   * apply to the <code>Booking</code> object in question
   */
  @Test
  public void testGetBookingMethodWithValidBookingAndCustomerID() {
    int bookingID = 2;
    int customerID = 1;

    List<Booking> allBookings = createBookingsList();

    Booking expectedBooking = allBookings.stream().filter(
            e -> e.getID().equals(bookingID) && e.getCustomer().getID().equals(customerID)
    ).toList().get(0);

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    Booking actualBooking = service.getBooking(bookingID, customerID);

    assertEquals(expectedBooking, actualBooking);
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager, but a customer id that does not
   * apply to the <code>Booking</code> object in question
   */
  @Test
  public void testGetBookingMethodWithInvalidCustomerID() {
    int bookingID = 2;
    int customerID = 2;

    List<Booking> allBookings = createBookingsList();

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNotNull(service.getBooking(bookingID));
    assertNull(service.getBooking(bookingID, customerID));
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager, and a customer id that does
   * apply to the <code>Booking</code> object in question
   */
  @Test
  public void testGetBookingMethodWithValidBookingAndCustomerName() {
    int bookingID = 5;
    String customerName = "Schmitz";

    List<Booking> allBookings = createBookingsList();

    Booking expectedBooking = allBookings.stream().filter(
            e -> e.getID().equals(bookingID) && e.getCustomer().getPersonalData().getLastName().equals(customerName)
    ).toList().get(0);

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    Booking actualBooking = service.getBooking(bookingID, customerName);

    assertEquals(expectedBooking, actualBooking);
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager, but a customer id that does not
   * apply to the <code>Booking</code> object in question
   */
  @Test
  public void testGetBookingMethodWithInvalidCustomerName() {
    int bookingID = 6;
    String customerName = "Maier";

    List<Booking> allBookings = createBookingsList();

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNotNull(service.getBooking(bookingID));
    assertNull(service.getBooking(bookingID, customerName));
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager, and a customer id that does
   * apply to the <code>Booking</code> object in question
   */
  @Test
  public void testGetBookingMethodWithValidBookingCustomerIDAndName() {
    int bookingID = 7;
    int customerID = 4;
    String customerName = "Weiß";

    List<Booking> allBookings = createBookingsList();

    Booking expectedBooking = allBookings.stream().filter(
            e -> e.getID().equals(bookingID)
                    && e.getCustomer().getID().equals(customerID)
                    && e.getCustomer().getPersonalData().getLastName().equals(customerName)
    ).toList().get(0);

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    Booking actualBooking = service.getBooking(bookingID, customerID, customerName);

    assertEquals(expectedBooking, actualBooking);
  }

  /**
   * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
   * corresponding <code>Booking</code> object is known to the entity manager, but a customer id that does not
   * apply to the <code>Booking</code> object in question
   */
  @Test
  public void testGetBookingMethodWithInvalidCustomerIDAndName() {
    int bookingID = 6;
    int validCustomerID = 3;
    int invalidCustomerID = 4;
    String validCustomerName = "Boden";
    String invalidCustomerName = "Weiß";

    List<Booking> allBookings = createBookingsList();

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNotNull(service.getBooking(bookingID));

    assertNotNull(service.getBooking(bookingID, validCustomerID));
    assertNotNull(service.getBooking(bookingID, validCustomerName));

    assertNull(service.getBooking(bookingID, validCustomerID, invalidCustomerName));
    assertNull(service.getBooking(bookingID, invalidCustomerID, validCustomerName));
    assertNull(service.getBooking(bookingID, invalidCustomerID, invalidCustomerName));
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
      e -> e.getID().equals(bookingID)
    ).toList().get(0).getTripSet().stream().toList();

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

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

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNull(service.getTrips(bookingID));
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

    EntityManager entityManager = createBookingEntityManager(allBookings, bookingID);

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

    List<HotelBooking> expectedHotelBookings = allTrips.stream().filter(
            e -> e.getID().equals(tripID)
    ).toList().get(0).getHotelBookingSet().stream().toList();

    EntityManager entityManager = createTripEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<HotelBooking> actualHotelBookings = service.getHotelBookings(tripID);

    assertEquals(expectedHotelBookings, actualHotelBookings);
  }

  /**
   * This method tests the <code>getHotelBookings(int tripID)</code> method with an invalid trip ID
   * to which no trip is known to the entity manager.
   */
  @Test
  public void testGetHotelBookingsMethodWithInvalidId() {
    int tripID = 0;

    List<Trip> allTrips = createTripList();

    EntityManager entityManager = createTripEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNull(service.getHotelBookings(tripID));
  }

  /**
   * This method tests the <code>getHotelBookings(int tripID)</code> method with a valid trip ID and a
   * corresponding trip that has no hotel bookings allocated.
   */
  @Test
  public void testGetHotelBookingsMethodWithoutHotelBookings() {
    int tripID = 3;

    List<Trip> allTrips = createTripList();

    List<HotelBooking> expectedHotelBookings = new LinkedList<>();

    EntityManager entityManager = createTripEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<HotelBooking> actualHotelBookings = service.getHotelBookings(tripID);

    assertEquals(expectedHotelBookings, actualHotelBookings);
  }

  /**
   * This method tests the <code>getFlightBookings(int tripID)</code> method with a valid trip ID and a
   * corresponding trip that has at least one flight booking allocated.
   */
  @Test
  public void testGetFlightBookingsMethodWithValidId() {
    int tripID = 1;

    List<Trip> allTrips = createTripList();

    List<FlightBooking> expectedFlightBookings = allTrips.stream().filter(
            e -> e.getID().equals(tripID)
    ).toList().get(0).getFlightBookingSet().stream().toList();

    EntityManager entityManager = createTripEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<FlightBooking> actualFlightBookings = service.getFlightBookings(tripID);

    assertEquals(expectedFlightBookings, actualFlightBookings);
  }

  /**
   * This method tests the <code>getFlightBookings(int tripID)</code> method with an invalid trip ID
   * to which no trip is known to the entity manager.
   */
  @Test
  public void testGetFlightBookingsMethodWithInvalidId() {
    int tripID = 0;

    List<Trip> allTrips = createTripList();

    EntityManager entityManager = createTripEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    assertNull(service.getFlightBookings(tripID));
  }

  /**
   * This method tests the <code>getFlightBookings(int tripID)</code> method with a valid trip ID and a
   * corresponding trip that has no flight bookings allocated.
   */
  @Test
  public void testGetFlightBookingsMethodWithoutHotelBookings() {
    int tripID = 3;

    List<Trip> allTrips = createTripList();

    List<FlightBooking> expectedFlightBookings = new LinkedList<>();

    EntityManager entityManager = createTripEntityManager(allTrips, tripID);

    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(entityManager);

    List<FlightBooking> actualFlightBookings = service.getFlightBookings(tripID);

    assertEquals(expectedFlightBookings, actualFlightBookings);
  }

}