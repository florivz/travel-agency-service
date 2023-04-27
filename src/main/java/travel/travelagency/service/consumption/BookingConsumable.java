package travel.travelagency.service.consumption;

import java.time.LocalDate;

public record BookingConsumable(
    Integer bookingID, Integer customerID, String customerName, LocalDate date, Double totalPrice, String currencyKey
) {

}