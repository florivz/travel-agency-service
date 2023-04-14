package travel.travelagency.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import travel.travelagency.TravelAgencyServiceApplication;

public class LandingPageController {

    public static final String VIEW_NAME = "landing_page.fxml";


    @FXML private Rectangle _bg__landing_page;
    @FXML private Rectangle rectangle_2;
    @FXML private ImageView ellipse_2;
    @FXML private ImageView rectangle_3;
    @FXML private Text log_in;
    @FXML private Text password_ek1;
    @FXML private Text username_ek1;
    @FXML private Text welcome_back_;

    public void initialize() throws IOException{
        //custom code here
    }

    @FXML
    private void frame_3_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("starting_page");
    }

    @FXML
    private void _login_onClick(ActionEvent actionEvent) throws IOException {
        TravelAgencyServiceApplication.setRoot(StartingPageController.VIEW_NAME);
    }


}

