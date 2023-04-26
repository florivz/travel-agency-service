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

    private List<BookingConsumable> convertBookingEntityToConsumable(List<Booking> bookings) {
        List<BookingConsumable> bookingConsumables = new LinkedList<>();
        for (Booking booking : bookings) {
            bookingConsumables.add(new BookingConsumable(
                booking.getId(),
                booking.getCustomer().getId(),
                booking.getCustomer().getPersonalData().getLastName(),
                booking.getDate(),
                booking.getTotalPrice(),
                "EUR"
            ));
        }
        return bookingConsumables;
    }

    @Override
    public List<BookingConsumable> getBookings(int customerID, String customerLastName) {
        List<Booking> bookingList = dataService.getBookings(customerID, customerLastName);
        return bookingList == null ? new LinkedList<>() : convertBookingEntityToConsumable(bookingList);
    }

    @Override
    public List<BookingConsumable> getBookings(int customerID) {
        List<Booking> bookingList = dataService.getBookings(customerID);
        return bookingList == null ? new LinkedList<>() : convertBookingEntityToConsumable(bookingList);
    }

    @Override
    public List<BookingConsumable> getBookings(String customerLastName) {
        List<Booking> bookingList = dataService.getBookings(customerLastName);
        return bookingList == null ? new LinkedList<>() : convertBookingEntityToConsumable(bookingList);
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, int customerID, String customerLastName) {
        Booking booking = dataService.getBooking(bookingID, customerID, customerLastName);
        return booking == null ? new LinkedList<>() : convertBookingEntityToConsumable(List.of(booking));
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, int customerID) {
        Booking booking = dataService.getBooking(bookingID, customerID);
        return booking == null ? new LinkedList<>() : convertBookingEntityToConsumable(List.of(booking));
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, String customerLastName) {
        Booking booking = dataService.getBooking(bookingID, customerLastName);
        return booking == null ? new LinkedList<>() : convertBookingEntityToConsumable(List.of(booking));
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID) {
        Booking booking = dataService.getBooking(bookingID);
        return booking == null ? new LinkedList<>() : convertBookingEntityToConsumable(List.of(booking));
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