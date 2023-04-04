package travel.travelagency.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import org.mockito.Mockito;
import travel.travelagency.entities.*;
import java.util.List;
import java.util.LinkedList;

public class TravelAgencyViewServiceImplementationTest {

  private EntityManager EM;

  @BeforeEach
  public void createMockedEntityManager() {
    TypedQuery<Booking> allBookings = Mockito.mock(TypedQuery.class);
    Mockito.when(allBookings.getResultList()).thenReturn(createBookingList());

    EM = Mockito.mock(EntityManager.class);
    Mockito.when(EM.createNamedQuery(Booking.FIND_ALL, Booking.class))
        .thenReturn(allBookings);
  }

  private List<Booking> createBookingList() {
    List<Booking> bookingList = new LinkedList<>();
    return bookingList;
  }

  @Test
  public void testGetBookingsWithNull() {
    List<Booking> expectedBookingList = createBookingList();

    List<Booking> actualBookingList = new TravelAgencyViewServiceImplementation(EM).getBookings(null, null);

    assertEquals(expectedBookingList, actualBookingList);
  }

}