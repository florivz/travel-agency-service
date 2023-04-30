package travel.travelagency.controllers;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import travel.travelagency.TravelAgencyServiceApplication;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;

public class StartingPageController extends TravelAgencyController {

    public static final String VIEW_NAME = "starting_page.fxml";

    private final String LANGUAGES_DIRECTORY = "src/main/resources/languages/";

    private final String VIEW_DIRECTORY = "starting_page/";

    @FXML public Text agencyName;

    @FXML public Text home;
    @FXML public Text searchBookings;
    @FXML public Text createBooking;
    @FXML public Button logoutButton;

    @FXML public Text searchBookingTitle;
    @FXML public Text bookingIDText;
    @FXML public TextField bookingIDTextField;
    @FXML public Text customerNameText;
    @FXML public TextField customerNameTextField;
    @FXML public Text customerIDText;
    @FXML public TextField customerIDTextField;
    @FXML public Button searchBookingButton;
    @FXML public Text createBookingTitle;
    @FXML public Button createBookingButton;

    private TravelAgencyEntityManagerFactory factory;

    public void initialize() throws IOException{
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
            LANGUAGES_DIRECTORY + VIEW_DIRECTORY, TravelAgencyServiceApplication.getLanguageFile()
        );
        agencyName.setText(languageProperties.getProperty("menu.agencyName", "Agency Reis"));
        home.setText(languageProperties.getProperty("menu.home", "Home"));
        createBooking.setText(languageProperties.getProperty("menu.createBooking", "New Booking"));
        searchBookings.setText(languageProperties.getProperty("menu.showBookings", "Show Bookings"));
        logoutButton.setText(languageProperties.getProperty("menu.logout", "LOG OUT"));

        searchBookingTitle.setText(languageProperties.getProperty("searchBooking.title"));
        bookingIDText.setText(languageProperties.getProperty("searchBooking.bookingNumber"));
        bookingIDTextField.setPromptText(languageProperties.getProperty("searchBooking.bookingNumber"));
        customerIDText.setText(languageProperties.getProperty("searchBooking.customerNumber"));
        customerIDTextField.setPromptText(languageProperties.getProperty("searchBooking.customerNumber"));
        customerNameText.setText(languageProperties.getProperty("searchBooking.customerName"));
        customerNameTextField.setPromptText(languageProperties.getProperty("searchBooking.customerName"));
        searchBookingButton.setText(languageProperties.getProperty("searchBooking.searchButton"));

        createBookingTitle.setText(languageProperties.getProperty("createBooking.title"));
        createBookingButton.setText(languageProperties.getProperty("createBooking.createButton"));
    }

    public void setEntityManagerFactory(TravelAgencyEntityManagerFactory factory) {
        this.factory = factory;
    }


    @FXML
    private void _home_onClick() {
        application.setRoot(VIEW_NAME);
    }

    @FXML
    private void _searchBookings_onClick() {
        application.setRoot(BookingsController.VIEW_NAME);
    }

    @FXML
    private void _logout_onClick(ActionEvent actionEvent) {
        factory = null;
        application.setRoot(LandingPageController.VIEW_NAME);
    }

    @FXML
    private void _search_bookings_onClick(ActionEvent actionEvent) {
        FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(BookingsController.VIEW_NAME);
        Scene scene = application.loadScene(loader);
        BookingsController controller = loader.getController();
        controller.setApplication(application);
        controller.setEntityManager(factory.createEntityManager());
        application.setScene(scene);
    }
}


