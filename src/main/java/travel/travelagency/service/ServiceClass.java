package travel.travelagency.service;

import javax.persistence.EntityManager;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;
import travel.travelagency.entities.Customer;

public class ServiceClass {

  public static void main(String[] args) {
    TravelAgencyEntityManagerFactory emf = new TravelAgencyEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    System.out.println(
        "-".repeat(150) + '\n' +
            em.find(Customer.class, 1) + '\n' +
            "-".repeat(150)
    );
  }

}
