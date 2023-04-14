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
@Table(name = "HOTEL_BOOKING")
public class HotelBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HOTEL_BOOKING_ID")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "HOTEL_ID")
  private Hotel hotel;

  @Column(name = "NUMBER_OF_GUESTS")
  private Integer numberOfGuests;

  @Column(name = "NUMBER_OF_NIGHTS")
  private Integer numberOfNights;

  public HotelBooking() {

  }

  public HotelBooking(Hotel hotel, Integer numberOfGuests, Integer numberOfNights) {
    this.hotel = hotel;
    this.numberOfGuests = numberOfGuests;
    this.numberOfNights = numberOfNights;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public Integer getNumberOfGuests() {
    return numberOfGuests;
  }

  public void setNumberOfGuests(Integer numberOfGuests) {
    this.numberOfGuests = numberOfGuests;
  }

  public Integer getNumberOfNights() {
    return numberOfNights;
  }

  public void setNumberOfNights(Integer numberOfNights) {
    this.numberOfNights = numberOfNights;
  }

  public double getTotalPrice() {
    return numberOfGuests * numberOfNights * hotel.getPricePerPerson();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      HotelBooking booking = (HotelBooking) obj;
      return
          ((id == null && booking.getId() == null) || id.equals(booking.getId())) &&
          ((hotel == null && booking.getHotel() == null) || hotel.equals(booking.getHotel())) &&
          ((numberOfGuests == null && booking.getNumberOfGuests() == null)
              || numberOfGuests.equals(booking.getNumberOfGuests())) &&
          ((numberOfNights == null && booking.getNumberOfNights() == null)
              || numberOfNights.equals(booking.getNumberOfNights()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (hotel != null          ? hotel.toString() + '\n' : "" )
      + (numberOfGuests != null ? "Guests: " + numberOfGuests + '\n' : "" )
      + (numberOfNights != null ? "Nights: " + numberOfNights : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null    ? id.hashCode()             : null)
            + (hotel != null          ? hotel.hashCode()          : null)
            + (numberOfGuests != null ? numberOfGuests.hashCode() : null)
            + (numberOfNights != null ? numberOfNights.hashCode() : null)).hashCode();
  }

}
