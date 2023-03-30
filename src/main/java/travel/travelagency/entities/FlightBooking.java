package travel.travelagency.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHT_BOOKING")
public class FlightBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FLIGHT_BOOKING_ID")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "TRIP_ID")
  private Trip trip;

  @ManyToOne
  @JoinColumn(name = "FLIGHT")
  private Hotel flight;

  @Column(name = "NUMBER_OF_PASSENGERS")
  private Integer numberOfPassengers;

  public FlightBooking() {

  }



}
