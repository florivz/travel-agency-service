package travel.travelagency.service.consumption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import travel.travelagency.service.data.TravelAgencyViewDataService;

public class TravelAgencyViewConsumptionServiceImplementationTest {

    private TravelAgencyViewDataService createDataService() {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        return dataService;
    }

    private List<BookingConsumable> createBookingsList() {
        return List.of(
            new BookingConsumable(1, 1, "Maier", LocalDate.now(), 50.0, "EUR"),
            new BookingConsumable(2, 1, "Maier", LocalDate.now(), 100.0, "EUR"),
            new BookingConsumable(3, 2, "Weiß", LocalDate.now(), 20.9, "EUR")
        );
    }

    @Test
    public void testGetBookingsMethodWithCustomerID() {
        int customerID = 1;

        List<BookingConsumable> allBookings = createBookingsList();

        List<BookingConsumable> expectedBookings = null;

        TravelAgencyViewDataService dataService = createDataService();

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookings = service.getBookings(customerID);

        assertEquals(expectedBookings, actualBookings);
    }


}
