package travel.travelagency.service.consumption;

import java.util.List;

public interface TravelAgencyViewConsumptionService {

  record BookingConsumption(Integer bookingID, Integer customerID, Double totalPrice) { }

  record TripConsumption(Integer tripID, Double totalPrice) { }

  record HotelBookingConsumption(Integer bookingID, String hotelName, Double price) { }

  record FlightBookingConsumption(Integer bookingID, String departure, String arrival, Double price) { }

  List<BookingConsumption> getBookings(Integer bookingID, Integer customerID);

  List<TripConsumption> getTrips(BookingConsumption booking);

  List<HotelBookingConsumption> getHotelBookings(TripConsumption trip);

  List<FlightBookingConsumption> getFlightBookings(TripConsumption trip);

}
