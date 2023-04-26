package travel.travelagency.service.consumption;

public record TripConsumable(
    Integer tripID, Integer numberOfHotels, Integer numberOfFlights, Double totalPrice
) {

}
