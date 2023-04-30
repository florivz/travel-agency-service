package travel.travelagency.controllers;

import java.io.IOException;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import travel.travelagency.TravelAgencyServiceApplication;

public class StartingPageController {

    public static final String VIEW_NAME = "starting_page.fxml";

    private final String LANGUAGES_DIRECTORY = "src/main/resources/languages/";

    private final String VIEW_DIRECTORY = "landing_page/";

    @FXML public Rectangle startingPage;
    @FXML public Group searchTripGroup;
    @FXML public Group bookingIDGroup;
    @FXML public Text bookingIDText;
    @FXML public TextField bookingIDTextField;
    @FXML public Group customerNameGroup;
    @FXML public Text customerNameText;
    @FXML public TextField customerNameTextField;
    @FXML public Group customerIDGroup;
    @FXML public Text customerIDText;
    @FXML public TextField customerIDTextField;
    @FXML public Button searchButton;

    public void initialize() throws IOException{
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
            LANGUAGES_DIRECTORY + VIEW_DIRECTORY + TravelAgencyServiceApplication.getLanguageFile()
        );
        bookingIDText.setText(languageProperties.getProperty("booking.number"));
        bookingIDTextField.setPromptText(languageProperties.getProperty("booking.number"));
        customerIDText.setText(languageProperties.getProperty("customer.number"));
        customerIDTextField.setPromptText(languageProperties.getProperty("customer.number"));
        customerNameText.setText(languageProperties.getProperty("customer.name"));
        customerNameTextField.setPromptText(languageProperties.getProperty("customer.name"));
    }


    @FXML
    private void frame_3_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("bookings_filtered");
    }

    @FXML
    private void frame_3_ek1_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot(NewTripController.VIEW_NAME);
    }

    @FXML
    private void frame_3_ek2_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot(LandingPageController.VIEW_NAME);
    }

    @FXML
    private void _new_trip_ek1_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot(NewTripController.VIEW_NAME);
    }

    @FXML
    private void _all_bookings_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot(BookingsController.VIEW_NAME);
    }

    @FXML
    private void _logout_onClick(ActionEvent actionEvent) throws  IOException {
        TravelAgencyServiceApplication.setRoot(LandingPageController.VIEW_NAME);
    }

    @FXML
    private void _search_bookings_onClick(ActionEvent actionEvent) throws IOException {
        TravelAgencyServiceApplication.setRoot(BookingsController.VIEW_NAME);
    }
}


