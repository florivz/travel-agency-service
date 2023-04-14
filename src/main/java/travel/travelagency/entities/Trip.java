package travel.travelagency.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRIP")
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRIP_ID")
  private Integer id;

  @OneToMany
  @JoinColumn(name = "TRIP_ID")
  private Set<HotelBooking> hotelBookingSet;

  @OneToMany
  @JoinColumn(name = "TRIP_ID")
  private Set<FlightBooking> flightBookingSet;

  public Trip() {

  }

  public Trip(Set<HotelBooking> hotelBookingSet, Set<FlightBooking> flightBookingSet) {
    this.hotelBookingSet = hotelBookingSet;
    this.flightBookingSet = flightBookingSet;
  }

  public Integer getId() {
    return id;
  }

  public Set<HotelBooking> getHotelBookingSet() {
    return hotelBookingSet;
  }

  public void setHotelBookingSet(
      Set<HotelBooking> hotelBookingList) {
    this.hotelBookingSet = hotelBookingList;
  }

  public Set<FlightBooking> getFlightBookingSet() {
    return flightBookingSet;
  }

  public void setFlightBookingSet(
      Set<FlightBooking> flightBookingList) {
    this.flightBookingSet = flightBookingList;
  }

  public double getTotalPrice() {
    return getTotalHotelPrice() + getTotalFlightPrice();
  }

  public double getTotalHotelPrice() {
    return hotelBookingSet.stream().mapToDouble(HotelBooking::getTotalPrice).sum();
  }

  public double getTotalFlightPrice() {
    return flightBookingSet.stream().mapToDouble(FlightBooking::getTotalPrice).sum();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Trip trip = (Trip) obj;
      return
          ((id == null && trip.getId() == null) || id.equals(trip.getId())) &&
          ((hotelBookingSet == null && trip.getHotelBookingSet() == null)
              || hotelBookingSet.equals(trip.getHotelBookingSet())) &&
          ((flightBookingSet == null && trip.getFlightBookingSet() == null)
              || flightBookingSet.equals(trip.getFlightBookingSet()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (id != null ? "Trip no. : " + id + '\n' : "")
      + (hotelBookingSet != null  ? "Hotel Bookings:\n" + hotelBookingSet + '\n' : "" )
      + (flightBookingSet != null ? "Flight Bookings:\n" + flightBookingSet : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null      ? id.hashCode()               : null)
            + (hotelBookingSet != null  ? hotelBookingSet.hashCode()  : null)
            + (flightBookingSet != null ? flightBookingSet.hashCode() : null)).hashCode();
  }

}
