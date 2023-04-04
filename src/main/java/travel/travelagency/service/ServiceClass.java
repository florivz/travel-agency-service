package travel.travelagency.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;
import travel.travelagency.entities.*;

public class ServiceClass {

  public static void main(String[] args) {
    TravelAgencyEntityManagerFactory factory = new TravelAgencyEntityManagerFactory("db.properties");
    EntityManager entityManager = factory.createEntityManager();
    TravelAgencyViewService service = new TravelAgencyViewServiceImplementation(entityManager);

    TypedQuery<Booking> query = entityManager.createNamedQuery(Booking.FIND_WITH_FILTERS, Booking.class);

    TypedQuery<Booking> bQuery = query.setParameter("bookingID", null);
    TypedQuery<Booking> cQuery = bQuery.setParameter("customerID", null);

    List<Booking> notAllList = cQuery.getResultList();
  }

}
