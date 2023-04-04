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

@Entity
@Table(name = "BOOKING")
@NamedQueries({
    @NamedQuery(
        name = Booking.FIND_ALL,
        query = "SELECT booking FROM Booking booking"
    ),
    @NamedQuery(
        name = Booking.FIND_BY_ID,
        query = "SELECT booking FROM Booking booking WHERE booking.id = :bookingID"
    ),
    @NamedQuery(
        name = Booking.FIND_BY_CUSTOMER_ID,
        query = "SELECT booking FROM Booking booking WHERE booking.customer.id = :customerID"
    ),
    @NamedQuery(
        name = Booking.FIND_BY_ID_AND_CUSTOMER_ID,
        query = "SELECT booking FROM Booking booking "
            + "WHERE booking.id = :bookingID AND booking.customer.id = :customerID"
    )
})
public class Booking {

  public static final String
      FIND_ALL = "Booking.findAll",
      FIND_BY_ID = "Booking.findById",
      FIND_BY_CUSTOMER_ID = "Booking.findByCustomerId",
      FIND_BY_ID_AND_CUSTOMER_ID = "Booking.findByIdAndCustomerId";

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

  public Booking(Integer id, Customer customer, Set<Trip> tripSet) {
    this.id = id;
    this.customer = customer;
    this.tripSet = tripSet;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
      + (customer != null ? "Customer:\n" + customer.toString() + '\n' : "" )
      + (tripSet != null  ? "Trips:\n" + tripSet.toString() : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
      + (customer != null           ? customer.hashCode() : null)
      + (tripSet != null            ? tripSet.hashCode()  : null)).hashCode();
  }

}
