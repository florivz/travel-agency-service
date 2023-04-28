package travel.travelagency.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    @FXML public Group credentialsGroup;
    @FXML public Group usernameGroup;
    @FXML private TextField usernameTextField;
    @FXML public Group passwordGroup;
    @FXML private PasswordField passwordTextField;
    @FXML public Group infoMessageGroup;
    @FXML public Text infoMessage;
    @FXML public Group loginButtonGroup;
    @FXML public Group loginButtonFrame;


    @FXML private Rectangle _bg__landing_page;
    @FXML private Rectangle rectangle_2;
    @FXML private ImageView ellipse_2;
    @FXML private ImageView rectangle_3;
    @FXML private Text log_in;
    @FXML private Text headerMessage;

    private static class LoginThread extends Thread {

        private final TextField USERNAME, PASSWORD;
        private final Text INFO;

        public LoginThread(TextField username, TextField password, Text info) {
            this.USERNAME = username;
            this.PASSWORD = password;
            this.INFO = info;
        }

        @Override
        public void run() {
            try {
                TravelAgencyEntityManagerFactory factory = TravelAgencyDatabaseAuthenticator.loginToDataBase(
                        USERNAME.getText(), PASSWORD.getText()
                );
                TravelAgencyServiceApplication.setRoot(StartingPageController.VIEW_NAME);
            } catch (RuntimeException | IOException e) {
                logger.info(e.getMessage());
                USERNAME.clear();
                PASSWORD.clear();
                INFO.setFill(Color.RED);
                INFO.setText("Invalid credentials");
            }
        }

    }

    public void initialize() throws IOException{
        //custom code here
    }

    @FXML
    private void frame_3_onClick() throws IOException {
        TravelAgencyServiceApplication.setRoot("starting_page");
    }

    @FXML
    private void _login_onClick(ActionEvent actionEvent) throws IOException {
        infoMessage.setFill(Color.BLACK);
        infoMessage.setText("Attempting login...");
        new LoginThread(usernameTextField, passwordTextField, infoMessage).start();
    }


}

