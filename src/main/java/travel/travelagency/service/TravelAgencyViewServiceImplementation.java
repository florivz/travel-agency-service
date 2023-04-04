package travel.travelagency.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import travel.travelagency.entities.Booking;
import travel.travelagency.entities.FlightBooking;
import travel.travelagency.entities.HotelBooking;
import travel.travelagency.entities.Trip;

public class TravelAgencyViewServiceImplementation implements TravelAgencyViewService {

  private final EntityManager EM;

  public TravelAgencyViewServiceImplementation(EntityManager entityManager) {
    this.EM = entityManager;
  }

  public static void main(String[] args) {
    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(
        new travel.travelagency.database.TravelAgencyEntityManagerFactory().createEntityManager());
    List<Booking> list = service.getBookings(null, 1);
    System.out.println(list.toString());
  }

  @Override
  public List<Booking> getBookings(Integer bookingID, Integer customerID) {
    String namedQuery;

    if (bookingID != null && customerID != null)
      namedQuery = Booking.FIND_BY_ID_AND_CUSTOMER_ID;
    else if (bookingID != null)
      namedQuery = Booking.FIND_BY_ID;
    else if (customerID != null)
      namedQuery = Booking.FIND_BY_CUSTOMER_ID;
    else
      namedQuery = Booking.FIND_ALL;

    TypedQuery<Booking> typedQuery = EM.createNamedQuery(namedQuery, Booking.class);
    typedQuery.setParameter("bookingID", bookingID);
    typedQuery.setParameter("customerID", customerID);
    return typedQuery.getResultList();
  }

  @Override
  public List<Trip> getTrips(Booking booking) {
    List<Trip> tripList;
    if(booking == null)
      tripList = new LinkedList<>();
    else
      tripList = new LinkedList<>(booking.getTripSet());
    return tripList;
  }

  @Override
  public List<HotelBooking> getHotelBookings(Trip trip) {
    List<HotelBooking> hotelBookingList;
    if(trip == null)
      hotelBookingList = new LinkedList<>();
    else
      hotelBookingList = new LinkedList<>(trip.getHotelBookingSet());
    return hotelBookingList;
  }

  @Override
  public List<FlightBooking> getFlightBookings(Trip trip) {
    List<FlightBooking> flightBookingList;
    if(trip == null)
      flightBookingList = new LinkedList<>();
    else
      flightBookingList = new LinkedList<>(trip.getFlightBookingSet());
    return flightBookingList;
  }

}
