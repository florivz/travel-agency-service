package travel.travelagency.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "FLIGHT")
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FLIGHT_ID")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "FLIGHT_CONNECTION_ID")
  private FlightConnection flightConnection;

  @Column(name = "DEPARTURE_DATE")
  private LocalDate dateOfDeparture;

  @Column(name = "DEPARTURE_TIME")
  private LocalTime timeOfDeparture;

  @Column(name = "DEPARTURE_TIME_ZONE")
  private ZoneId timeZoneOfDeparture;

  @Column(name = "ARRIVAL_DATE")
  private LocalDate dateOfArrival;

  @Column(name = "ARRIVAL_TIME")
  private LocalTime timeOfArrival;

  @Column(name = "ARRIVAL_TIME_ZONE")
  private ZoneId timeZoneOfArrival;

  @Column(name = "PRICE_PER_PERSON")
  private Double pricePerPerson;

  @Column(name = "CURRENCY_KEY")
  private String currencyKey;

  private ZonedDateTime departureTimestamp, arrivalTimestamp;

  private java.time.Duration flightDuration;

  public Flight() {

  }

  public Flight(Integer id, FlightConnection flightConnection, LocalDate dateOfDeparture,
      LocalTime timeOfDeparture, ZoneId timeZoneOfDeparture, LocalDate dateOfArrival,
      LocalTime timeOfArrival, ZoneId timeZoneOfArrival, Double pricePerPerson,
      String currencyKey) {
    this.id = id;
    this.flightConnection = flightConnection;
    this.dateOfDeparture = dateOfDeparture;
    this.timeOfDeparture = timeOfDeparture;
    this.timeZoneOfDeparture = timeZoneOfDeparture;
    this.dateOfArrival = dateOfArrival;
    this.timeOfArrival = timeOfArrival;
    this.timeZoneOfArrival = timeZoneOfArrival;
    this.pricePerPerson = pricePerPerson;
    this.currencyKey = currencyKey;

    this.departureTimestamp = ZonedDateTime.of(dateOfDeparture, timeOfDeparture, timeZoneOfDeparture);
    this.arrivalTimestamp = ZonedDateTime.of(dateOfArrival, timeOfArrival, timeZoneOfArrival);
    this.flightDuration = java.time.Duration.ofMinutes(ChronoUnit.MINUTES.between(arrivalTimestamp, departureTimestamp));
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public FlightConnection getFlightConnection() {
    return flightConnection;
  }

  public void setFlightConnection(FlightConnection flightConnection) {
    this.flightConnection = flightConnection;
  }

  public Double getPricePerPerson() {
    return pricePerPerson;
  }

  public void setPricePerPerson(Double pricePerPerson) {
    this.pricePerPerson = pricePerPerson;
  }

  public String getCurrencyKey() {
    return currencyKey;
  }

  public void setCurrencyKey(String currencyKey) {
    this.currencyKey = currencyKey;
  }

  public ZonedDateTime getDepartureTimestamp() {
    return departureTimestamp;
  }

  public void setDepartureTimestamp(ZonedDateTime departureTimestamp) {
    this.departureTimestamp = departureTimestamp;
  }

  public ZonedDateTime getArrivalTimestamp() {
    return arrivalTimestamp;
  }

  public void setArrivalTimestamp(ZonedDateTime arrivalTimestamp) {
    this.arrivalTimestamp = arrivalTimestamp;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Flight flight = (Flight) obj;
      return
          ((id == null && flight.getId() == null)
              || id.equals(flight.getId())) &&
          ((flightConnection == null && flight.getFlightConnection() == null)
              || flightConnection.equals(flight.getFlightConnection())) &&
          ((departureTimestamp == null && flight.getDepartureTimestamp() == null)
              || departureTimestamp.equals(flight.getDepartureTimestamp())) &&
          ((arrivalTimestamp == null && flight.getArrivalTimestamp() == null)
              || arrivalTimestamp.equals(flight.getArrivalTimestamp())) &&
          ((pricePerPerson == null && flight.getPricePerPerson() == null)
              || pricePerPerson.equals(flight.getPricePerPerson()))&&
          ((currencyKey == null && flight.getCurrencyKey() == null)
              || currencyKey.equals(flight.getCurrencyKey()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (flightConnection != null ? flightConnection.toString() + '\n' : "")
            + (departureTimestamp != null ? "Departure: " + departureTimestamp.toString() + '\n' : "")
            + (arrivalTimestamp != null   ? "Arrival  : " + arrivalTimestamp.toString() + '\n' : "")
            + (pricePerPerson != null && currencyKey != null ?
                  "Price    : " + pricePerPerson.toString() + " " + currencyKey.toString() : "");
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null        ? id.hashCode()                 : null)
            + (flightConnection != null   ? flightConnection.hashCode()   : null)
            + (departureTimestamp != null ? departureTimestamp.hashCode() : null)
            + (arrivalTimestamp != null   ? arrivalTimestamp.hashCode()   : null)
            + (pricePerPerson != null     ? pricePerPerson.hashCode()     : null)
            + (currencyKey != null        ? currencyKey.hashCode()        : null)).hashCode();
  }

}
