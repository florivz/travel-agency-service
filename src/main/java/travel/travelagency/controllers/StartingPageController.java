package travel.travelagency.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import travel.travelagency.TravelAgencyServiceApplication;

public class StartingPageController {

    public static final String VIEW_NAME = "starting_page.fxml";


    @FXML private Rectangle _bg__starting_page;
    @FXML private Text order_no_;
    @FXML private Text _41874403;
    @FXML private Text customer_name;
    @FXML private Text xxx;
    @FXML private Text customer_id;
    @FXML private Text xxx_ek1;
    @FXML private Text search;
    @FXML private Text search_for_an_existing_trip;
    @FXML private ImageView david_vives_elf8m_ywrty_unsplash_1;
    @FXML private Text destination;
    @FXML private Text where_to_;
    @FXML private Text people;
    @FXML private Text how_many_persons_;
    @FXML private Text date;
    @FXML private Text select_date;
    @FXML private Text search_ek1;
    @FXML private Rectangle rectangle_6;
    @FXML private Text log_out;
    @FXML private ImageView ellipse_2;
    @FXML private Text home;
    @FXML private Text _new_trip_ek1;
    @FXML private Text _bookings;
    @FXML private Rectangle line_1;
    @FXML private Text life_is_short__travel_often;
    @FXML private Text book_a_new_trip;

    public void initialize() throws IOException{

        //custom code here

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
    private void _bookings_onClick() throws IOException {
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


