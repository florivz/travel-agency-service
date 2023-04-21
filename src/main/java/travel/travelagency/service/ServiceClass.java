package travel.travelagency.service;

import org.hibernate.internal.build.AllowSysOut;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;
import travel.travelagency.entities.Booking;

import javax.persistence.EntityManager;

public class ServiceClass {

  public static void main(String[] args) {
    EntityManager em = new TravelAgencyEntityManagerFactory("db.properties").createEntityManager();
    Booking book = em.find(Booking.class, 1);
    System.out.println(book.toString());
  }

}
