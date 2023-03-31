package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "flight_connection")
public class FlightConnection {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flight_connection_id")
  private Integer id;

  @Column(name = "carrier_id")
  private String carrierID;

  @Column(name = "connection_id")
  private String connectionID;

  @Column(name = "departure_airport_code")
  private String departureAirport;

  @Column(name = "arrival_airport_code")
  private String arrivalAirport;

  public FlightConnection() {

  }

  public FlightConnection(Integer id, String carrierID, String connectionID,
      String departureAirport,
      String arrivalAirport) {
    this.id = id;
    this.carrierID = carrierID;
    this.connectionID = connectionID;
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCarrierID() {
    return carrierID;
  }

  public void setCarrierID(String carrierID) {
    this.carrierID = carrierID;
  }

  public String getConnectionID() {
    return connectionID;
  }

  public void setConnectionID(String connectionID) {
    this.connectionID = connectionID;
  }

  public String getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
  }

  public String getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      FlightConnection flightConnection = (FlightConnection) obj;
      return
          ((id == null && flightConnection.getId() == null)
              || id.equals(flightConnection.getId())) &&
          ((carrierID == null && flightConnection.getCarrierID() == null)
              || carrierID.equals(flightConnection.getCarrierID())) &&
          ((connectionID == null && flightConnection.getConnectionID() == null)
              || connectionID.equals(flightConnection.getConnectionID())) &&
          ((departureAirport == null && flightConnection.getDepartureAirport() == null)
              || departureAirport.equals(flightConnection.getDepartureAirport())) &&
          ((arrivalAirport == null && flightConnection.getArrivalAirport() == null)
              || arrivalAirport.equals(flightConnection.getArrivalAirport()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (carrierID != null        ? carrierID.toString()                    : "_".repeat(2) )
      + (connectionID != null     ? connectionID.toString()                 : "_".repeat(4))
      + (departureAirport != null ? " from " + departureAirport.toString()  : "")
      + (arrivalAirport != null   ? " to " + arrivalAirport.toString()      : "");
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
            + (carrierID != null        ? carrierID.hashCode()        : null)
            + (connectionID != null     ? connectionID.hashCode()     : null)
            + (departureAirport != null ? departureAirport.hashCode() : null)
            + (arrivalAirport != null   ? arrivalAirport.hashCode()   : null)).hashCode();
  }

}
