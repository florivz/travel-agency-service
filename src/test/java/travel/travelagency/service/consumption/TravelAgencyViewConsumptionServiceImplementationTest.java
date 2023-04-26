package travel.travelagency.service.consumption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import travel.travelagency.entities.Booking;
import travel.travelagency.entities.Customer;
import travel.travelagency.entities.PersonalData;
import travel.travelagency.service.data.TravelAgencyViewDataService;

public class TravelAgencyViewConsumptionServiceImplementationTest {

    /**
     * This private inner class is used to set an id for a <code>Customer</code> object.
     * This class is only necessary for unit testing purposes.
     */
    private static class TestCustomer extends Customer {

        private final Integer ID;

        public TestCustomer(Integer id, String lastName) {
            this.setPersonalData(new PersonalData());
            this.getPersonalData().setLastName(lastName);
            this.ID = id;
        }

        @Override
        public Integer getID() { return ID; }

    }

    /**
     * This private inner class is used to set an id for a <code>Booking</code> object.
     * This class is only necessary for unit testing purposes.
     */
    private static class TestBooking extends Booking {

        private final Integer ID;
        private final double PRICE;

        public TestBooking(
                Integer id, Integer customerID, String lastName, LocalDate date, Double price, String currency
        ) {
            this.ID = id;
            this.setCustomer(new TestCustomer(customerID, lastName));
            this.setDate(date);
            this.PRICE = price;
        }

        @Override
        public Integer getID() { return ID; }

        @Override
        public double getTotalPrice() { return PRICE; }

    }

    private TravelAgencyViewDataService createDataService(
        List<Booking> resultList, Integer customerID, String customerName
    ) {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        if(customerID != null && customerName != null)
            Mockito.when(dataService.getBookings(customerID, customerName)).thenReturn(resultList);
        else if(customerID != null)
            Mockito.when(dataService.getBookings(customerID)).thenReturn(resultList);
        else if(customerName != null)
            Mockito.when(dataService.getBookings(customerName)).thenReturn(resultList);
        return dataService;
    }

    private TravelAgencyViewDataService createDataService(
        Booking resultBooking, Integer bookingID, Integer customerID, String customerName
    ) {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        if(bookingID != null && customerID != null && customerName != null)
            Mockito.when(dataService.getBooking(bookingID, customerID, customerName)).thenReturn(resultBooking);
        else if(bookingID != null && customerID != null)
            Mockito.when(dataService.getBooking(bookingID, customerID)).thenReturn(resultBooking);
        else if(bookingID != null && customerName != null)
            Mockito.when(dataService.getBooking(bookingID, customerName)).thenReturn(resultBooking);
        else if(bookingID != null)
            Mockito.when(dataService.getBooking(bookingID)).thenReturn(resultBooking);
        return dataService;
    }

    private List<Booking> createBookingList() {
        return List.of(
            new TestBooking(1, 1, "Maier", LocalDate.now(), 50.0, "EUR"),
            new TestBooking(2, 1, "Maier", LocalDate.now(), 100.0, "EUR"),
            new TestBooking(3, 2, "Weiß", LocalDate.now(), 20.9, "EUR")
        );
    }

    private List<BookingConsumable> createBookingConsumableList() {
        return List.of(
            new BookingConsumable(1, 1, "Maier", LocalDate.now(), 50.0, "EUR"),
            new BookingConsumable(2, 1, "Maier", LocalDate.now(), 100.0, "EUR"),
            new BookingConsumable(3, 2, "Weiß", LocalDate.now(), 20.9, "EUR")
        );
    }

    /**
     * This method tests the <code>getBookings(int customerID)</code> method with a valid customer ID to which a
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingsMethodWithCustomerID() {
        int customerID = 1;

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
            e -> e.customerID().equals(customerID)
        ).toList();

        List<Booking> resultBookings = createBookingList().stream().filter(
                e -> e.getCustomer().getID().equals(customerID)
        ).toList();

        TravelAgencyViewDataService dataService = createDataService(resultBookings, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(int customerID)</code> method with an invalid customer ID to which no
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidCustomerID() {
        int customerID = 0;

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerName)</code> method with a valid customer's last name to which a
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingsMethodWithCustomerName() {
        String customerName = "Maier";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.customerName().equals(customerName)
        ).toList();

        List<Booking> resultBookings = createBookingList().stream().filter(
                e -> e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList();

        TravelAgencyViewDataService dataService = createDataService(resultBookings, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerName)</code> method with an invalid customer's
     * last name to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidCustomerName() {
        String customerName = "Jürgen";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerID, customerName)</code> method
     * with a combination of customer id and customer's last name
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingsMethodWithCustomerIdAndName() {
        int customerID = 1;
        String customerName = "Maier";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.customerID().equals(customerID) &&
                        e.customerName().equals(customerName)
        ).toList();

        List<Booking> resultBookings = createBookingList().stream().filter(
                e -> e.getCustomer().getID().equals(customerID) &&
                    e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList();

        TravelAgencyViewDataService dataService = createDataService(resultBookings, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerID, customerName)</code> method
     * with a combination of customer id and an customer's last name
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithCustomerIdAndInvalidName() {
        int customerID = 1;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingID() {
        int bookingID = 2;

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(int bookingID)</code> method with an invalid booking ID to which no
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingID() {
        int bookingID = 0;

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID)</code> method
     * with a combination of booking id and customer id
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingAndCustomerID() {
        int bookingID = 2;
        int customerID = 1;

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID) && e.customerID().equals(customerID)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID) &&
                        e.getCustomer().getID().equals(customerID)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID)</code> method
     * with a combination of booking id and customer id
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingAndCustomerID() {
        int bookingID = 1;
        int customerID = 3;

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID,customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerName)</code> method
     * with a combination of booking id and customer's last name
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingIDAndCustomerName() {
        int bookingID = 3;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID) && e.customerName().equals(customerName)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID) &&
                        e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerName)</code> method
     * with a combination of booking id and customer's last name
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingIDAndCustomerName() {
        int bookingID = 3;
        String customerName = "Maier";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID, customerName)</code> method
     * with a combination of booking id, customer id, and customer's last name
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingIDCustomerIDAndName() {
        int bookingID = 3;
        int customerID = 2;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID) &&
                        e.customerID().equals(customerID) &&
                        e.customerName().equals(customerName)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID) &&
                        e.getCustomer().getID().equals(customerID) &&
                        e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID, customerName)</code> method
     * with a combination of booking id, customer id, and customer's last name
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingIDCustomerIDAndName() {
        int bookingID = 3;
        int customerID = 1;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }


}
