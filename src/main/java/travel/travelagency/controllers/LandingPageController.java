package travel.travelagency.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.TravelAgencyServiceApplication;
import travel.travelagency.authentication.TravelAgencyDatabaseAuthenticator;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;

public class LandingPageController {

    static final Logger logger = LogManager.getLogger(LandingPageController.class);

    public static final String VIEW_NAME = "landing_page.fxml";
    @FXML public Group usernameGroup;
    @FXML public Group usernameFrame;
    @FXML public Group passwordGroup;
    @FXML public Group passwordFrame;


    @FXML private Rectangle _bg__landing_page;
    @FXML private Rectangle rectangle_2;
    @FXML private ImageView ellipse_2;
    @FXML private ImageView rectangle_3;
    @FXML private Text log_in;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField usernameTextField;
    @FXML private Text headerMessage;

    public void initialize() throws IOException{
        //custom code here
    }

    @FXML
    private void frame_3_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("starting_page");
    }

    @FXML
    private void _login_onClick(ActionEvent actionEvent) throws IOException {
        try {
            TravelAgencyEntityManagerFactory factory = TravelAgencyDatabaseAuthenticator.loginToDataBase(
                    usernameTextField.getText(), passwordTextField.getText()
            );
            TravelAgencyServiceApplication.setRoot(StartingPageController.VIEW_NAME);
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
            usernameTextField.clear();
            passwordTextField.clear();
        }
    }


}

