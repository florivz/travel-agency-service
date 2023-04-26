package travel.travelagency.service.consumption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.entities.Booking;
import travel.travelagency.entities.Trip;
import travel.travelagency.service.data.TravelAgencyViewDataService;
import travel.travelagency.service.data.TravelAgencyViewDataServiceImplementation;

import java.util.LinkedList;
import java.util.List;

public class TravelAgencyViewConsumptionServiceImplementation implements TravelAgencyViewConsumptionService {

    static final Logger logger = LogManager.getLogger(TravelAgencyViewConsumptionServiceImplementation.class);

    private final TravelAgencyViewDataService dataService;

    public TravelAgencyViewConsumptionServiceImplementation(TravelAgencyViewDataService dataService) {
        this.dataService = dataService;
    }

    /**
     * This private method is used to map all the necessary attributes from a List of
     * <code>Booking</code> jpa entities to a List of <code>BookingConsumable</code> objects
     * containing all necessary information for the front end
     * @param bookings A List of all <code>Booking</code> jpa entities to be converted
     * @return A List of successfully converted <code>BookingConsumption</code> objects
     */
    private List<BookingConsumable> convertBookingEntityToConsumable(List<Booking> bookings) {
        List<BookingConsumable> bookingConsumables = new LinkedList<>();
        for (Booking booking : bookings) {
            bookingConsumables.add(new BookingConsumable(
                booking.getID(),
                booking.getCustomer().getID(),
                booking.getCustomer().getPersonalData().getLastName(),
                booking.getDate(),
                booking.getTotalPrice(),
                "EUR"
            ));
        }
        return bookingConsumables;
    }

    /**
     * This private method is used to map all the necessary attributes from a List of
     * <code>Trip</code> jpa entities to a List of <code>TripConsumable</code> objects
     * containing all necessary information for the front end
     * @param trips A List of all <code>Trip</code> jpa entities to be converted
     * @return A List of successfully converted <code>TripConsumption</code> objects
     */
    private List<TripConsumable> convertTripEntityToConsumable(List<Trip> trips) {
        List<TripConsumable> tripConsumables = new LinkedList<>();
        for (Trip trip : trips) {
            tripConsumables.add(new TripConsumable(
                trip.getID(),
                trip.getHotelBookingSet().size(),
                trip.getFlightBookingSet().size(),
                trip.getTotalPrice()
            ));
        }
        return tripConsumables;
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
    public List<TripConsumable> getTrips(int bookingID) {
        Booking booking = dataService.getBooking(bookingID);
        if(booking == null) {
            final String MSG = "No Booking with booking id = %s found";
            logger.warn(MSG);
            throw new RuntimeException(MSG);
        }
        return convertTripEntityToConsumable(booking.getTripSet().stream().toList());
    }

    @Override
    public List<HotelBookingConsumable> getHotelBookings(int tripID) {
        return null;
    }

    @Override
    public List<FlightBookingConsumable> getFlightBookings(int tripID) {
        return null;
    }
}