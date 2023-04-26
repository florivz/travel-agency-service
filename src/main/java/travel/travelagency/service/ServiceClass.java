package travel.travelagency.service;

import travel.travelagency.database.TravelAgencyEntityManagerFactory;
import travel.travelagency.entities.Booking;
import travel.travelagency.service.data.TravelAgencyViewDataService;
import travel.travelagency.service.data.TravelAgencyViewDataServiceImplementation;

import javax.persistence.EntityManager;

public class ServiceClass {

  public static void main(String[] args) {
    EntityManager em = new TravelAgencyEntityManagerFactory().createEntityManager();
    TravelAgencyViewDataService service = new TravelAgencyViewDataServiceImplementation(em);
    Booking booking = service.getBooking(0);
    //System.out.println(book.toString());
  }

}
