package travel.travelagency.service.consumption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import travel.travelagency.entities.Booking;
import travel.travelagency.entities.Customer;
import travel.travelagency.entities.Trip;
import travel.travelagency.service.data.TravelAgencyViewDataService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

public class TravelAgencyViewConsumptionImplementationTest2 {

    private TravelAgencyViewConsumptionService service;

    @BeforeEach
    public void initialize(){
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        Mockito.when(dataService.getBookings(1, null))
                .thenReturn(mockedBookings());

        Mockito.when(dataService.getTrips(new Booking(1, null, null)))
                .thenReturn(mockedTrips());
    }

    private List<Booking> mockedBookings() {
        return List.of(
                new Booking(1, new Customer(1, null, null, null), null)
        );
    }

    private List<Trip> mockedTrips() {
        return List.of(
                new Trip(1, null, null)
        );
    }

    private List<TravelAgencyViewConsumptionService.BookingConsumption> getBookingsConsumptions() {
        return service.getBookings(1, null, "Test");
    }

//    private List<TravelAgencyViewConsumptionService.TripConsumption> getTripsConsumptions(){
//        return service.getTrips();
//    }


    @Test
    public void getBookingConsumptionsWithId1() {
        List<TravelAgencyViewConsumptionService.BookingConsumption> actualBookings = new LinkedList<>();
                actualBookings.add(new TravelAgencyViewConsumptionService.BookingConsumption(1, 1, null, null, 0.0));

        List<TravelAgencyViewConsumptionService.BookingConsumption> expectedBookings = getBookingsConsumptions();

        assertEquals(expectedBookings, actualBookings);
    }

//    @Test
//    public void getTripConsumptionsWithId1() {
//        List<TravelAgencyViewConsumptionService.TripConsumption> actualTrips = new LinkedList<>();
//        actualTrips.add(new TravelAgencyViewConsumptionService.TripConsumption(1, 0.0));
//
//        List<TravelAgencyViewConsumptionService.TripConsumption> expectedTrips = getTripsConsumptions();
//
//        assertEquals(expectedTrips, actualTrips);
//    }
}
