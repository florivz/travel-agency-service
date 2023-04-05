package travel.travelagency.service.data;

import java.util.List;

import travel.travelagency.entities.Booking;
import travel.travelagency.entities.Trip;
import travel.travelagency.entities.HotelBooking;
import travel.travelagency.entities.FlightBooking;

public interface TravelAgencyViewDataService {

  /**
   * This method returns all bookings filtered by booking ID and customer ID.
   * If no corresponding booking is found,
   * this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>Integer</code> of all booking IDs to be filtered by.
   *                  If this parameter is not equal to <code>null</code>,
   *                  the result shall only contain a single item.
   *                  If this parameter is equal to <code>null</code>,
   *                  it shall be ignored in the filter.
   * @param customerID An <code>Integer</code> of all customer IDs to be filtered by.
   *                   If this parameter is not equal to <code>null</code>,
   *                   the result shall contain all bookings of the corresponding customer.
   *                   If this parameter is equal to <code>null</code>,
   *                   it shall be ignored in the filter.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(Integer bookingID, Integer customerID);

  /**
   * This method returns all trips included in the booking provided.
   * If the provided booking is <code>null</code> or does not contain any trips,
   * this method shall return an empty <code>List</code> object.
   * @param booking A <code>Booking</code> object whose trips shall be returned.
   *                If this parameter is not equal to <code>null</code>,
   *                the method shall work as stated above.
   *                If this parameter is equal to <code>null</code>,
   *                the method shall return an empty <code>List</code> object.
   * @return A <code>List</code> object containing all trips included in this booking.
   */
  List<Trip> getTrips(Booking booking);

  /**
   * This method returns all hotel bookings included in the trip provided.
   * If the provided trip is <code>null</code> or does not contain any hotel bookings,
   * this method shall return an empty <code>List</code> object.
   * @param trip A <code>Trip</code> object whose hotel bookings shall be returned.
   *             If this parameter is not equal to <code>null</code>,
   *             the method shall work as stated above.
   *             If this parameter is equal to <code>null</code>,
   *             the method shall return an empty <code>List</code> object.
   * @return A <code>List</code> object containing all hotel bookings included in this trip.
   */
  List<HotelBooking> getHotelBookings(Trip trip);

  /**
   * This method returns all flight bookings included in the trip provided.
   * If the provided trip is <code>null</code> or does not contain any flight bookings,
   * this method shall return an empty <code>List</code> object.
   * @param trip A <code>Trip</code> object whose flight bookings shall be returned.
   *             If this parameter is not equal to <code>null</code>,
   *             the method shall work as stated above.
   *             If this parameter is equal to <code>null</code>,
   *             the method shall return an empty <code>List</code> object.
   * @return A <code>List</code> object containing all flight bookings included in this trip.
   */
  List<FlightBooking> getFlightBookings(Trip trip);

}
