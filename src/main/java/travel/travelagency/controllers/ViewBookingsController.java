package travel.travelagency.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.TravelAgencyServiceApplication;
import travel.travelagency.service.consumption.BookingConsumable;
import travel.travelagency.service.consumption.TravelAgencyViewConsumptionService;
import travel.travelagency.service.consumption.TravelAgencyViewConsumptionServiceImplementation;
import travel.travelagency.service.consumption.TripConsumable;
import travel.travelagency.service.data.TravelAgencyViewDataServiceImplementation;

public class ViewBookingsController extends TravelAgencyController {

    static final Logger logger = LogManager.getLogger(ViewBookingsController.class);

    public static final String VIEW_NAME = "view_bookings.fxml";

    //title bar
    @FXML public Text agencyName;

    @FXML public Text home;
    @FXML public Text searchBookings;
    @FXML public Text createBooking;
    @FXML public Button logoutButton;

    //search bar
    @FXML public Text searchBookingTitle;
    @FXML public Text bookingIDText;
    @FXML public TextField bookingIDTextField;
    @FXML public Text customerNameText;
    @FXML public TextField customerNameTextField;
    @FXML public Text customerIDText;
    @FXML public TextField customerIDTextField;
    @FXML public Button searchBookingButton;

    //table view
    @FXML public TableView<BookingConsumable> bookingsTableView;
    @FXML public TableView<TripConsumable> tripTableView;

    /**
     * service used to display bookings and trips in their corresponding table view
     */
    private TravelAgencyViewConsumptionService service;

    public ViewBookingsController(TravelAgencyServiceApplication application) {
        this.application = application;
    }

    public void initialize() throws IOException{
        setTexts(application.getLanguageFile());
        this.service = new TravelAgencyViewConsumptionServiceImplementation(
            new TravelAgencyViewDataServiceImplementation(
                application.createEntityManager()
            )
        );
        this.loadBookingsTableView();
    }

    private void setTexts(String languageFile) {
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
            TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "starting_page/", languageFile
        );
        agencyName.setText(languageProperties.getProperty("menu.agencyName", "Agency Reis"));
        home.setText(languageProperties.getProperty("menu.home", "Home"));
        createBooking.setText(languageProperties.getProperty("menu.createBooking", "New Booking"));
        searchBookings.setText(languageProperties.getProperty("menu.showBookings", "Show Bookings"));
        logoutButton.setText(languageProperties.getProperty("menu.logout", "LOG OUT"));
    }

    public void loadBookingsTableView() {
        tripTableView.setVisible(false);
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
            TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_bookings/", application.getLanguageFile()
        );
        TableColumn<BookingConsumable, Integer> bookingIDCol = createTableColumn(languageProperties, "bookingID", 200);
        TableColumn<BookingConsumable, Integer> customerIDCol = createTableColumn(languageProperties, "customerID", 200);
        TableColumn<BookingConsumable, String> customerNameCol = createTableColumn(languageProperties, "customerName", 200);
        TableColumn<BookingConsumable, LocalDate> bookingDateCol = createTableColumn(languageProperties, "date", 200);
        TableColumn<BookingConsumable, Double> totalPriceCol = createTableColumn(languageProperties, "totalPrice", 200);
        TableColumn<BookingConsumable, String> currencyCol = createTableColumn(languageProperties, "currencyKey", 100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<BookingConsumable> bookingsList = loadBookingList();
                ObservableList<BookingConsumable> bookingsObsList = FXCollections.observableList(bookingsList);
                Platform.runLater(() -> {
                    bookingsTableView.getColumns().add(bookingIDCol);
                    bookingsTableView.getColumns().add(customerIDCol);
                    bookingsTableView.getColumns().add(customerNameCol);
                    bookingsTableView.getColumns().add(bookingDateCol);
                    bookingsTableView.getColumns().add(totalPriceCol);
                    bookingsTableView.getColumns().add(currencyCol);

                    bookingsTableView.getItems().clear();
                    bookingsTableView.setItems(bookingsObsList);
                    bookingsTableView.setPlaceholder(null);
                    bookingsTableView.setVisible(true);
                });
            }
        }).start();
    }

    public void loadTripsTableView() {
        bookingsTableView.setVisible(false);
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
                TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_bookings/", application.getLanguageFile()
        );
        TableColumn<TripConsumable, Integer> tripIDCol = createTableColumn(languageProperties, "tripID", 200);
        TableColumn<TripConsumable, Integer> noHotelsCol = createTableColumn(languageProperties, "numberOfHotels", 200);
        TableColumn<TripConsumable, Integer> noFlightsCol = createTableColumn(languageProperties, "numberOfFlights", 200);
        TableColumn<TripConsumable, Double> totalPriceCol = createTableColumn(languageProperties, "totalPrice", 200);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TripConsumable> tripList = loadTripList();
                ObservableList<TripConsumable> tripObsList = FXCollections.observableList(tripList);
                Platform.runLater(() -> {
                    tripTableView.getColumns().add(tripIDCol);
                    tripTableView.getColumns().add(noHotelsCol);
                    tripTableView.getColumns().add(noFlightsCol);
                    tripTableView.getColumns().add(totalPriceCol);

                    tripTableView.getItems().clear();
                    tripTableView.setItems(tripObsList);
                    tripTableView.setPlaceholder(null);
                    tripTableView.setVisible(true);
                });
            }
        }).start();
    }

    private <T, V> TableColumn<T, V> createTableColumn(Properties languageProperties, String id, int width) {
        TableColumn<T, V> column = new TableColumn<>(languageProperties.getProperty("tableView." + id, id));
        column.setCellValueFactory(new PropertyValueFactory<>(id));
        column.setPrefWidth(width);
        return column;
    }

    private List<BookingConsumable> loadBookingList() {
        List<BookingConsumable> bookingsList = new LinkedList<>();
        Integer bookingID = bookingIDTextField.getText().isEmpty() ?
                null : Integer.parseInt(bookingIDTextField.getText());
        Integer customerID = customerIDTextField.getText().isEmpty() ?
                null : Integer.parseInt(customerIDTextField.getText());
        String customerName = customerNameTextField.getText().isEmpty() ?
                null : customerNameTextField.getText();
        if(bookingID != null) {
            if(customerID != null && customerName != null)
                bookingsList = service.getBooking(bookingID, customerID, customerName);
            else if(customerID != null)
                bookingsList = service.getBooking(bookingID, customerID);
            else if(customerName != null)
                bookingsList = service.getBooking(bookingID, customerName);
            else
                bookingsList = service.getBooking(bookingID);
        } else {
            if(customerID != null && customerName != null)
                bookingsList = service.getBookings(customerID, customerName);
            else if(customerID != null)
                bookingsList = service.getBookings(customerID);
            else if(customerName != null)
                bookingsList = service.getBookings(customerName);
            else
                bookingsList = service.getBookings();
        }
        return bookingsList;
    }

    private List<TripConsumable> loadTripList() {
        Integer bookingID = bookingIDTextField.getText().isEmpty() ?
            null : Integer.parseInt(bookingIDTextField.getText());
        if(bookingID != null)
            return service.getTrips(bookingID);
        else
            return new LinkedList<>();
    }

    public void setBookingID(int bookingID) {
        bookingIDTextField.setText(String.valueOf(bookingID));
    }

    public void setCustomerID(int customerID) {
        customerIDTextField.setText(String.valueOf(customerID));
    }

    public void setCustomerName(String customerName) {
        customerNameTextField.setText(customerName);
    }

    public void _home_onClick(MouseEvent mouseEvent) {
        mouseEvent.consume();
        FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(StartingPageController.VIEW_NAME);
        try {
            loader.setControllerFactory(c -> new StartingPageController(application));
            Scene scene = application.loadScene(loader);
            application.setScene(scene);
        } catch (LoadException e) {
            logger.error(e.getMessage());
        }
    }

    public void _searchBookings_onClick(MouseEvent mouseEvent) {
        bookingIDTextField.clear();
        customerIDTextField.clear();
        customerNameTextField.clear();
        this.loadBookingsTableView();
    }

    public void _logout_onClick(ActionEvent actionEvent) {
        service = null;
        application.setRoot(LandingPageController.VIEW_NAME, new LandingPageController(application));
    }

    public void _bookingsTableView_onClick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() < 2)
            return;
        TableView<BookingConsumable> tableView = (TableView<BookingConsumable>) mouseEvent.getSource();
        BookingConsumable bookingConsumable = tableView.getSelectionModel().getSelectedItem();
        bookingIDTextField.setText(String.valueOf(bookingConsumable.getBookingID()));
        loadTripsTableView();
    }

    public void _tripTableView_onClick(MouseEvent mouseEvent) {

    }

    /**
     * Action Listener for search button and text fields for booking id, customer id, and customer name
     * @param actionEvent automatically generated actionEvent from runtime
     */
    public void _search_bookings_onClick(ActionEvent actionEvent) {
        this.loadBookingsTableView();
    }
}