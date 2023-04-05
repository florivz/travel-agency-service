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

  public HotelBooking() {

  }

  public HotelBooking(Integer id, Hotel hotel, Integer numberOfGuests) {
    this.id = id;
    this.hotel = hotel;
    this.numberOfGuests = numberOfGuests;
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

  public double getTotalPrice() {
    return numberOfGuests * hotel.getPricePerPerson();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      HotelBooking booking = (HotelBooking) obj;
      return
          ((id == null && booking.getId() == null) || id.equals(booking.getId())) &&
          ((hotel == null && booking.getHotel() == null) || hotel.equals(booking.getHotel())) &&
          ((numberOfGuests == null && booking.getNumberOfGuests() == null)
              || numberOfGuests.equals(booking.getNumberOfGuests()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (hotel != null          ? hotel.toString() + '\n' : "" )
      + (numberOfGuests != null ? "Guests: " + numberOfGuests : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null    ? id.hashCode()             : null)
            + (hotel != null          ? hotel.hashCode()          : null)
            + (numberOfGuests != null ? numberOfGuests.hashCode() : null)).hashCode();
  }

}
