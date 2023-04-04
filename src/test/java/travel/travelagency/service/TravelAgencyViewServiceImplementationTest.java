package travel.travelagency.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class TravelAgencyViewServiceImplementationTest {

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

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithId() {
    List<Booking> expectedBookingList = createBookingList(1, null);

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(1, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithCustomerId() {
    List<Booking> expectedBookingList = createBookingList(null, 1);

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(null, 1);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetBookingsWithIdAndCustomerId() {
    List<Booking> expectedBookingList = createBookingList(3, 2);

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<Booking> actualBookingList = service.getBookings(3, 2);

    assertEquals(expectedBookingList, actualBookingList);
  }

  @Test
  public void testGetTripsWithNull() {
    List<Trip> expectedTripList = new LinkedList<>();

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<Trip> actualTripList = service.getTrips(null);

    assertEquals(expectedTripList, actualTripList);
  }

  @Test
  public void testGetTripsWithNullTripSet() {
    List<Trip> expectedTripList = new LinkedList<>();

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<Trip> actualTripList = service.getTrips(new Booking());

    assertEquals(expectedTripList, actualTripList);
  }

  @Test
  public void testGetHotelBookingsWithNull() {
    List<HotelBooking> expectedHotelBookingList = new LinkedList<>();

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<HotelBooking> actualFlightBookingList = service.getHotelBookings(null);

    assertEquals(expectedHotelBookingList, actualFlightBookingList);
  }

  @Test
  public void testGetHotelBookingsWithNullHotelBookingSet() {
    List<HotelBooking> expectedHotelBookingList = new LinkedList<>();

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<HotelBooking> actualFlightBookingList = service.getHotelBookings(new Trip());

    assertEquals(expectedHotelBookingList, actualFlightBookingList);
  }

  @Test
  public void testGetFlightBookingsWithNull() {
    List<FlightBooking> expectedFlightBookingList = new LinkedList<>();

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<FlightBooking> actualFlightBookingList = service.getFlightBookings(null);

    assertEquals(expectedFlightBookingList, actualFlightBookingList);
  }

  @Test
  public void testGetFlightBookingsWithNullFlightBookingSet() {
    List<FlightBooking> expectedFlightBookingList = new LinkedList<>();

    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(EM);
    List<FlightBooking> actualFlightBookingList = service.getFlightBookings(new Trip());

    assertEquals(expectedFlightBookingList, actualFlightBookingList);
  }

}