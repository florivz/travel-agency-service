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
    public List<BookingConsumption> getBookings(Integer bookingID, Integer customerID, String customerName) {
        List<Booking> bookingList = dataService.getBookings(bookingID, customerID);
        List<BookingConsumption> bookingConsumptionList = new LinkedList<>();
        for (Booking booking : bookingList) {
            bookingConsumptionList.add(new BookingConsumption(
                    booking.getId(), booking.getCustomer().getId(), null, null, 0.0
            ));
        }
        return bookingConsumptionList;
    }

    @Override
    public List<TripConsumption> getTrips(Booking booking) {
        return null;
    }

    @Override
    public List<HotelBookingConsumption> getHotelBookings(TripConsumption trip) {
        return null;
    }

    @Override
    public List<FlightBookingConsumption> getFlightBookings(TripConsumption trip) {
        return null;
    }
//
//    @Override
//    public List<TripConsumption> getTrips(Booking booking) {
//        List<Trip> tripList = dataService.getTrips(booking.getId());
//        List<TripConsumption> tripConsumptionList = new LinkedList<>();
//        for (Trip trip : tripList) {
//            tripConsumptionList.add(new TripConsumption(trip.getId(), trip.getTotalPrice()));
//        }
//        return tripConsumptionList;
//    }
//
//    @Override
//    public List<HotelBookingConsumption> getHotelBookings(TripConsumption trip) {
//        List<HotelBooking> hotelBookingList = dataService.getHotelBookings(trip.tripID());
//        List<HotelBookingConsumption> hotelBookingConsumptionList = new LinkedList<>();
//        for (HotelBooking hotelBooking : hotelBookingList) {
//            hotelBookingConsumptionList.add(new HotelBookingConsumption(
//                    hotelBooking.getId(),
//                    hotelBooking.getHotel().getName(),
//                    hotelBooking.getTotalPrice()
//            ));
//        }
//        return hotelBookingConsumptionList;
//    }
//
//    @Override
//    public List<FlightBookingConsumption> getFlightBookings(TripConsumption trip) {
//        List<FlightBooking> flightBookingList = dataService.getFlightBookings(trip.tripID());
//        List<FlightBookingConsumption> flightBookingConsumptionList = new LinkedList<>();
//        for (FlightBooking flightBooking : flightBookingList) {
//            flightBookingConsumptionList.add(new FlightBookingConsumption(
//                    flightBooking.getId(),
//                    flightBooking.getFlight().getDepartureTimestamp().toString(),
//                    flightBooking.getFlight().getArrivalTimestamp().toString(),
//                    flightBooking.getTotalPrice()
//                    ));
//        }
//        return flightBookingConsumptionList;
//    }
}