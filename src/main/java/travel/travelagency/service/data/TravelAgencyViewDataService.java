package travel.travelagency.service.data;

import java.util.List;

import travel.travelagency.entities.Booking;
import travel.travelagency.entities.Trip;
import travel.travelagency.entities.HotelBooking;
import travel.travelagency.entities.FlightBooking;

public interface TravelAgencyViewDataService {

  /**
   * This method returns all bookings filtered customer ID, and customer's last name.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(int customerID, String customerLastName) throws RuntimeException;

  /**
   * This method returns all bookings filtered customer ID.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(int customerID);

  /**
   * This method returns all bookings filtered customer's last name.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(String customerLastName) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID, customer ID, and customer's last name.
   * If no corresponding booking is found, this method shall throw a RuntimeException.
   * If more than one booking is found, this method shall throw a RuntimeException.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID, int customerID, String customerLastName) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID, and customer ID.
   * If no corresponding booking is found, this method shall throw a RuntimeException.
   * If more than one booking is found, this method shall throw a RuntimeException.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID, int customerID) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID, and customer's last name.
   * If no corresponding booking is found, this method shall throw a RuntimeException.
   * If more than one booking is found, this method shall throw a RuntimeException.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID, String customerLastName) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID.
   * If no corresponding booking is found, this method shall throw a RuntimeException.
   * If more than one booking is found, this method shall throw a RuntimeException.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID);

  /**
   * This method returns all trips included in the booking with the provided booking ID.
   * If no <code>Booking</code> object with the provided booking ID is found,
   * this method shall return <code>null</code>.
   * If the <code>Booking</code> object with the provided booking ID does not contain any trips,
   * this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @return A <code>List</code> object containing all trips included in the booking with the provided booking ID
   *                If no <code>Booking</code> object with the provided booking ID is found,
   *                this method shall return <code>null</code>.
   *                If the <code>Booking</code> object with the provided booking ID does not contain any trips,
   *                this method shall return an empty <code>List</code> object.
   */
  List<Trip> getTrips(int bookingID);

  /**
   * This method returns all hotel bookings included in the trip with the provided trip ID.
   * If no <code>Trip</code> object with the provided trip ID is found,
   * this method shall return <code>null</code>.
   * If the <code>Trip</code> object with the provided trip ID does not contain any hotel bookings,
   * this method shall return an empty <code>List</code> object.
   * @param tripID An <code>int</code> of the trip ID to be filtered by.
   * @return A <code>List</code> object containing all hotel bookings included in the trip with the provided trip ID
   *         If no <code>Trip</code> object with the provided trip ID is found,
   *         this method shall return <code>null</code>.
   *         If the <code>Trip</code> object with the provided trip ID does not contain any hotel bookings,
   *         this method shall return an empty <code>List</code> object.
   */
  List<HotelBooking> getHotelBookings(int tripID);

  /**
   * This method returns all flight bookings included in the trip with the provided trip ID.
   * If no <code>Trip</code> object with the provided trip ID is found,
   * this method shall return <code>null</code>.
   * If the <code>Trip</code> object with the provided trip ID does not contain any flight bookings,
   * this method shall return an empty <code>List</code> object.
   * @param tripID An <code>int</code> of the trip ID to be filtered by.
   * @return A <code>List</code> object containing all flight bookings included in the trip with the provided trip ID
   *         If no <code>Trip</code> object with the provided trip ID is found,
   *         this method shall return <code>null</code>.
   *         If the <code>Trip</code> object with the provided trip ID does not contain any flight bookings,
   *         this method shall return an empty <code>List</code> object.
   */
  List<FlightBooking> getFlightBookings(int tripID);

}
