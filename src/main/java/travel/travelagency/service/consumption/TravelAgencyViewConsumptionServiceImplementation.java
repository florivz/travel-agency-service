package travel.travelagency.service.consumption;

import travel.travelagency.entities.Booking;
import travel.travelagency.service.data.TravelAgencyViewDataService;

import java.util.LinkedList;
import java.util.List;

public class TravelAgencyViewConsumptionServiceImplementation implements TravelAgencyViewConsumptionService {

    private final TravelAgencyViewDataService dataService;

    public TravelAgencyViewConsumptionServiceImplementation(TravelAgencyViewDataService dataService) {
        this.dataService = dataService;
    }


    @Override
    public List<BookingConsumable> getBookings(int customerID, String customerLastName) {
        return null;
    }

    @Override
    public List<BookingConsumable> getBookings(int customerID) {
        return null;
    }

    @Override
    public List<BookingConsumable> getBookings(String customerLastName) {
        return null;
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, int customerID, String customerLastName) {
        return null;
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, int customerID) {
        return null;
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, String customerLastName) {
        return null;
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID) {
        return null;
    }

    @Override
    public List<TripConsumable> getTrips(Booking bookingID) {
        return null;
    }

    @Override
    public List<HotelBookingConsumable> getHotelBookings(Integer tripID) {
        return null;
    }

    @Override
    public List<FlightBookingConsumable> getFlightBookings(Integer tripID) {
        return null;
    }
}