package travel.travelagency.controllers;
import java.io.IOException;
import javafx.fxml.FXML;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import travel.travelagency.TravelAgencyServiceApplication;

public class BookingDetailsController {

    public static final String VIEW_NAME = "booking_details.fxml";

    @FXML private Rectangle _bg__booking_details;
    @FXML private Text new_booking;
    @FXML private Text delete_booking;
    @FXML private Text adjust_booking;
    @FXML private Rectangle line_1;
    @FXML private Text duration_ek1;
    @FXML private Text _22_06_23___06_07_23;
    @FXML private Text date_of_purchase;
    @FXML private Text _21_01_23;
    @FXML private Text customer_name_ek1;
    @FXML private Text baedorf__cedrik;
    @FXML private Text customer_id_ek1;
    @FXML private Text bace4927;
    @FXML private Text order_no__ek1;
    @FXML private Text _18627406;
    @FXML private ImageView image_6;
    @FXML private Text plaza_de_las_cortes__7_28014_madrid;
    @FXML private Text westin_palace;
    @FXML private Text room_333;
    @FXML private Text booking_duration;
    @FXML private Text _22_06_23___06_07_23_ek1;
    @FXML private Rectangle rectangle_6;
    @FXML private ImageView ellipse_2;
    @FXML private Text _home;
    @FXML private Text _new_trip;
    @FXML private Text _bookings;
    @FXML private Rectangle line_1_ek1;
    @FXML private Text log_out;
    @FXML private Text life_is_short__travel_often;
    @FXML private Rectangle rectangle_7;
    @FXML private Text thursday__june_22nd;
    @FXML private Text _13_00_fra_to_mad;
    @FXML private Text _16_00_arrive_madrid_airport;
    @FXML private Text pst_spanair_airlines_302;
    @FXML private Text pst_spanair_airlines_302_ek1;
    @FXML private Text aircraft__airbus_a321___320_passenger_c43_nonstop_3h__00m;
    @FXML private Rectangle rectangle_8;
    @FXML private Text thursday__july_6th;
    @FXML private Text _17_00_mad_to_mun;
    @FXML private Text _19_40_arrive_munich_airport;
    @FXML private Text pst_spanair_airlines_302_ek2;
    @FXML private Text pst_spanair_airlines_302_ek3;
    @FXML private Text aircraft__airbus_a321___320_passenger_a12_nonstop_2h__40m;
    @FXML private ImageView image_4;
    @FXML private ImageView image_5;
    @FXML private Text hotel_information_ek2;

    public void initialize() throws IOException{

        //custom code here

    }


    @FXML
    private void back_button_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("new_trip");
    }

    @FXML
    private void frame_3_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("bookings");
    }

    @FXML
    private void _home_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("starting_page");
    }

    @FXML
    private void _new_trip_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("new_trip");
    }

    @FXML
    private void _bookings_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("bookings");
    }

    @FXML
    private void frame_3_ek3_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("landing_page");
    }


}


