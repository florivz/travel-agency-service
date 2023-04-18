package travel.travelagency.service.consumption;

import travel.travelagency.entities.Booking;

import java.time.LocalDate;
import java.util.List;

public interface TravelAgencyViewConsumptionService {

  record BookingConsumption(Integer bookingID, Integer customerID, String customerName, LocalDate date, Double totalPrice) { }

  record TripConsumption(Integer tripID, Double totalPrice) { }

  record HotelBookingConsumption(Integer bookingID, String hotelName, Double price) { }

  record FlightBookingConsumption(Integer bookingID, String departure, String arrival, Double price) { }

  List<BookingConsumption> getBookings(Integer bookingID, Integer customerID, String customerName);

  List<TripConsumption> getTrips(Booking booking);

  List<HotelBookingConsumption> getHotelBookings(TripConsumption trip);

  List<FlightBookingConsumption> getFlightBookings(TripConsumption trip);

}
