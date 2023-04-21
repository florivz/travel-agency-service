package travel.travelagency.service.consumption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import travel.travelagency.entities.*;
import travel.travelagency.service.data.TravelAgencyViewDataService;

public class TravelAgencyViewConsumptionImplementationTest {

    private TravelAgencyViewConsumptionService service;

    @BeforeEach
    public void initializeService() {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);

        Mockito.when(dataService.getBookings(null, null))
                .thenReturn(getBookingList());

        Mockito.when(dataService.getBookings(1, 1))
                .thenReturn(getBookingList());

        Mockito.when(dataService.getTrips(null))
                .thenReturn(getTripList());

        Mockito.when(dataService.getTrips(new Booking(1, null, null)))
                .thenReturn(getTripList());

        service = new TravelAgencyViewConsumptionServiceImplementation(dataService);
    }

    private List<Booking> getBookingList() {
        return List.of(
            new Booking(1, new Customer(1, null, null, null), Set.of(new Trip())),
            new Booking(null, null, null)
        );
    }

    private List<TravelAgencyViewConsumptionService.BookingConsumption> getBookingConsumptionList() {
        List<TravelAgencyViewConsumptionService.BookingConsumption> resultList = new LinkedList<>();
        for(Booking booking : getBookingList())
            resultList.add(new TravelAgencyViewConsumptionService.BookingConsumption(
               booking.getId(), booking.getCustomer().getId(), null, null, 0.0
            ));
        return resultList;
    }

    private List<Trip> getTripList() {
        return List.of(
                new Trip(1, null, null)
        );
    }

    private List<TravelAgencyViewConsumptionService.TripConsumption> getTripConsumptionList(){
        List<TravelAgencyViewConsumptionService.TripConsumption> resultList = new LinkedList<>();
        for (Trip trip: getTripList()) {
            resultList.add(
                    new TravelAgencyViewConsumptionService.TripConsumption(
                            trip.getId(), 0.0
                    )
            );
        }
        return  resultList;
    }

    @Test
    public void testGetBookingsWithNullId() {
        List<TravelAgencyViewConsumptionService.BookingConsumption> expectedBookings = getBookingConsumptionList();

        List<TravelAgencyViewConsumptionService.BookingConsumption> actualBookings =
                service.getBookings(null, null, null);

        assertEquals(expectedBookings, actualBookings);
    }
    @Test
    public void testGetBookingsWithId1() {
        List<TravelAgencyViewConsumptionService.BookingConsumption> expectedBookings = getBookingConsumptionList()
                .stream()
                .filter(e -> e.customerID().equals(1) && e.bookingID().equals(1))
                .toList();

        List<TravelAgencyViewConsumptionService.BookingConsumption> actualBookings =
                service.getBookings(1, 1, null);

        assertEquals(expectedBookings, actualBookings);
    }

    @Test
    public void testGetTripsWithNullId() {
        List<TravelAgencyViewConsumptionService.TripConsumption> expectedTrips =
                getTripConsumptionList()
                        .stream()
                        .filter(e -> e.tripID().equals(0))
                        .toList();

        List<TravelAgencyViewConsumptionService.TripConsumption> actualTrips =
                service.getTrips(null);

        assertEquals(expectedTrips, actualTrips);
    }

    @Test
    public void testGetTripsWithID1() {
        List<TravelAgencyViewConsumptionService.TripConsumption> expectedTrips = getTripConsumptionList();
                expectedTrips = expectedTrips
                .stream()
                .filter(e -> e.tripID().equals(1) && e.totalPrice().equals(0.0))
                .toList();

        List<TravelAgencyViewConsumptionService.TripConsumption> actualTrips =
                service.getTrips(new Booking(1, null, null));

        assertEquals(expectedTrips, actualTrips);
    }
}
