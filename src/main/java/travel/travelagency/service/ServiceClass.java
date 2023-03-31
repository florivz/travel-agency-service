package travel.travelagency.service;

import javax.persistence.EntityManager;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;
import travel.travelagency.entities.*;

import java.time.*;

public class ServiceClass {

  public static void main(String[] args) {
    TravelAgencyEntityManagerFactory emf = new TravelAgencyEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    //Booking book = em.find(Booking.class, 1);
    Flight flight = em.find(Flight.class, 1);
    System.out.println(
        "-".repeat(150) + '\n' +
            em.find(Customer.class, 1) + '\n' +
            "-".repeat(150)
    );
  }

}
