package travel.travelagency.controllers;

import java.io.IOException;
import javafx.fxml.FXML;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import travel.travelagency.TravelAgencyServiceApplication;

import javax.persistence.EntityManager;

public class BookingsController extends TravelAgencyController {

    public static final String VIEW_NAME = "bookings.fxml";


    @FXML private Rectangle _bg__bookings;
    @FXML private Text new_booking;
    @FXML private Text delete_booking;
    @FXML private Rectangle line_1;
    @FXML private Rectangle line_5;
    @FXML private Rectangle line_4;
    @FXML private Rectangle line_3;
    @FXML private Rectangle line_2;
    @FXML private Text duration_ek1;
    @FXML private Text _20_12_23___29_12_23;
    @FXML private Text date_of_purchase;
    @FXML private Text _16_02_23;
    @FXML private Text customer_name_ek1;
    @FXML private Text gerber__robin;
    @FXML private Text customer_id_ek1;
    @FXML private Text gero1191;
    @FXML private Text order_no__ek1;
    @FXML private Text _41874403;
    @FXML private ImageView image_3;
    @FXML private Text duration_ek3;
    @FXML private Text _01_07_23___22_07_23;
    @FXML private Text date_of_purchase_ek1;
    @FXML private Text _03_04_23;
    @FXML private Text customer_name_ek3;
    @FXML private Text becker__jonas;
    @FXML private Text customer_id_ek3;
    @FXML private Text bejo5205;
    @FXML private Text order_no__ek3;
    @FXML private Text _10029874;
    @FXML private ImageView image_3_ek1;
    @FXML private Text duration_ek5;
    @FXML private Text _10_04_23___11_05_23;
    @FXML private Text date_of_purchase_ek2;
    @FXML private Text _04_12_22;
    @FXML private Text customer_name_ek5;
    @FXML private Text veitz__florian;
    @FXML private Text customer_id_ek5;
    @FXML private Text vefl1299;
    @FXML private Text order_no__ek5;
    @FXML private Text _37256522;
    @FXML private ImageView image_2;
    @FXML private Text duration_ek7;
    @FXML private Text _07_08_23___14_08_23;
    @FXML private Text date_of_purchase_ek3;
    @FXML private Text _22_11_22;
    @FXML private Text customer_name_ek7;
    @FXML private Text gossner__anna;
    @FXML private Text customer_id_ek7;
    @FXML private Text goan8376;
    @FXML private Text order_no__ek7;
    @FXML private Text _28300174;
    @FXML private ImageView image_2_ek1;
    @FXML private Text duration_ek9;
    @FXML private Text _22_06_23___06_07_23;
    @FXML private Text date_of_purchase_ek4;
    @FXML private Text _21_01_23;
    @FXML private Text customer_name_ek9;
    @FXML private Text baedorf__cedrik;
    @FXML private Text customer_id_ek9;
    @FXML private Text bace4927;
    @FXML private Text order_no__ek9;
    @FXML private Text _18627406;
    @FXML private ImageView _image_1;
    @FXML private Text customer_name_ek11;
    @FXML private Text xxx;
    @FXML private Text customer_id_ek11;
    @FXML private Text xxx_ek1;
    @FXML private Text order_no__ek11;
    @FXML private Text _18627406_ek1;
    @FXML private Text search;
    @FXML private Rectangle rectangle_6;
    @FXML private ImageView ellipse_2;
    @FXML private Text _home;
    @FXML private Text _new_trip;
    @FXML private Text _bookings;
    @FXML private Rectangle line_1_ek1;
    @FXML private Text log_out;
    @FXML private Text life_is_short__travel_often;

    private EntityManager entityManager;

    public void initialize() throws IOException{

        //custom code here

    }

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }


    @FXML
    private void new_booking_button_onClick() throws IOException {
        application.setRoot("new_trip");
    }

    @FXML
    private void frame_3_onClick() throws IOException {
        application.setRoot("new_trip");
    }

    @FXML
    private void _image_1_onClick() throws IOException {
        application.setRoot("booking_details");
    }

    @FXML
    private void frame_3_ek2_onClick() throws IOException {
        application.setRoot("bookings_filtered");
    }

    @FXML
    private void _home_onClick() throws IOException {
        application.setRoot("languages/starting_page");
    }

    @FXML
    private void _new_trip_onClick() throws IOException {
        application.setRoot("new_trip");
    }

    @FXML
    private void _bookings_onClick() throws IOException {
        application.setRoot("bookings");
    }

    @FXML
    private void frame_3_ek3_onClick() throws IOException {
        application.setRoot("landing_page");
    }


}
