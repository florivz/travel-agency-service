package travel.travelagency.service.data;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.entities.Booking;
import travel.travelagency.entities.FlightBooking;
import travel.travelagency.entities.HotelBooking;
import travel.travelagency.entities.Trip;

public class TravelAgencyViewDataServiceImplementation implements TravelAgencyViewDataService {

  private final EntityManager EM;

  static final Logger logger = LogManager.getLogger(TravelAgencyViewDataServiceImplementation.class);

  private static final String MSG_CUSTOMER_LASTNAME_NULL =
      "Cannot filter by customerLastName = 'null'";

  private static final String MSG_ENTITY_NOT_FOUND =
      "Could not retrieve %s with ID = %s so null was returned.";

  public TravelAgencyViewDataServiceImplementation(EntityManager entityManager) {
    this.EM = entityManager;
  }

  /**
   * Private method returning a <code>List</code> object containing all bookings filtered by the selected criteria.
   * Each parameter with the <code>null</code> value will be ignored in the filter.
   * If no corresponding <code>Booking</code> object is found, this method returns an empty <code>List</code> object.
   * @param customerID customer ID to be filtered by.
   * @param customerLastName customer's last name to be filtered by.
   * @return A <code>List</code> object containing all bookings matching the filters above.
   */
  private List<Booking> getBookingList(Integer customerID, String customerLastName) {
    TypedQuery<Booking> typedQuery = EM.createNamedQuery(Booking.FIND_WITH_FILTERS, Booking.class)
      .setParameter(Booking.CUSTOMER_ID, customerID)
      .setParameter(Booking.CUSTOMER_LASTNAME, customerLastName);
    return typedQuery.getResultList();
  }

  /**
   * Private method returning a <code>Booking</code> object with the provided booking ID.
   * If no corresponding <code>Booking</code> object is found,
   * this method throws a <code>RuntimeException</code>.
   * @param bookingID booking ID to be filtered by.
   * @return The <code>Booking</code> object to the corresponding booking ID.
   */
  private Booking getSingleBooking(int bookingID) {
    Booking booking = EM.find(Booking.class, bookingID);
    if(booking == null)
      logger.info(String.format(MSG_ENTITY_NOT_FOUND, "Booking", bookingID));
    return booking;
  }

  /**
   * Private method returning a <code>Trip</code> object with the provided trip ID.
   * If no corresponding <code>Trip</code> object is found,
   * this method throws a <code>RuntimeException</code>.
   * @param tripID trip ID to be filtered by.
   * @return The <code>Trip</code> object to the corresponding trip ID.
   */
  private Trip getSingleTrip(int tripID) {
    Trip trip = EM.find(Trip.class, tripID);
    if(trip == null)
      logger.info(String.format(MSG_ENTITY_NOT_FOUND, "Trip", trip));
    return trip;
  }

  @Override
  public List<Booking> getBookings(int customerID, String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    return getBookingList(customerID, customerLastName);
  }

  @Override
  public List<Booking> getBookings(int customerID) {
    return getBookingList(customerID, null);
  }

  @Override
  public List<Booking> getBookings(String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    return getBookingList(null, customerLastName);
  }

  @Override
  public Booking getBooking(int bookingID, int customerID, String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    Booking booking = getBooking(bookingID, customerID);
    if(! customerLastName.equals(booking.getCustomer().getPersonalData().getLastName())) {
      final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "customerLastName", customerLastName);
      logger.warn(MSG);
      throw new RuntimeException(MSG);
    }
    return booking;
  }

  @Override
  public Booking getBooking(int bookingID, int customerID) throws RuntimeException {
    Booking booking = getSingleBooking(bookingID);
    if(customerID != booking.getCustomer().getId()) {
      final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "customerID", customerID);
      logger.warn(MSG);
      throw new RuntimeException(MSG);
    }
    return booking;
  }

  @Override
  public Booking getBooking(int bookingID, String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    Booking booking = getSingleBooking(bookingID);
    if(! customerLastName.equals(booking.getCustomer().getPersonalData().getLastName())) {
      final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "customerLastName", customerLastName);
      logger.warn(MSG);
      throw new RuntimeException(MSG);
    }
    return booking;
  }

  @Override
  public Booking getBooking(int bookingID) {
    return getSingleBooking(bookingID);
  }

  @Override
  public List<Trip> getTrips(int bookingID) {
    Booking booking = getSingleBooking(bookingID);
    if(booking == null)
      return null;
    if(booking.getTripSet() == null)
      return new LinkedList<>();
    return booking.getTripSet().stream().toList();
  }

  @Override
  public List<HotelBooking> getHotelBookings(int tripID) {
    Trip trip = getSingleTrip(tripID);
    if(trip == null)
      return null;
    if(trip.getHotelBookingSet() == null)
      return new LinkedList<>();
    return trip.getHotelBookingSet().stream().toList();
  }

  @Override
  public List<FlightBooking> getFlightBookings(int tripID) {
    Trip trip = getSingleTrip(tripID);
    if(trip == null)
      return null;
    if(trip.getFlightBookingSet() == null)
      return new LinkedList<>();
    return trip.getFlightBookingSet().stream().toList();
  }

}
