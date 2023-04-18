package travel.travelagency.service.data;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import travel.travelagency.entities.Booking;
import travel.travelagency.entities.FlightBooking;
import travel.travelagency.entities.HotelBooking;
import travel.travelagency.entities.Trip;

public class TravelAgencyViewDataServiceImplementation implements TravelAgencyViewDataService {

  private final EntityManager EM;

  public TravelAgencyViewDataServiceImplementation(EntityManager entityManager) {
    this.EM = entityManager;
  }

  @Override
  public List<Booking> getBookings(Integer bookingID, Integer customerID) {
    TypedQuery<Booking> typedQuery = EM.createNamedQuery(Booking.FIND_WITH_FILTERS, Booking.class);
    typedQuery = typedQuery.setParameter(Booking.BOOKING_ID, bookingID);
    typedQuery = typedQuery.setParameter(Booking.CUSTOMER_ID, customerID);
    return typedQuery.getResultList();
  }

  @Override
  public List<Trip> getTrips(Booking booking) {
    List<Trip> tripList;
    if(booking == null || booking.getTripSet() == null)
      tripList = new LinkedList<>();
    else
      tripList = new LinkedList<>(booking.getTripSet());
    return tripList;
  }


  @Override
  public List<HotelBooking> getHotelBookings(Trip trip) {
    List<HotelBooking> hotelBookingList;
    if(trip == null || trip.getHotelBookingSet() == null)
      hotelBookingList = new LinkedList<>();
    else
      hotelBookingList = new LinkedList<>(trip.getHotelBookingSet());
    return hotelBookingList;
  }

  @Override
  public List<FlightBooking> getFlightBookings(Trip trip) {
    List<FlightBooking> flightBookingList;
    if(trip == null || trip.getFlightBookingSet() == null)
      flightBookingList = new LinkedList<>();
    else
      flightBookingList = new LinkedList<>(trip.getFlightBookingSet());
    return flightBookingList;
  }

}
