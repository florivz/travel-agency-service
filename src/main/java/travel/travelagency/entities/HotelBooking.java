package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "HOTEL_BOOKING")
public class HotelBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HOTEL_BOOKING_ID")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "TRIP_ID")
  private Trip trip;

  @ManyToOne
  @JoinColumn(name = "HOTEL_ID")
  private Hotel hotel;

  @Column(name = "NUMBER_OF_GUESTS")
  private Integer numberOfGuests;

  public HotelBooking() {

  }



}
