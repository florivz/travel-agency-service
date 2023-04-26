package travel.travelagency.service;

import travel.travelagency.database.TravelAgencyEntityManagerFactory;
import travel.travelagency.entities.Booking;
import travel.travelagency.service.consumption.BookingConsumable;
import travel.travelagency.service.consumption.TravelAgencyViewConsumptionService;
import travel.travelagency.service.consumption.TravelAgencyViewConsumptionServiceImplementation;
import travel.travelagency.service.data.TravelAgencyViewDataService;
import travel.travelagency.service.data.TravelAgencyViewDataServiceImplementation;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiceClass {

  public static void main(String[] args) {
    EntityManager em = new TravelAgencyEntityManagerFactory().createEntityManager();
    TravelAgencyViewDataService dataService = new TravelAgencyViewDataServiceImplementation(em);
    TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

    List<BookingConsumable> bookings = service.getBooking(1);

    System.out.println(bookings.toString());
  }

}
