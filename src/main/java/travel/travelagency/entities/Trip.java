package travel.travelagency.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRIP")
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRIP_ID")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "BOOKING_ID")
  private Booking booking;

  @OneToMany
  @JoinColumn(name = "TRIP_ID")
  private List<HotelBooking> hotelBookingList;

  @OneToMany
  @JoinColumn(name = "TRIP_ID")
  private List<FlightBooking> flightBookingList;

  public Trip() {

  }

}
