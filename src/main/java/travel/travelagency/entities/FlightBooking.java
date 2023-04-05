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
@Table(name = "flight_booking")
public class FlightBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flight_booking_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "flight_id")
  private Flight flight;

  @Column(name = "number_of_passengers")
  private Integer numberOfPassengers;

  public FlightBooking() {

  }

  public FlightBooking(Integer id, Flight flight, Integer numberOfPassengers) {
    this.id = id;
    this.flight = flight;
    this.numberOfPassengers = numberOfPassengers;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public Integer getNumberOfPassengers() {
    return numberOfPassengers;
  }

  public void setNumberOfPassengers(Integer numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      FlightBooking booking = (FlightBooking) obj;
      return
          ((id == null && booking.getId() == null) || id.equals(booking.getId())) &&
          ((flight == null && booking.getFlight() == null) || flight.equals(booking.getFlight())) &&
          ((numberOfPassengers == null && booking.getNumberOfPassengers() == null)
              || numberOfPassengers.equals(booking.getNumberOfPassengers()));
    }
    return false;
  }

  public double getTotalPrice() {
    return numberOfPassengers * flight.getPricePerPerson();
  }

  @Override
  public String toString() {
    return
        (flight != null             ? flight.toString() + '\n'                       : "" )
      + (numberOfPassengers != null ? "Passengers: " + numberOfPassengers : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null        ? id.hashCode()                 : null)
            + (flight != null             ? flight.hashCode()             : null)
            + (numberOfPassengers != null ? numberOfPassengers.hashCode() : null)).hashCode();
  }

}
