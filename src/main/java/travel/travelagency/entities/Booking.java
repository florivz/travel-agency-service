package travel.travelagency.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class is a jpa entity to the corresponding table 'BOOKING' in the database 'travel-agency-service_db'.
 * It contains several prepared statements that allow for queries of filtered bookings
 */
@Entity
@Table(name = "BOOKING")
@NamedQueries({
    @NamedQuery(
        name = Booking.FIND_ALL,
        query = "SELECT booking FROM Booking booking"
    ),
    @NamedQuery(
        name = Booking.FIND_WITH_FILTERS,
        query = """
            SELECT booking FROM Booking booking
            WHERE booking.id                              = coalesce(:bookingID, booking.id)
            AND   booking.customer.id                     = coalesce(:customerID, booking.customer.id)
            AND   booking.customer.personalData.lastName  = coalesce(:customerName, booking.customer.personalData.lastName)"""
    )
})
public class Booking {

  public static final String
      FIND_ALL = "Booking.findAll",
      FIND_WITH_FILTERS = "Booking.findWithFilters",
      BOOKING_ID = "bookingID",
      CUSTOMER_ID = "customerID",
      CUSTOMER_NAME = "customerName";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BOOKING_ID")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;

  @OneToMany
  @JoinColumn(name = "BOOKING_ID")
  private Set<Trip> tripSet;

  public Booking() {

  }

  public Booking(Customer customer, Set<Trip> tripSet) {
    this.customer = customer;
    this.tripSet = tripSet;
  }

  public Integer getId() {
    return id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Set<Trip> getTripSet() {
    return tripSet;
  }

  public void setTripSet(Set<Trip> tripList) {
    this.tripSet = tripList;
  }

  public double getTotalPrice() {
    return tripSet.stream().mapToDouble(Trip::getTotalPrice).sum();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Booking booking = (Booking) obj;
      return
          ((id == null && booking.getId() == null) || id.equals(booking.getId())) &&
          ((customer == null && booking.getCustomer() == null)
              || customer.equals(booking.getCustomer())) &&
          ((tripSet == null && booking.getTripSet() == null)
              || tripSet.equals(booking.getTripSet()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (id != null ? "Booking no. : " + id + '\n' : "")
      + (customer != null ? "Customer:\n" + customer + '\n' : "" )
      + (tripSet != null  ? "Trips:\n" + tripSet : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
      + (customer != null           ? customer.hashCode() : null)
      + (tripSet != null            ? tripSet.hashCode()  : null)).hashCode();
  }

}
