package travel.travelagency.entities;

import java.time.Duration;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name = "flight")
public class Flight {

  static final Logger logger = LogManager.getLogger(Flight.class);

  private static final String
    MSG_NULL_TIMESTAMP = "date, time, and time zone have not all been initialized yet";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flight_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "flight_connection_id")
  private FlightConnection flightConnection;

  @Column(name = "departure_date")
  private LocalDate dateOfDeparture;

  @Column(name = "departure_time")
  private LocalTime timeOfDeparture;

  @Column(name = "departure_time_zone")
  private String timeZoneOfDeparture;

  @Column(name = "arrival_date")
  private LocalDate dateOfArrival;

  @Column(name = "arrival_time")
  private LocalTime timeOfArrival;

  @Column(name = "arrival_time_zone")
  private String timeZoneOfArrival;

  @Column(name = "price_per_person")
  private Double pricePerPerson;

  @Column(name = "currency_key")
  private String currencyKey;

  public Flight() {

  }

  public Flight(FlightConnection flightConnection, LocalDate dateOfDeparture,
      LocalTime timeOfDeparture, String timeZoneOfDeparture, LocalDate dateOfArrival,
      LocalTime timeOfArrival, String timeZoneOfArrival, Double pricePerPerson,
      String currencyKey) {
    this.flightConnection = flightConnection;
    this.dateOfDeparture = dateOfDeparture;
    this.timeOfDeparture = timeOfDeparture;
    this.timeZoneOfDeparture = timeZoneOfDeparture;
    this.dateOfArrival = dateOfArrival;
    this.timeOfArrival = timeOfArrival;
    this.timeZoneOfArrival = timeZoneOfArrival;
    this.pricePerPerson = pricePerPerson;
    this.currencyKey = currencyKey;
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
    try {
      return ZonedDateTime.of(dateOfDeparture, timeOfDeparture, ZoneId.of(timeZoneOfDeparture));
    } catch (NullPointerException e) {
      logger.error(MSG_NULL_TIMESTAMP + " for method getDepartureTimestamp()");
      return null;
    }
  }

  public void setDepartureTimestamp(ZonedDateTime departureTimestamp) {
    this.dateOfDeparture = departureTimestamp.toLocalDate();
    this.timeOfDeparture = departureTimestamp.toLocalTime();
    this.timeZoneOfDeparture = departureTimestamp.getZone().toString();
  }

  public ZonedDateTime getArrivalTimestamp() {
    try {
      return ZonedDateTime.of(dateOfArrival, timeOfArrival, ZoneId.of(timeZoneOfArrival));
    } catch (NullPointerException e) {
      logger.error(MSG_NULL_TIMESTAMP + " for method getArrivalTimestamp()");
      return null;
    }
  }

  public void setArrivalTimestamp(ZonedDateTime arrivalTimestamp) {
    this.dateOfArrival = arrivalTimestamp.toLocalDate();
    this.timeOfArrival = arrivalTimestamp.toLocalTime();
    this.timeZoneOfArrival = arrivalTimestamp.getZone().toString();
  }

  public Duration getFlightDuration() {
    ZonedDateTime
        departureTimestamp  = this.getDepartureTimestamp(),
        arrivalTimestamp    = this.getArrivalTimestamp();
    if(departureTimestamp != null && arrivalTimestamp != null)
      return Duration.ofMinutes(ChronoUnit.MINUTES.between(
          this.getArrivalTimestamp(),
          this.getDepartureTimestamp()
      )).abs();
    logger.error(MSG_NULL_TIMESTAMP + " for method getFlightDurationTimestamp()");
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Flight flight = (Flight) obj;
      ZonedDateTime
          departureTimestamp  = this.getDepartureTimestamp(),
          arrivalTimestamp    = this.getArrivalTimestamp();
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
    ZonedDateTime
        departureTimestamp  = this.getDepartureTimestamp(),
        arrivalTimestamp    = this.getArrivalTimestamp();
    return
        (flightConnection != null ? flightConnection.toString() + '\n' : "")
            + (departureTimestamp != null ? "Departure: " + departureTimestamp.toString() + '\n' : "")
            + (arrivalTimestamp != null   ? "Arrival  : " + arrivalTimestamp.toString() + '\n' : "")
            + (pricePerPerson != null && currencyKey != null ?
                  "Price    : " + pricePerPerson.toString() + " " + currencyKey.toString() : "");
  }

  @Override
  public int hashCode() {
    ZonedDateTime
        departureTimestamp  = this.getDepartureTimestamp(),
        arrivalTimestamp    = this.getArrivalTimestamp();
    return
        (String.valueOf(id != null        ? id.hashCode()                 : null)
            + (flightConnection != null   ? flightConnection.hashCode()   : null)
            + (departureTimestamp != null ? departureTimestamp.hashCode() : null)
            + (arrivalTimestamp != null   ? arrivalTimestamp.hashCode()   : null)
            + (pricePerPerson != null     ? pricePerPerson.hashCode()     : null)
            + (currencyKey != null        ? currencyKey.hashCode()        : null)).hashCode();
  }

}
